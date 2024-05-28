package com.sparta.onlymyproject.controller;

import com.sparta.onlymyproject.dtos.commentDto.CommentRequestDto;
import com.sparta.onlymyproject.dtos.commentDto.CommentResponseDto;
import com.sparta.onlymyproject.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Getter
@Setter
@RequestMapping("/api/schedule")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{scheduleId}/comments")
    public CommentResponseDto addComment(@PathVariable Long scheduleId, @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.addComment(scheduleId, commentRequestDto);
    }
}
