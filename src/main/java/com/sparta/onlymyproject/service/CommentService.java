package com.sparta.onlymyproject.service;

import com.sparta.onlymyproject.dtos.commentDto.CommentRequestDto;
import com.sparta.onlymyproject.dtos.commentDto.CommentResponseDto;
import com.sparta.onlymyproject.entity.Comment;
import com.sparta.onlymyproject.entity.Schedule;
import com.sparta.onlymyproject.repository.CommentRepository;
import com.sparta.onlymyproject.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // comment 의 id를 기반으로 댓글을 찾아오는 메서드
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("요청하신 댓글을 찾을 수 없습니다. id = " + id));
    }

    public CommentResponseDto addComment(Long scheduleId, CommentRequestDto commentRequestDto) {
        // scheduleRepository 에 존재하는 일정의 id를 이용하여 요청된 일정을 찾는 메서드
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("요청하신 일정을 찾을 수 없습니다."));

        // Comment entity 객체 생성
        Comment comment = new Comment();
        comment.setCommentContent(commentRequestDto.getCommentContent());
        comment.setCommentUser(commentRequestDto.getCommentUser());
        comment.setCommentDate(LocalDateTime.now());
        comment.setSchedule(schedule);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
