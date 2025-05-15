package com.example.todoproject.domain.comment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoproject.domain.comment.dto.request.CommentCreateRequest;
import com.example.todoproject.domain.comment.dto.request.CommentUpdateRequest;
import com.example.todoproject.domain.comment.dto.response.CommentResponse;
import com.example.todoproject.domain.comment.service.CommentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedule/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping
	public ResponseEntity<CommentResponse> createComment(
		@PathVariable Long scheduleId,
		@Valid @RequestBody CommentCreateRequest request
	) {
		CommentResponse response = commentService.createComment(scheduleId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<CommentResponse>> getComment(
		@PathVariable Long scheduleId
	) {
		List<CommentResponse> response = commentService.findComment(scheduleId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<CommentResponse> updateComment(
		@PathVariable Long scheduleId,
		@PathVariable Long commentId,
		@Valid @RequestBody CommentUpdateRequest request
	) {
		CommentResponse response = commentService.updateComment(scheduleId, commentId, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteComment(
		@PathVariable Long scheduleId,
		@PathVariable Long commentId
	) {
		commentService.deleteComment(scheduleId, commentId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
