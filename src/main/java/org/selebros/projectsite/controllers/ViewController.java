package org.selebros.projectsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ViewController {
    @GetMapping("/departments")
    public String departments() {
        return "pages/departments";
    }

    @GetMapping("/employees")
    public String employees() {
        return "pages/employees";
    }

    @GetMapping("/test")
    public String test() {
        return "pages/test";
    }

    @GetMapping("/error")
    public String error() {
        return "pages/error";
    }
}
