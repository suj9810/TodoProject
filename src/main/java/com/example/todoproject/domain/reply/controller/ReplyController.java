package com.example.todoproject.domain.reply.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoproject.domain.comment.dto.response.CommentResponse;
import com.example.todoproject.domain.reply.dto.request.ReplyCreateRequest;
import com.example.todoproject.domain.reply.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments/{commentId}/replies")
@RequiredArgsConstructor
public class ReplyController {

	private final ReplyService replyService;

	@PostMapping
	public ResponseEntity<CommentResponse> createReply(
		@PathVariable Long scheduleId,
		@PathVariable Long commentId,
		@RequestBody ReplyCreateRequest request
	) {
		CommentResponse response = replyService.createReply(scheduleId, commentId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{replyId}")
	public ResponseEntity<Void> deleteReply(
		@PathVariable Long scheduleId,
		@PathVariable Long commentId,
		@PathVariable Long replyId
	) {
		replyService.deleteReply(scheduleId, commentId, replyId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
