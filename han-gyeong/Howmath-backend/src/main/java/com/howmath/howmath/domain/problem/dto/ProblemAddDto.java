package com.howmath.howmath.domain.problem.dto;

import lombok.Data;

@Data
public class ProblemAddDto {
    private String question;
    private int grade;
    private String answer;
    private int tagNumber;
    private double difficulty;
    private double discrimination;
    private double guess;
}
