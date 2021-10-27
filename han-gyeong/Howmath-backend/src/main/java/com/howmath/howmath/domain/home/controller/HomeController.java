package com.howmath.howmath.domain.home.controller;

import com.howmath.howmath.domain.account.model.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("loginStatus", true);
        } else {
            model.addAttribute("loginStatus", false);
        }
        return "index";
    }
}