package com.example.todoproject.domain.schedule.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.todoproject.domain.comment.repository.CommentRepository;
import com.example.todoproject.domain.schedule.dto.request.ScheduleCreateRequest;
import com.example.todoproject.domain.schedule.dto.request.ScheduleUpdateRequest;
import com.example.todoproject.domain.schedule.dto.response.ScheduleResponse;
import com.example.todoproject.domain.schedule.entity.Schedule;
import com.example.todoproject.domain.schedule.repository.ScheduleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	
	// 일정 저장
	@Transactional
	public ScheduleResponse createSchedule(ScheduleCreateRequest request) {
		Schedule schedule = Schedule.builder()
			.title(request.getTitle())
			.content(request.getContent())
			.build();
		scheduleRepository.save(schedule);
		return ScheduleResponse.of(schedule);
	}
	
	// 전체 일정 조회
	@Transactional
	public List<ScheduleResponse> findAll() {
		return scheduleRepository.findAll().stream().map(ScheduleResponse::of).toList();
	}
	
	// 일정 상세 조회
	@Transactional
	public ScheduleResponse findById(Long id) {
		Schedule schedule = getScheduleOrThrow(id);
		return ScheduleResponse.of(schedule);
	}
	
	// 일정 수정
	@Transactional
	public ScheduleResponse updateSchedule(Long boardId, ScheduleUpdateRequest request) {
		Schedule schedule = getScheduleOrThrow(boardId);
		schedule.updateSchedule(request);
		return ScheduleResponse.of(schedule);
	}
	
	// 일정 삭제
	@Transactional
	public void deleteSchedule(Long boardId) {
		Schedule schedule = getScheduleOrThrow(boardId);
		scheduleRepository.delete(schedule);
	}

	private Schedule getScheduleOrThrow(Long scheduleId) {
		return scheduleRepository.findById(scheduleId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. ID : " + scheduleId));
	}
}
