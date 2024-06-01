package com.sparta.onlymyproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

// 회원가입과 로그인에 사용하 유저 엔티티 클래스
@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    // 사용자명의 길이 조건
    @Length(min = 4, max = 10)
    @Column(unique = true, nullable = false)
    private String username;

    @Length(min = 8, max = 15)
    @Column(nullable = false)
    private String password;
    // 사용자 권한
    private String authority;

    // 사용자 이름과 비밀번호 사용을 위한 Setter
    public void setUserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
