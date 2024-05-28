package com.sparta.onlymyproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Comment {
    @Id
    private Long commentId;
}
