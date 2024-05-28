package com.sparta.onlymyproject.entity;

import com.sparta.onlymyproject.dtos.ScheduleRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    // entity 사용을 위한 Id 설정.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scheduleName; // 일정 제목
    private String scheduleContent; // 일정 내용
    private String scheduleUser; // 일정 작성자
    private String schedulePassword; // 일정 비밀번호
    private LocalDateTime createdAt; // 일정 작성일자


    // 클라이언트와 통신해서 받아온 데이터를 entity 에 넣어주기 위해서 생성자를 만들어서 사용한다.
    // Schedule 클래스의 toEntity 메서드에서 리턴 파라미터 안에 this를 넣어서 모든 필드를 반환하도록 한다.
    public Schedule(ScheduleRequestDto requestDto) {
        this.scheduleName = requestDto.getScheduleName();
        this.scheduleContent = requestDto.getScheduleContent();
        this.scheduleUser = requestDto.getScheduleUser();
        this.schedulePassword = requestDto.getSchedulePassword();

    }

    public Schedule(String scheduleName, String scheduleContent, String scheduleUser, String schedulePassword) {
        this.scheduleName = scheduleName;
        this.scheduleContent = scheduleContent;
        this.scheduleUser = scheduleUser;
        this.schedulePassword = schedulePassword;
    }
}
