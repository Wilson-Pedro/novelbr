package com.novelsbr.backend.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.novelsbr.backend.domain.entities.Comment;

public class CommentMinDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long authorId;
	
	private String username;
	
	private Long parentId;
	
	private String bodyText;
	
	private LocalDateTime createdAt;
	
	public CommentMinDTO() {
	}
	
	public CommentMinDTO(Comment comment) {
		this.id = comment.getId();
		this.authorId = comment.getAuthor().getId();
		this.username = comment.getAuthor().getUsername();
		this.parentId = comment.getId() == null ? null : comment.getId();
		this.bodyText = comment.getBodyText();
		this.createdAt = comment.getDateRegistration();
	}

	public CommentMinDTO(Long id, Long authorId, String username, Integer commentCode, Long entityId, String bodyText, LocalDateTime createdAt) {
		this.id = id;
		this.authorId = authorId;
		this.username = username;
		this.bodyText = bodyText;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id == null ? null : id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getParentId() {
		return parentId == null ? null : parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
