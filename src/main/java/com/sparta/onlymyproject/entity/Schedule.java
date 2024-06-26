package com.sparta.onlymyproject.entity;

import com.sparta.onlymyproject.dtos.scheduleDto.ScheduleRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

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

    @NotBlank(message = "일정의 제목은 비워둘 수 없습니다.")
    private String scheduleName; // 일정 제목
    @NotBlank(message = "일정의 내용은 비워둘 수 없습니다.")
    private String scheduleContent; // 일정 내용
    @NotBlank(message = "일정의 작성자를 비워두지 마십시오.")
    private String scheduleUser; // 일정 작성자
    @NotBlank(message = "일정의 비밀번호는 비워둘 수 없습니다.")
    private String schedulePassword; // 일정 비밀번호

    @CreatedDate
    private LocalDateTime createdAt; // 날짜


    // 클라이언트와 통신해서 받아온 데이터를 entity 에 넣어주기 위해서 생성자를 만들어서 사용한다.
    // Schedule 클래스의 toEntity 메서드에서 리턴 파라미터 안에 this를 넣어서 모든 필드를 반환하도록 한다.
    public Schedule(ScheduleRequestDto requestDto) {
        this.scheduleName = requestDto.getScheduleName();
        this.scheduleContent = requestDto.getScheduleContent();
        this.scheduleUser = requestDto.getScheduleUser();
        this.schedulePassword = requestDto.getSchedulePassword();
        this.createdAt = LocalDateTime.now();
    }
}
