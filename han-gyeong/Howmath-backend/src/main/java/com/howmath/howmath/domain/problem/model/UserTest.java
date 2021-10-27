package com.howmath.howmath.domain.problem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usertest")
public class UserTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false)
    private Long testerId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long difficulty;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private int elapsedTime;
}
