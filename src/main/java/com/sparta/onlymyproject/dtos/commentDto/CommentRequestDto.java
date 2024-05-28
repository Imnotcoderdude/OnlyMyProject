package com.sparta.onlymyproject.dtos.commentDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private String commentContent;
    private String commentUser;
}