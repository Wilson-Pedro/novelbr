package com.novelsbr.backend.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.novelsbr.backend.domain.projections.LastChaptersProjection;

public class LastChaptersDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long novelId;
	
	private Long authorId;
	
	private String novelName;
	
	private String username;
	
	private String title;
	
	private Integer chapterNumber;
	
	private String dateRegistration;
	
	public LastChaptersDTO() {
	}

	public LastChaptersDTO(LastChaptersProjection projection) {
		this.novelId = projection.getNovelId();
		this.authorId = projection.getAuthorId();
		this.novelName = projection.getNovelName();
		this.username = projection.getUsername();
		this.title = projection.getTitle();
		this.chapterNumber = projection.getChapterNumber();
		this.dateRegistration = formatDate(projection.getDateRegistration());
	}

	public Long getNovelId() {
		return novelId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public String getNovelName() {
		return novelName;
	}

	public String getUsername() {
		return username;
	}

	public String getTitle() {
		return title;
	}

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public String getDateRegistration() {
		return dateRegistration;
	}
	
	private String formatDate(LocalDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}
