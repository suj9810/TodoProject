package com.example.todoproject.domain.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todoproject.domain.comment.entity.Comment;

@Repository
public interface ReplyRepository extends JpaRepository<Comment, Long> {
}
