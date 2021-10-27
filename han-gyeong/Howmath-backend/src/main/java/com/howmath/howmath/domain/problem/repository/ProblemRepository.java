package com.howmath.howmath.domain.problem.repository;

import com.howmath.howmath.domain.problem.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    List<Problem> findAllByTagNumber(int tagNumber);
    Problem getProblemById(Long id);
    List<Problem> findByDifficultyBetweenAndTagNumber(double startDiff, double endDiff, int tagNumber);
    List<Problem> findProblemsByTagNumberOrderByDifficultyDesc(int tagNumber);
}
