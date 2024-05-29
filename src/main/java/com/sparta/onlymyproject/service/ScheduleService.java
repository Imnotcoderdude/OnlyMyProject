package com.sparta.onlymyproject.service;

import com.sparta.onlymyproject.dtos.scheduleDto.ScheduleRequestDto;
import com.sparta.onlymyproject.dtos.scheduleDto.ScheduleResponseDto;
import com.sparta.onlymyproject.entity.Schedule;
import com.sparta.onlymyproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    // ScheduleRepository 인터페이스를 사용하기 위해서 필드 선언
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 클라이언트가 입력한 일정을 저장하는 메서드
    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // 클라이언트가 요청한 id 값에 일치하는 일정 내보내기.
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다. id=" + id));
        return new ScheduleResponseDto(schedule);
    }

    // 클라이언트가 요청하면 가지고 있는 모든 일정 내보내기
    // 모든 일정은 List 컬랙션에 담아서 내보낸다.
    public List<ScheduleResponseDto> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    // 선택한 일정을 수정하는 메서드
    // 일정의 id 값으로 접근해서 내용을 json 으로 수정해서 입력하기?
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        // entity 객체를 생성해서 scheduleRepository 안에 있는 id 를 findById 를 사용해서 가져오고 만약 요청받은 id가 없다면 orElseThrow 메서드로 예외처리하기
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 일정이 없습니다. id = " + id));
        // 비밀번호를 확인하는 조건문
        if (!schedule.getSchedulePassword().equals(scheduleRequestDto.getSchedulePassword())){
           throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        schedule.setScheduleName(scheduleRequestDto.getScheduleName());
        schedule.setScheduleContent(scheduleRequestDto.getScheduleContent());
        schedule.setScheduleUser(scheduleRequestDto.getScheduleUser());

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule);
    }

    // 선택한 일정을 삭제하는 메서드
    // Url 에서 id 값을 받고 body에서 비밀번호를 받아서 검증하고 삭제함
    public void deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 일정이 없습니다. id = " + id));
        if (!schedule.getSchedulePassword().equals(scheduleRequestDto.getSchedulePassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
