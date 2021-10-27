package com.howmath.howmath.domain.problem.service;

import com.howmath.howmath.domain.problem.dto.ProblemForTestDto;
import com.howmath.howmath.domain.problem.model.Problem;
import com.howmath.howmath.domain.problem.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PretestService {
    private final int PRETEST_AMOUNT = 1; // 몇개의 태그를 랜덤으로 가져올 것인지.
    private final int PROBLEM_PER_TAG = 2; // 태그당 몇개의 진단고사 문제를 가져올 것인가?
    private final int NUMBER_OF_TAG = 5;

    private final ProblemRepository problemRepository;

    // PRETEST_AMOUNT 개 만큼의 태그번호를 가져와서, 각 태그당 PROBLEM_PER_TAG 개 만큼의 문제를 가져온다.
    public ArrayList<ProblemForTestDto> getRandomProblem() {
        ArrayList<ProblemForTestDto> problemList = new ArrayList<>();
        List<Integer> randomTagNumber = generateRandomTagNum(PRETEST_AMOUNT);

        for (int tag : randomTagNumber) {
            List<Problem> problemListByTag = problemRepository.findAllByTagNumber(tag);
            for (int i = 0; i < PROBLEM_PER_TAG; i++) {
                // 그냥 리스트에 0,1 을 가져오는게 아니라, 랜덤으로 가져오는게 더 괜찮을 거 같은데.
                problemList.add(new ProblemForTestDto(problemListByTag.get(i)));
            }
        }
        return problemList;
    }

    // 1~10까지의 태그 번호중에 랜덤으로 amount개만 꺼내주기
    private List<Integer> generateRandomTagNum(int amount) {
        ArrayList<Integer> randomValueList = new ArrayList<>();

        while (randomValueList.size() != amount) { // amount개가 채워질때까지
            int randomValue = (int) (Math.random() * NUMBER_OF_TAG + 1);
            if (!randomValueList.contains(randomValue)) { // 리스트에 안들어있으면 넣는다.
                randomValueList.add(randomValue);
            }
        }
        return randomValueList;
    }

    // 답 맞추기
    public List<Integer> checkAnswerByListAndInputAnswer(List<Integer> problemIdList, List<String> inputAnswer, long userId) {
        List<Integer> answerResult = new ArrayList<>();
        for (int i = 0; i < problemIdList.size(); i++) {
            if (isCorrect(problemIdList.get(i), inputAnswer.get(i))) {
                answerResult.add(1);
            } else {
                answerResult.add(0);
            }
        }
        return answerResult;
    }

    // 문제 ID와 사용자가 입력한 답을 받아서, 답이 맞는지 여부를 boolean 으로 리턴해줍니다.
    private boolean isCorrect(long problemId, String answer) {
        Problem problem = problemRepository.findById(problemId).orElseThrow(IllegalArgumentException::new);
        return problem.getAnswer().equals(answer);
    }

}
