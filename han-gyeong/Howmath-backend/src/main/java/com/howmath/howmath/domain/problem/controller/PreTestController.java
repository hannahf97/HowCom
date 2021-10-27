package com.howmath.howmath.domain.problem.controller;

import com.howmath.howmath.domain.problem.dto.ProblemForTestDto;
import com.howmath.howmath.domain.problem.service.PretestService;
import com.howmath.howmath.domain.account.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PreTestController {

    private final PretestService pretestService;

    @GetMapping("/pretest")
    public String showPretestPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ProblemForTestDto> problemList = pretestService.getRandomProblem();
        model.addAttribute("problemList", problemList);
        model.addAttribute("username", userDetails.getUser().getUsername()); // 사용자 이름 추가
        return "pretest";
    }

    // 답을 받을 POST
}
