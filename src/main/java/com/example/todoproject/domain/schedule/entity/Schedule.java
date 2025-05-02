package com.example.todoproject.domain.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.todoproject.common.audit.BaseEntity;
import com.example.todoproject.domain.comment.entity.Comment;
import com.example.todoproject.domain.schedule.dto.request.ScheduleUpdateRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedules")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String content;
	// @Column(nullable = false)
	// private Long writerId;

	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();

	public void updateSchedule(ScheduleUpdateRequest request) {
		this.title = request.getTitle();
		this.content = request.getContent();
	}

}
