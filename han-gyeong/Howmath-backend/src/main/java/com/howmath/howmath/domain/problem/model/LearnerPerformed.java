package com.howmath.howmath.domain.problem.model;

import javax.persistence.*;

// 학습자의 퍼포먼스를 저장하기 위한 모델
@Entity
public class LearnerPerformed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false)
    long problemId;

    @Column(nullable = false)
    int answer;

    @Column(nullable = false)
    long userId;
}
