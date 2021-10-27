package com.howmath.howmath.domain.account.controller;

import com.howmath.howmath.domain.account.dto.UserRegisterDto;
import com.howmath.howmath.domain.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     로그인
     */
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login/failure")
    public String loginErrorHandler(Model model) {
        model.addAttribute("loginFail", true);
        return "login";
    }

    /*
     회원가입
     */
    @GetMapping("/register")
    public String showRegister() {
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterDto userRegisterDto, Errors errors) {
        if (errors.hasErrors()) {
            return "signup";
        }

        userService.registerUser(userRegisterDto);
        return "redirect:/";
    }
}
