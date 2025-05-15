package com.example.todoproject.domain.comment.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.todoproject.common.audit.BaseEntity;
import com.example.todoproject.domain.comment.dto.request.CommentUpdateRequest;
import com.example.todoproject.domain.schedule.entity.Schedule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String comment;

	@Column(nullable = false)
	private Long writerId;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	@ToString.Exclude
	private Schedule schedule;

	@OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
	@ToString.Exclude
	private List<Comment> replies = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "parent_comment_id")
	@ToString.Exclude
	private Comment parentComment;

	public void updateComment(CommentUpdateRequest request) {
		this.comment = request.getComment();
	}

	public void addReply(Comment reply) {
		this.replies.add(reply);
		reply.setParentComment(this);
	}
}
