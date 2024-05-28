package com.sparta.onlymyproject.dtos.scheduleDto;


import com.sparta.onlymyproject.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long id; // 일정 고유 번호

    private String scheduleName; // 일정 제목
    private String scheduleContent; // 일정 내용
    private String scheduleUser; // 일정 작성자
    private String schedulePassword; // 일정 비밀번호
    private LocalDateTime createdAt; // 일정 작성일자

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.scheduleName = schedule.getScheduleName();
        this.scheduleContent = schedule.getScheduleContent();
        this.scheduleUser = schedule.getScheduleUser();
        this.schedulePassword = schedule.getSchedulePassword();
        this.createdAt = schedule.getCreatedAt();
    }
}
