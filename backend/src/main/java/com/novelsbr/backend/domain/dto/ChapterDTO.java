package com.novelsbr.backend.domain.dto;

import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.domain.entities.Novel;

public class ChapterDTO {

	private Long id;
	
	private String title;
	
	private String chapterText;
	
	private Novel novel;
	
	
	public ChapterDTO() {
	}
	
	public ChapterDTO(Chapter chapter) {
		this.id = chapter.getId();
		this.title = chapter.getTitle();
		this.chapterText = chapter.getChapterText();
		this.novel = chapter.getNovel();
	}
	
	public ChapterDTO(Long id, String title, String chapterText, Novel novel) {
		this.id = id;
		this.title = title;
		this.chapterText = chapterText;
		this.novel = novel;
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

	public Novel getNovel() {
		return novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}
}
