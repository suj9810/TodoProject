package com.example.todoproject.domain.schedule.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

	@Transactional
	public ScheduleResponse saveBaord(ScheduleCreateRequest request) {
		Schedule schedule = Schedule.builder()
			.title(request.getTitle())
			.content(request.getContent())
			// .writerId(request.getWriterId())
			.build();
		scheduleRepository.save(schedule);
		return ScheduleResponse.of(schedule);
	}

	@Transactional
	public List<ScheduleResponse> findAll() {
		return scheduleRepository.findAll().stream().map(ScheduleResponse::of).toList();
	}

	@Transactional
	public ScheduleResponse findById(Long id) {
		Schedule schedule = getScheduleOrThrow(id);
		return new ScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent());
	}

	@Transactional
	public ScheduleResponse updateSchedule(Long boardId, ScheduleUpdateRequest request) {
		Schedule schedule = getScheduleOrThrow(boardId);
		schedule.updateSchedule(request);
		return ScheduleResponse.of(schedule);
	}

	@Transactional
	public void deleteSchedule(Long boardId) {
		Schedule schedule = getScheduleOrThrow(boardId);
		scheduleRepository.delete(schedule);
	}

	private Schedule getScheduleOrThrow(Long scheduleId) {
		return scheduleRepository.findById(scheduleId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. ID : " + scheduleId));
	}

	// private void validateWriter(Schedule schedule, Long writerId) {
	// 	if (schedule.getCreatedBy() == null || !schedule.getCreatedBy().equals(writerId)) {
	// 		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자만 수정할 수 있습니다.");
	// 	}
	// }
}
