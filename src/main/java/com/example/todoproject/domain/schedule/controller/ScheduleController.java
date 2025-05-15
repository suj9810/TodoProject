package com.example.todoproject.domain.schedule.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoproject.domain.schedule.dto.request.ScheduleCreateRequest;
import com.example.todoproject.domain.schedule.dto.request.ScheduleUpdateRequest;
import com.example.todoproject.domain.schedule.dto.response.ScheduleResponse;
import com.example.todoproject.domain.schedule.service.ScheduleService;

import lombok.RequiredArgsConstructor;

/**
 * 스케줄 Controller
 */
@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;

	/**
	 * 스케줄 생성
	 *
	 * @param request the request 
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleCreateRequest request) {
		ScheduleResponse boardResponse = scheduleService.createSchedule(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(boardResponse);
	}

	/**
	 * 스케줄 전체 조회
	 *
	 * @return the schedule
	 */
	@GetMapping
	public ResponseEntity<List<ScheduleResponse>> getSchedule() {
		List<ScheduleResponse> scheduleResponses = scheduleService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(scheduleResponses);
	}

	/**A
	 * 스케줄 단건 조회
	 *
	 * @param id the id
	 * @return the schedule by id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ScheduleResponse> getScheduleById(@Valid @PathVariable Long id) {
		ScheduleResponse scheduleResponse = scheduleService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
	}

	/**
	 * 스케줄 수정
	 *
	 * @param id the id 
	 * @param request the request 
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ScheduleResponse> updateScheduleById(
		@Valid @PathVariable Long id,
		@RequestBody ScheduleUpdateRequest request) {
		ScheduleResponse scheduleResponse = scheduleService.updateSchedule(id,request);
		return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
	}

	/**
	 * 스케줄 삭제
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSchedule(
		@PathVariable Long id
	) {
		scheduleService.deleteSchedule(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
