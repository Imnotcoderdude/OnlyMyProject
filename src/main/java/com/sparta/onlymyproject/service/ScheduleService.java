package com.sparta.onlymyproject.service;

import com.sparta.onlymyproject.dtos.ScheduleResponseDto;
import com.sparta.onlymyproject.entity.Schedule;
import com.sparta.onlymyproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    // ScheduleRepository 인터페이스를 사용하기 위해서 필드 선언
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 클라이언트가 입력한 일정을 저장하는 메서드
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // 클라이언트가 요청한 id 값에 일치하는 일정 내보내기.
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id=" + id));
        return new ScheduleResponseDto(schedule);
    }
}
