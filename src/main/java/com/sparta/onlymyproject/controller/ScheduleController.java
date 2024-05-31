package com.sparta.onlymyproject.controller;

import com.sparta.onlymyproject.dtos.scheduleDto.ScheduleRequestDto;
import com.sparta.onlymyproject.dtos.scheduleDto.ScheduleResponseDto;
import com.sparta.onlymyproject.entity.Schedule;
import com.sparta.onlymyproject.entity.UserRoleEnum;
import com.sparta.onlymyproject.jwt.JwtUtil;
import com.sparta.onlymyproject.service.ScheduleService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ScheduleController {

    private final JwtUtil jwtUtil;

    private final ScheduleService scheduleService;

    // 일정을 입력받고 반환하는 api
    // 파라미터 안에 requestDto 안에는 클라이언트에게서 받아온 데이터가 들어았을 것이다.
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> addSchedule(@RequestBody ScheduleRequestDto requestDto) {
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
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id,requestDto);
    }

    // 선택한 일정을 삭제하는 api
    @DeleteMapping("/schedules/{id}")
    public void deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.delete(id, requestDto);
    }

    // JWT 토큰 생성
    @GetMapping("/create-jwt")
    public String createJwt(HttpServletResponse res) {
        // Jwt 생성
        String token = jwtUtil.createToken("조규성", UserRoleEnum.USER);

        // Jwt 쿠키 저장
        jwtUtil.addJwtToCookie(token, res);

        return "createJwt : " + token;
    }

    // JWT 토큰 검증
    @GetMapping("/get-jwt")
    public String getJwt(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }

        // 토큰에서 사용자 정보 가져오기
        Claims info = jwtUtil.getUserInfoFromToken(token);
        // 사용자 username
        String username = info.getSubject();
        System.out.println("username = " + username);
        // 사용자 권한
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        System.out.println("authority = " + authority);

        return "getJwt : " + username + ", " + authority;
    }

}
