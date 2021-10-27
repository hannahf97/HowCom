package com.howmath.howmath.domain.problem.controller;

import com.howmath.howmath.domain.problem.dto.ProblemAddDto;
import com.howmath.howmath.global.response.Message;
import com.howmath.howmath.domain.problem.model.Problem;
import com.howmath.howmath.domain.problem.service.ProblemService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/problem")
@RestController
public class ProblemController {
    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    /**
     * CREATE, 문제 추가하기
     */
    // 문제를 추가하기
    @GetMapping("/add")
    public String showAddProblemPage() {
        return "problemAdding";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Problem addProblem(@RequestBody ProblemAddDto problemAddDto) {
        return problemService.addProblem(problemAddDto);
    }

    @PostMapping
    public Problem addProblem2(@RequestBody ProblemAddDto problemAddDto) {
        return problemService.addProblem(problemAddDto);
    }

    /**
     * READ, 문제 가져오기
     */
    // ID로 문제 가져오기
    @GetMapping(params = "id")
    public ResponseEntity<Message> findById(@RequestParam Long id) {
        Problem problem = problemService.findById(id);
        return ResponseEntity.ok(Message.getDefaultOkMessage(problem));
    }

    // 태그번호로 문제 가져오기
    @GetMapping(params = "tag")
    public ResponseEntity<Message> findByTag(@RequestParam int tag) {
        List<Problem> problems = problemService.findByTag(tag);
        if (problems.isEmpty()) {
//            return new ResponseEntity<>(Message.getDefaultNotFoundMessage(), HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
//        return new ResponseEntity<>(Message.getDefaultOkMessage(problems), HttpStatus.OK);
        return ResponseEntity.ok(Message.getDefaultOkMessage(problems));
    }

    // 태그번호와 난이도를 기준으로 문제 가져오기, 범위는 상수에 의해 설정된다.
    @GetMapping(params = {"tag", "diff"})
    public ResponseEntity<Message> findByTagAndDiff(@RequestParam int tag, @RequestParam double diff) {
        List<Problem> result = problemService.findByTagAndOneDiff(diff, tag);
//        return new ResponseEntity<>(Message.getDefaultOkMessage(result), HttpStatus.OK);
        return ResponseEntity.ok(Message.getDefaultOkMessage(result));
    }

    // 태그번호와 난이도를 기준으로 문제 가져오기, 시작 범위와 종료 범위를 직접 설정할 수 있다.
    @GetMapping(params = {"tag", "startDiff", "endDiff"})
    public ResponseEntity<Message> findByTagAndDiffRange(@RequestParam int tag, @RequestParam double startDiff, @RequestParam double endDiff) {
        List<Problem> result = problemService.findByTagAndDiff(startDiff, endDiff, tag);
//        return new ResponseEntity<>(Message.getDefaultOkMessage(result), HttpStatus.OK);
        return ResponseEntity.ok(Message.getDefaultOkMessage(result));
    }

    // 문제번호 List, 사용자 ID -> 답 List 만들어서, Repository 에 업로드
}