package com.howmath.howmath.domain.account.service;

import com.howmath.howmath.domain.account.dto.UserRegisterDto;
import com.howmath.howmath.domain.account.model.User;
import com.howmath.howmath.domain.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 회원가입
    public void registerUser(UserRegisterDto userRegisterDto) {
        String username = userRegisterDto.getUsername();
        if (isExistUser(username)) {
            // 추가적인 커스텀 예외를 만들자.
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        User user = new User(userRegisterDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // 사용자가 이미 존재하는지 확인하기
    public boolean isExistUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }
}