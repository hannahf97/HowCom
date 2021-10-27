package com.howmath.howmath.domain.problem.service;

import com.howmath.howmath.domain.problem.dto.ProblemAddDto;
import com.howmath.howmath.domain.problem.dto.ProblemForTestDto;
import com.howmath.howmath.domain.problem.model.Problem;
import com.howmath.howmath.domain.problem.repository.ProblemRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProblemService {
    private final double STANDARD_RANGE = 1.0;

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    /**
     * CREATE, 문제 추가하기
     */
    public Problem addProblem(ProblemAddDto problemAddDto) {
        Problem problem = new Problem(problemAddDto);
        problemRepository.save(problem);
        return problem;
    }


    /**
     * READ, 문제 불러오기
     */
    // 태그 번호로만 이용해서 불러오기
    public List<Problem> findByTag(int tagNumber) {
        return problemRepository.findAllByTagNumber(tagNumber);
    }

    // 문제 ID를 이용해서 불러오기
    public Problem findById(Long id) {
        return problemRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    // 문제 태그와 난이도를 이용해서 불러오기(범위 입력값 - STANDARD_RANGE ~ 입력값 + STANDARD_RANGE)
    public List<Problem> findByTagAndOneDiff(double diff, int tag) {
        double startDiff = diff - STANDARD_RANGE;
        double endDiff = diff + STANDARD_RANGE;
        return problemRepository.findByDifficultyBetweenAndTagNumber(startDiff, endDiff, tag);
    }

    // 문제 태그와 난이도를 이용해서 불러오기(시작 범위와 종료 범위를 직접 설정)
    public List<Problem> findByTagAndDiff(double startDiff, double endDiff, int tag) {
        return problemRepository.findByDifficultyBetweenAndTagNumber(startDiff, endDiff, tag);
    }

    /**
     * UPDATE, 문제 업데이트하기
     */


    /**
     * DELETE, 문제 삭제하기
     */


}
