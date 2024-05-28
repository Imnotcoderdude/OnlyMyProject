package com.sparta.onlymyproject.repository;

import com.sparta.onlymyproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
