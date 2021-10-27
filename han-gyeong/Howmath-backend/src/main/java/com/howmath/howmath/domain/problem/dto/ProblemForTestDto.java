package com.howmath.howmath.domain.problem.dto;

import com.howmath.howmath.domain.problem.model.Problem;
import lombok.Data;

@Data
public class ProblemForTestDto {
    public Long id;
    public String question;

    public ProblemForTestDto(Problem problem) {
        this.id = problem.getId();
        this.question = problem.getQuestion();
    }
}
