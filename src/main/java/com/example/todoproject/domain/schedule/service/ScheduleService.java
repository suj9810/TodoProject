package com.example.todoproject.domain.schedule.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todoproject.domain.schedule.dto.request.CreateScheduleRequest;
import com.example.todoproject.domain.schedule.dto.response.ScheduleResponse;
import com.example.todoproject.domain.schedule.entity.Schedule;
import com.example.todoproject.domain.schedule.repository.ScheduleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;

	@Transactional
	public ScheduleResponse saveBaord(CreateScheduleRequest request) {
		Schedule schedule = Schedule.builder()
			.title(request.getTitle())
			.content(request.getContent())
			.build();
		scheduleRepository.save(schedule);
		return ScheduleResponse.of(schedule);
	}

	@Transactional
	public List<ScheduleResponse> findAll() {
		return scheduleRepository.findAll().stream().map(ScheduleResponse::of).toList();
	}
}
