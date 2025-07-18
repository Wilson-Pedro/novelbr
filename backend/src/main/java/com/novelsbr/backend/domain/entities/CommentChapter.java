package com.novelsbr.backend.domain.entities;

import java.time.LocalDateTime;

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.enums.CommentType;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_COMMENT_CHAPTER")
public class CommentChapter extends Comment {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "chapter_id")
	private Chapter chapter;

	public CommentChapter() {
		super();
	}

	public CommentChapter(CommentDTO commentDTO) {
		super(commentDTO);
	}

	public CommentChapter(Long id, Author author, CommentType commentType, String text,
			LocalDateTime dateRegistration, Chapter chapter) {
		super(id, author, commentType, text, dateRegistration);
		this.chapter = chapter;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	
	@Override
	public Long getEntityId() {
		return chapter.getId();
	}
}
