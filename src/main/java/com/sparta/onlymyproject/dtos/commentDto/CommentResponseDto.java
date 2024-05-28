package com.sparta.onlymyproject.dtos.commentDto;

import com.sparta.onlymyproject.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private String commentContent;
    private String commentUser;
    private LocalDateTime commentDate;

    // Comment 를 인자로 받는 생성자 생성
    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.commentContent = comment.getCommentContent();
        this.commentUser = comment.getCommentUser();
        this.commentDate = LocalDateTime.now();
    }
}
