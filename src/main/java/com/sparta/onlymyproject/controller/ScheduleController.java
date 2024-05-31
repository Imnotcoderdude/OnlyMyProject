package com.sparta.onlymyproject.controller;

import com.sparta.onlymyproject.dtos.scheduleDto.ScheduleRequestDto;
import com.sparta.onlymyproject.dtos.scheduleDto.ScheduleResponseDto;
import com.sparta.onlymyproject.entity.Schedule;
import com.sparta.onlymyproject.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정을 입력받고 반환하는 api
    // 파라미터 안에 requestDto 안에는 클라이언트에게서 받아온 데이터가 들어았을 것이다.
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> addSchedule(@Valid @RequestBody ScheduleRequestDto requestDto) {
        // 엔티티 객체를 생성해서 사용한다. 데이터 베이스에 저장하기 위해 데이터 베이스와 직접 통신하는 entity 클래스로 변환을 시켜주는 것이다.
        Schedule schedule = scheduleService.addSchedule(requestDto.toEntity());
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        return ResponseEntity.ok().body(responseDto);
    }

    // 클라이언트가 지정한 일정을 조회하는 api
    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    // 모든 일정을 한방에 내보냄.
    @GetMapping("/schedules")
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // 선택한 일정을 수정하는 메서드
    @PutMapping("/schedules/{id}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id,requestDto);
    }

    // 선택한 일정을 삭제하는 api
    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.delete(id, requestDto);
    }

}
