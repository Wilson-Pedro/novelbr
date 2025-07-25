package com.novelsbr.backend.domain.entities;

import java.time.LocalDateTime;

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.enums.CommentBy;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_COMMENT_NOVEL")
public class CommentNovel extends Comment {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "novel_id")
	private Novel novel;

	public CommentNovel() {
		super();
	}
	
	public CommentNovel(CommentDTO commentDTO) {
		super(commentDTO);
	}

	public CommentNovel(Long id, Author author, CommentBy commentBy, String text, LocalDateTime dateRegistration, Novel novel) {
		super(id, author, commentBy, text, dateRegistration);
		this.novel = novel;
	}

	public Novel getNovel() {
		return novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}
	
	@Override
	public Long getEntityId() {
		return novel.getId();
	}
}
