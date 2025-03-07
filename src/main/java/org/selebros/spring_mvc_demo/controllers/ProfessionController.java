package org.selebros.spring_mvc_demo.controllers;

import lombok.RequiredArgsConstructor;
import org.selebros.spring_mvc_demo.dao.interfaces.ProfessionsService;
import org.selebros.spring_mvc_demo.model.entities.Profession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/professions")
@RequiredArgsConstructor
public class ProfessionController {
    private final ProfessionsService profService;

    @GetMapping
    public String professions(Model model) {
        List<Profession> professions = profService.all();
        Profession profession = new Profession();
        model.addAttribute("professions", professions);
        model.addAttribute("profession", profession);
        return "pages/professions";
    }

    @PostMapping
    public String add(Profession profession) {
        profService.save(profession);
        return "redirect:/admin/professions";
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String getEditPage(Model model, @PathVariable int id) {
        model.addAttribute("profession", profService.findById(id).get());
        model.addAttribute("professions", profService.all());
        return "pages/professions";
    }





    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String update(Profession profession) {
        profService.update(profession);
        return "redirect:/admin/professions";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        profService.deleteById(id);
        return "redirect:/admin/professions";
    }
}
