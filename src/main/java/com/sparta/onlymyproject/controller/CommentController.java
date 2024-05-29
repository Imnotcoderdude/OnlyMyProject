package com.sparta.onlymyproject.controller;

import com.sparta.onlymyproject.dtos.commentDto.CommentRequestDto;
import com.sparta.onlymyproject.dtos.commentDto.CommentResponseDto;
import com.sparta.onlymyproject.service.CommentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Getter
@Setter
@RequestMapping("/api/schedules")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글을 생성하는 api. 댓글을 작성하고싶은 일정의 id를 입력해 그 일정에 댓글을 달 수 있게 해주는 작동방식.
    @PostMapping("/{scheduleId}/comments")
    public CommentResponseDto addComment(@PathVariable Long scheduleId, @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.addComment(scheduleId, commentRequestDto);
    }

    // 댓글을 수정하는 api
    // 과제의 조건이 댓글의 내용만 수정하는 것이기 떄문에 일부분만 수정할 수 있게 PutMapping 대신 PatchMapping 을 사용한다.
    @PatchMapping("/{scheduleId}/comments/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @Valid @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(scheduleId,commentId,requestDto);
    }

    // 요청된 일정에 대한 모든 댓글을 조회하는 api
    @GetMapping("/{scheduleId}/comments")
    public List<CommentResponseDto> getAllComments(@PathVariable Long scheduleId) {
        return commentService.getAllComment(scheduleId);
    }

}
