package com.novelsbr.backend.domain.dto;

import com.novelsbr.backend.domain.projections.ChapterTextProjection;

public class ChapterTextDTO {

	private Integer chapterNumber;
	
	private String title;
	
	private String chapterText;
	
	private String novelName;

	public ChapterTextDTO() {
	}

	public ChapterTextDTO(ChapterTextProjection projection) {
		this.chapterNumber = projection.getChapterNumber();
		this.title = projection.getTitle();
		this.chapterText = projection.getChapterText();
		this.novelName = projection.getNovelName();
	}

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getChapterText() {
		return chapterText;
	}

	public String getNovelName() {
		return novelName;
	}
}
