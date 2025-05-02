package com.example.todoproject.common.exception.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	// 커스텀 예외 처리
	@ExceptionHandler(CustomBadRequestException.class)
	public ResponseEntity<?> handleCustomBadRequest(CustomBadRequestException e) {
		Map<String, Object> body = new HashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", e.getMessage());

		return ResponseEntity.badRequest().body(body);
	}

	// // 일반 Exception 처리 (기타 모든 예외)
	// @ExceptionHandler(Exception.class)
	// public ResponseEntity<?> handleException(Exception e) {
	// 	Map<String, Object> body = new HashMap<>();
	// 	body.put("timestamp", LocalDateTime.now());
	// 	body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
	// 	body.put("error", "Internal Server Error");
	// 	body.put("message", "예상치 못한 오류가 발생했습니다.");
	//
	// 	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	// }
}
