package com.example.todoproject.domain.schedule.dto.response;

import java.util.List;

import com.example.todoproject.domain.comment.dto.response.CommentResponse;
import com.example.todoproject.domain.schedule.entity.Schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ScheduleResponse {
	private final Long id;
	private final String title;
	private final String content;
	private final List<CommentResponse> comments;
	private final Long commentCount;

	public static ScheduleResponse of(Schedule schedule) {
		List<CommentResponse> comments = schedule.getComments().stream()
			.map(CommentResponse::of)
			.toList();

		return ScheduleResponse.builder()
			.id(schedule.getId())
			.title(schedule.getTitle())
			.content(schedule.getContent())
			.comments(comments)
			.commentCount((long) schedule.getComments().size())
			.build();
	}
}
