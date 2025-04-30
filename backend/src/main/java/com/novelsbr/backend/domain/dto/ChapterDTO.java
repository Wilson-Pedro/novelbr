package com.novelsbr.backend.domain.dto;

import java.io.Serializable;

import com.novelsbr.backend.domain.entities.Chapter;

public class ChapterDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String title;
	
	private String chapterText;
	
	private Long novelId;
	
	
	public ChapterDTO() {
	}
	
	public ChapterDTO(Chapter chapter) {
		this.id = chapter.getId();
		this.title = chapter.getTitle();
		this.chapterText = chapter.getChapterText();
		this.novelId = chapter.getNovel().getId();
	}
	
	public ChapterDTO(Long id, String title, String chapterText, Long novelId) {
		this.id = id;
		this.title = title;
		this.chapterText = chapterText;
		this.novelId = novelId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChapterText() {
		return chapterText;
	}

	public void setChapterText(String chapterText) {
		this.chapterText = chapterText;
	}

	public Long getNovelId() {
		return novelId;
	}

	public void setNovelId(Long novelId) {
		this.novelId = novelId;
	}
}
