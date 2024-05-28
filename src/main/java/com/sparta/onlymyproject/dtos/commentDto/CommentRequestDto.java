package com.sparta.onlymyproject.dtos.commentDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private Long scheduleId;
    private String commentUser;

    @NotBlank(message = "댓글의 내용은 비어있을 수 없습니다.")
    // NotBlank 어노테이션으로 공백, 또는 아무것도 입력하지 않았을때, null 값 모두 차단.
    private String commentContent;

}
