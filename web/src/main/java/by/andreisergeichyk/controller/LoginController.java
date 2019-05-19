package by.andreisergeichyk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("currentUser")
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
