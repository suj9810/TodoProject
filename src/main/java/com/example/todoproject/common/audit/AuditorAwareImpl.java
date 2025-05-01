package com.example.todoproject.common.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// 실제 프로젝트에서는 로그인 사용자 ID 반환
		return Optional.of("anonymousUser"); // 임시 사용자 ID
	}
}
