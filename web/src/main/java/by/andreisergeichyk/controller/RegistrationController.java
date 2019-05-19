package by.andreisergeichyk.controller;

import by.andreisergeichyk.dto.account.AccountDto;
import by.andreisergeichyk.entity.Account;
import by.andreisergeichyk.entity.Role;
import by.andreisergeichyk.exception.UsernameExistsException;
import by.andreisergeichyk.service.RoleService;
import by.andreisergeichyk.service.UserService;
import by.andreisergeichyk.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {
    private RoleService roleService;
    private UserService userService;
    private AccountValidator accountValidator;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(RoleService roleService, UserService userService, AccountValidator accountValidator,
                                  PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.accountValidator = accountValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @InitBinder()
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(accountValidator);
    }

    @ModelAttribute("roles")
    public List<Role> roles(Model model) {
        return roleService.findAll();
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("account", new AccountDto());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("account") AccountDto accountDto,
                               BindingResult result, Errors err) {
        Account registered;
        if (result.hasErrors()) {
            return "registration";
        }
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        registered = createUserAccount(accountDto, result);
        if (registered == null) {
            result.rejectValue("username", "453", "account.username.is.exist.error");

            return "registration";
        }
        return "login";
    }

    private Account createUserAccount(AccountDto accountDto, BindingResult result) {
        Account registered;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (UsernameExistsException e) {
            return null;
        }

        return registered;
    }
}
