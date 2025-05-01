package com.example.todoproject.domain.schedule.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoproject.domain.schedule.dto.request.CreateScheduleRequest;
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
	public ResponseEntity<ScheduleResponse> createSchedule(@Valid @RequestBody CreateScheduleRequest request) {
		ScheduleResponse boardResponse = scheduleService.saveBaord(request);
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

}
