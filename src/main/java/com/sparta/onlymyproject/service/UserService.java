package com.sparta.onlymyproject.service;

import com.sparta.onlymyproject.dtos.register.RegisterRequest;
import com.sparta.onlymyproject.entity.User;
import com.sparta.onlymyproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUserInfo(registerRequest.getUsername(), registerRequest.getPassword());
        userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !Objects.equals(user.getPassword(),password)) {
            throw new IllegalArgumentException("사용자 이름과 비밀번호를 확인하세요");
        }
        return user;
    }
}
