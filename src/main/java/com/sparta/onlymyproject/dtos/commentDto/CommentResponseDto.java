package com.sparta.onlymyproject.dtos.commentDto;

import com.sparta.onlymyproject.entity.Comment;
import com.sparta.onlymyproject.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    // 일정의 번호도 표시하기 위해서 scheduleId 필드 생성
    private Long scheduleId;

    private Long commentId;
    private String commentContent;
    private String commentUser;
    private LocalDateTime commentDate;

    // Comment 를 인자로 받는 생성자 생성
    public CommentResponseDto(Comment comment) {
        // 일정의 번호도 출력하기 위해 comment 엔티티에 schedule 엔티티로 넘어간 다음 일정의 id를 가져온다.
        this.scheduleId = comment.getSchedule().getId();

        this.commentId = comment.getCommentId();
        this.commentContent = comment.getCommentContent();
        this.commentUser = comment.getCommentUser();
        this.commentDate = LocalDateTime.now();
    }
}
