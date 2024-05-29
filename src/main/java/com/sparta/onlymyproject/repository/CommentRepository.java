package com.sparta.onlymyproject.repository;

import com.sparta.onlymyproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 입력된 일정 id에 따라서 그에 해당하는 댓글을 찾아 리스트로 반환함.
    List<Comment> findByScheduleId(Long scheduleId);
}
