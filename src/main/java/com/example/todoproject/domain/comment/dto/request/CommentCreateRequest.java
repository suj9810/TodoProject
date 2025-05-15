package com.example.todoproject.domain.comment.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentCreateRequest {
	private String comment;
	private Long writerId;
}
