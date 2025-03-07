package org.selebros.projectsite.controllers;


import lombok.RequiredArgsConstructor;
import org.selebros.projectsite.dao.interfaces.UserService;
import org.selebros.projectsite.security.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public String users(Model model) {
        List<User> users = userService.all();
        User user = new User();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "pages/users";
    }

    @PostMapping
    public String add(User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole( 'ADMIN')")
    public String getEditPage(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.findById(id).get());
        model.addAttribute("users", userService.all());
        return "pages/users";
    }




    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole( 'ADMIN')")
    public String update(User user) {
        userService.update(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/profile")
    public String userProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("user", userDetails);

        // Затем в модели можно добавить информацию, которая зависит от роли
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);

        return "profile";
    }









//    @GetMapping("/")
//    public String adminPage(Authentication authentication, Model model) {
//        if (authentication != null) {
//            String username = authentication.getName(); // Получаем имя пользователя
//            model.addAttribute("username", username);
//        }
//        return "/pages/index"; // имя HTML-шаблона
//    }
}
