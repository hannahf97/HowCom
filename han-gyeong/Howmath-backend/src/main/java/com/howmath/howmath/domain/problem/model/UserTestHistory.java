package com.howmath.howmath.domain.problem.model;

import javax.persistence.*;

@Entity
@Table(name = "usertest_history")
public class UserTestHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long latestTest;

    @Column(nullable = false)
    private Long difficulty;
    // 난이도가 여기서도 필요한가?
}
