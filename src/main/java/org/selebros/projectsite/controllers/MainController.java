package org.selebros.projectsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "/pages/login";
    }

      @GetMapping
    public String index() {
        return "/pages/index";
    }


}
