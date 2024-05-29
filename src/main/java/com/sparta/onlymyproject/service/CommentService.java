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
import java.util.List;
import java.util.stream.Collectors;

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
    // 요청된 일정의 id를 기반으로 id에 해당하는 일정을 모두 찾아오는 메서드
    public List<CommentResponseDto> getAllComment(Long scheduleId) {
        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    // 일정에 달린 댓글을 수정하는 메서드
    // 먼저 일정을 찾기 위해 일정 id 값을 받고, 그 id 에 해당하는 일정을 찾아왔다면 그 일정 내에 원하는 댓글을 찾기 위해 댓글 id 값을 입력하고
    // 그후 json 형식으로 수정을 요청한다.
    public CommentResponseDto updateComment(Long scheduleId, Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()
                -> new IllegalArgumentException("해당 댓글 id 는 없는 id 입니다. id = "+commentId));
        // 댓글 내용만 수정하는 것이 조건이기에 CommentContent 만 수정이 가능하게끔 setter 설정.
        comment.setCommentContent(requestDto.getCommentContent());
        // 일정에 대한 정보를 저장하고 있는 CommentRepository 를 객체화 시켜서 가져온 후 save()메서드로 변경된 사항을 저장한다.
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
