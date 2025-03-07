package org.selebros.spring_mvc_demo.controllers;

import org.selebros.spring_mvc_demo.dao.interfaces.UserService;
import org.selebros.spring_mvc_demo.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")

public class RegistrationController {
@Autowired
private UserService userService;
    @GetMapping
    public String registrationPage() {
        return "/pages/registration";
    }

    @PostMapping
    public String registration (Model model, String username, String password, String matchingPassword) {

        if(!password.equals(matchingPassword)) {
           model.addAttribute("error", "password");
           return "/pages/registration";
       }
        User registrUser = new User(username,password);
        if (userService.save(registrUser) == null) {
            model.addAttribute("error", "username");
            return "pages/registration";
        }
        return "redirect:/";
    }
}
