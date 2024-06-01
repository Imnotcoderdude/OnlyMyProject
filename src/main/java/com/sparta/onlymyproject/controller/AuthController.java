package com.sparta.onlymyproject.controller;

import com.sparta.onlymyproject.dtos.auth.AuthRequest;
import com.sparta.onlymyproject.dtos.auth.AuthResponse;
import com.sparta.onlymyproject.dtos.register.RegisterRequest;
import com.sparta.onlymyproject.dtos.register.RegisterResponse;
import com.sparta.onlymyproject.entity.User;
import com.sparta.onlymyproject.service.UserService;
import com.sparta.onlymyproject.util.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;

    // 토큰 생성
    @PostMapping("/authenticate")
    public String createToken(@RequestBody AuthRequest authRequest) throws Exception {
        if ("user".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
            return JwtTokenProvider.generateToken(authRequest.getUsername());
        } else {
            throw new Exception("유효하지 않은 자격증명");
        }
    }

    // 토큰 유효성 검사
    @GetMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String token) {
        String username = JwtTokenProvider.extractUsername(token.substring(7));
        if (JwtTokenProvider.validateToken(token.substring(7), username)) {
            return "유효한 토큰";
        } else {
            return "유효하지 않은 토큰";
        }
    }

    // 회원가입 api
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {

        userService.register(registerRequest);
        RegisterResponse registerResponse = new RegisterResponse("유저등록 성공");
        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    // 로그인 api
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        User user = userService.login(authRequest.getUsername(),authRequest.getPassword());
        String token = JwtTokenProvider.generateToken(user.getUsername());
        AuthResponse authResponse = new AuthResponse("로그인 성공", token);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}