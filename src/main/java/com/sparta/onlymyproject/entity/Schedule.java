package com.sparta.onlymyproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Schedule {
    // entity 사용을 위한 Id 설정.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scheduleName; // 일정 제목
    private String scheduleContent; // 일정 내용
    private String scheduleUser; // 일정 작성자
    private Long schedulePassword; // 일정 비밀번호
    private LocalDateTime createdAt; // 일정 작성일자


}
