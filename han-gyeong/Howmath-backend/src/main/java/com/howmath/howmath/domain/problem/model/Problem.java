package com.howmath.howmath.domain.problem.model;

import com.howmath.howmath.domain.problem.dto.ProblemAddDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "problem")
@Data
public class Problem {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private int tagNumber; // 문제가 어느 유형의 문제인지를 판단하기 위해서, 집합, 빼기 등.

    @Column(nullable = false)
    private double difficulty;

    @Column(nullable = false)
    private double discrimination;

    @Column(nullable = false)
    private double guess;

    public Problem(ProblemAddDto problemAddDto) {
        this.question = problemAddDto.getQuestion();
        this.grade = problemAddDto.getGrade();
        this.answer = problemAddDto.getAnswer();
        this.tagNumber = problemAddDto.getTagNumber();
        this.difficulty = problemAddDto.getDifficulty();
        this.discrimination = problemAddDto.getDiscrimination();
        this.guess = problemAddDto.getGuess();
    }

}
