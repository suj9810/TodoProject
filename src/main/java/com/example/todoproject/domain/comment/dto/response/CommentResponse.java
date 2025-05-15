package com.example.todoproject.domain.comment.dto.response;

import java.util.List;

import com.example.todoproject.domain.comment.entity.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponse {
	private final Long id;
	private final Long writerId;
	private final String comment;
	private final List<CommentResponse> replies;

	public static CommentResponse of(Comment comment) {
		return CommentResponse.builder()
			.id(comment.getId())
			.comment(comment.getComment())
			.writerId(comment.getWriterId())
			.replies(comment.getReplies().stream().map(CommentResponse::of).toList())
			.build();
	}
}
