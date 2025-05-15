package com.example.todoproject.domain.comment.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.todoproject.domain.comment.dto.request.CommentCreateRequest;
import com.example.todoproject.domain.comment.dto.request.CommentUpdateRequest;
import com.example.todoproject.domain.comment.dto.response.CommentResponse;
import com.example.todoproject.domain.comment.entity.Comment;
import com.example.todoproject.domain.comment.repository.CommentRepository;
import com.example.todoproject.domain.schedule.entity.Schedule;
import com.example.todoproject.domain.schedule.repository.ScheduleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final ScheduleRepository scheduleRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public CommentResponse createComment(Long scheduleId, CommentCreateRequest request) {
		Schedule schedule = getScheduleOrThrow(scheduleId);
		Comment comment = Comment.builder()
			.schedule(schedule)
			.comment(request.getComment())
			.writerId(request.getWriterId())
			.build();
		Comment save = commentRepository.save(comment);
		return CommentResponse.of(save);
	}

	@Transactional
	public List<CommentResponse> findComment(Long scheduleId) {
		getScheduleOrThrow(scheduleId);
		List<Comment> commentList = commentRepository.findByScheduleIdAndParentCommentIsNull(scheduleId);
		return commentList.stream().map(CommentResponse::of).toList();

	}

	@Transactional
	public CommentResponse updateComment(Long scheduleId, Long commentId, CommentUpdateRequest request) {
		Comment comment = getCommentOrThrow(commentId);
		if (!comment.getSchedule().getId().equals(scheduleId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. ID : " + scheduleId);
		}
		comment.updateComment(request);
		return CommentResponse.of(comment);
	}

	@Transactional
	public void deleteComment(Long scheduleId, Long commentId) {
		Comment comment = getCommentOrThrow(commentId);
		if (!comment.getSchedule().getId().equals(scheduleId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. ID : " + scheduleId);
		}
		commentRepository.delete(comment);
	}

	private Schedule getScheduleOrThrow(Long scheduleId) {
		return scheduleRepository.findById(scheduleId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. ID : " + scheduleId));
	}

	private Comment getCommentOrThrow(Long commentId) {
		return commentRepository.findById(commentId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. ID : " + commentId));
	}
}
