package com.sparta.onlymyproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long commentId; // 댓글 식별 번호

    private String commentContent; // 댓글의 내용
    private String commentUser; // 댓글 작성자 아이디
    private LocalDateTime commentDate; // 댓글 작성일자

    @ManyToOne // 다 대 1 관계 설정
    @JoinColumn(name = "schedule_id") // 일정의 고유번호가 저장되어있는 칼럼을 조인한다/
    private Schedule schedule; // 댓글이 작성되어야 할 일정의 아이디.

}
