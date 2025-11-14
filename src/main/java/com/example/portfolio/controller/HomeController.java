package com.example.portfolio.controller;

import com.example.portfolio.model.Profile;
import com.example.portfolio.model.Project;
import com.example.portfolio.model.Skill;
import com.example.portfolio.service.ProfileService;
import com.example.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("profile", profileService.getProfile());
        model.addAttribute("skills", profileService.getAllSkills());
        model.addAttribute("projects", projectService.getRecentProjects(3));
        model.addAttribute("skillCategories", profileService.getSkillCategories());

        return "index";
    }
}
