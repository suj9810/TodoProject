// package com.example.todoproject.common.audit;
//
// import java.util.Optional;
//
// import org.springframework.data.domain.AuditorAware;
// import org.springframework.security.core.context.SecurityContext;
// import org.springframework.security.core.context.SecurityContextHolder;
//
// public class AuditorAwareImpl implements AuditorAware<Long> {
//
// 	@Override
// 	public Optional<Long> getCurrentAuditor() {
//
// 		SecurityContext securityContext = SecurityContextHolder.getContext();
// 		if (securityContext.getAuthentication() != null && securityContext.getAuthentication().getPrincipal() instanceof Long) {
// 			Long userId = (Long) securityContext.getAuthentication().getPrincipal();
// 			return Optional.of(userId);
// 		}
// 		return Optional.of(-1L);
// 	}
// }
