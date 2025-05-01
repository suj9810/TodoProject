package com.example.todoproject.domain.comment.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.todoproject.common.audit.BaseEntity;
import com.example.todoproject.domain.schedule.entity.Schedule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	private Long writerId;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> replies = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "parent_comment_id")
	private Comment parentComment;

	@Builder
	public Comment(String content) {
		this.content = content;
	}

}
