package jafar.task.controller;


import jafar.task.entity.AuthUser;
import jafar.task.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth/*")
public class AuthUserController {

    final UserService service;

    public AuthUserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "auth/login";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logoutPage() {
        return "auth/logout";
    }
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "auth/register";
    }
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute AuthUser user) {
        service.saveUser(user);
        return "redirect:todo/all";
    }
}
