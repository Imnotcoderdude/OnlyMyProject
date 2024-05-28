package com.sparta.onlymyproject.dtos.scheduleDto;

import com.sparta.onlymyproject.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequestDto {
    // RequestDto 타입이기 때문에 입력받을 값만 배치하는 것이 맞는가?

    private String scheduleName; // 일정 제목
    private String scheduleContent; // 일정 내용
    private String scheduleUser; // 일정 작성자
    private String schedulePassword; // 일정 비밀번호


    public Schedule toEntity() {
        return new Schedule(this);
    }
}
