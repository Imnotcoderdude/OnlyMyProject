package com.sparta.onlymyproject.service;

import com.sparta.onlymyproject.dtos.register.RegisterRequest;
import com.sparta.onlymyproject.entity.User;
import com.sparta.onlymyproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
