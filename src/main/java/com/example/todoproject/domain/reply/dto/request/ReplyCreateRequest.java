package com.example.todoproject.domain.reply.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyCreateRequest {
	private String comment;
	private Long writerId;
}
