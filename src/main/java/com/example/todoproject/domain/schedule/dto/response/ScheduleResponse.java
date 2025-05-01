package com.example.todoproject.domain.schedule.dto.response;

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

	public static ScheduleResponse of(Schedule schedule) {
		return ScheduleResponse.builder()
			.id(schedule.getId())
			.title(schedule.getTitle())
			.content(schedule.getContent())
			.build();
	}
}
