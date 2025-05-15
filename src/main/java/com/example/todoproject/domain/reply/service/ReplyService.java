package com.example.todoproject.domain.reply.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.todoproject.domain.comment.dto.response.CommentResponse;
import com.example.todoproject.domain.comment.entity.Comment;
import com.example.todoproject.domain.reply.dto.request.ReplyCreateRequest;
import com.example.todoproject.domain.reply.repository.ReplyRepository;
import com.example.todoproject.domain.schedule.entity.Schedule;
import com.example.todoproject.domain.schedule.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyService {

	private final ScheduleRepository scheduleRepository;
	private final ReplyRepository replyRepository;

	@Transactional
	public CommentResponse createReply(Long scheduleId, Long commentId, ReplyCreateRequest request) {
		Schedule schedule = scheduleRepository.findById(scheduleId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. ID: " + scheduleId));
		Comment parentComment = replyRepository.findById(commentId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. ID: " + commentId));

		Comment reply = Comment.builder()
			.writerId(request.getWriterId())
			.comment(request.getComment())
			.schedule(schedule)
			.parentComment(parentComment)
			.build();

		replyRepository.save(reply);
		return CommentResponse.of(reply);
	}

	@Transactional
	public void deleteReply(Long scheduleId, Long commentId, Long replyId) {
		Comment reply = replyRepository.findById(replyId)
			.orElseThrow(() -> new RuntimeException("Comment not found"));
		if (reply.getParentComment() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 댓글은 대댓글이 아닙니다.");
		}
		if (!reply.getSchedule().getId().equals(scheduleId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "대댓글이 해당 일정에 속하지 않았습니다.");
		}
		if (!reply.getParentComment().getId().equals(commentId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "대댓글이 해당 댓글의 하위 댓글이 아닙니다.");
		}
		replyRepository.delete(reply);
	}
}
