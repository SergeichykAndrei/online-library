package by.andreisergeichyk.controller;

import by.andreisergeichyk.dto.book.ViewFullInfoBookDto;
import by.andreisergeichyk.entity.Account;
import by.andreisergeichyk.service.AccountBookService;
import by.andreisergeichyk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserBookController {

    private static final String ADMIN = "ADMIN";

    private AccountBookService accountBookService;
    private UserService userService;

    @Autowired
    public UserBookController(AccountBookService accountBookService, UserService userService) {
        this.accountBookService = accountBookService;
        this.userService = userService;
    }

    @GetMapping("/userAccount")
    public String showUserAccount(Model model, String username) {
        Account account = userService.findByName(username);
        if (ADMIN.equals(account.getRole().getName())) {
            model.addAttribute("accountBooks", accountBookService.findAll());
        } else {
            model.addAttribute("accountBooks", accountBookService.findAllByUsername(username));
        }

        return "user-account";
    }

    @GetMapping("/userAccountSave")
    public String saveUserBookAndShowAccount(Model model, Long bookId, String username) {
        Account account = userService.findByName(username);
        if (bookId != null) {
            accountBookService.save(username, bookId);
        }
        if (ADMIN.equals(account.getRole().getName())) {
            model.addAttribute("accountBooks", accountBookService.findAll());
        } else {
            model.addAttribute("accountBooks", accountBookService.findAllByUsername(username));
        }

        return "user-account";
    }

    @GetMapping("/userAccountDelete")
    public String deleteAccountBook(Model model, String username, Long bookId) {
        Account account = userService.findByName(username);
        if (bookId != null) {
            accountBookService.delete(bookId);
        }
        if (ADMIN.equals(account.getRole().getName())) {
            model.addAttribute("accountBooks", accountBookService.findAll());
        } else {
            model.addAttribute("accountBooks", accountBookService.findAllByUsername(username));
        }

        return "user-account";
    }

    @PostMapping("/userAccountSave")
    public String saveBook(ViewFullInfoBookDto book) {
        System.out.println();
        System.out.println();
        return "user-account";
    }

}
