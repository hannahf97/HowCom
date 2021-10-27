package com.howmath.howmath.domain.account.model;

import com.howmath.howmath.domain.account.dto.UserRegisterDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {

    public User(String username, String password, String email, int grade, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.grade = grade;
        this.phone = phone;
    }

    public User(UserRegisterDto userRegisterDto) {
        this.username = userRegisterDto.getUsername();
        this.password = userRegisterDto.getPassword();
        this.phone = userRegisterDto.getPhone();
        this.grade = userRegisterDto.getGrade();
        this.email = userRegisterDto.getEmail();
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int grade;

    @Column(nullable = false)
    private String phone;
}
