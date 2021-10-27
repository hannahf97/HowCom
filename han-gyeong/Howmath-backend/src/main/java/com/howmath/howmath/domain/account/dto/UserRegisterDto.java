package com.howmath.howmath.domain.account.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String email;
    private int grade;
    private String phone;
}
