package com.sparta.onlymyproject.controller;

import com.sparta.onlymyproject.dtos.ScheduleRequestDto;
import com.sparta.onlymyproject.dtos.ScheduleResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> addSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // TODO : 일정을 입력받고 반환하는 api 작성하기
        return null;
    }

    @GetMapping("/show")
    public ResponseEntity<ScheduleResponseDto> getSchedule() {
        // TODO : 선택한 일정을 조회하는 api 작성하기
        return null;
    }

    @GetMapping("/show/all")
    public ResponseEntity<ScheduleResponseDto> getAllSchedule() {
        // TODO : 모든 일정 내역을 조회하는 api 작성하기
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> setSchedule(@RequestBody ScheduleRequestDto requestDto, @PathVariable int id) {
        // TODO : 선택한 일정 수정하는 api 작성하기
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable int id) {
        // TODO : 일정의 id를 사용하여 선택한 일정을 삭제하는 api 작성하기
        return null;
    }
}
