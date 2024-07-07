package com.wilsonpedro.novelbr.dto;

import java.io.Serializable;

import com.wilsonpedro.novelbr.entities.Chapter;

public class ChapterDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String chapterTilte;
	
	private Integer chapterNumber;
	
	private String text;
	
	private Long novelId;
	
	public ChapterDTO() {
	}
	
	public ChapterDTO(Long id, String chapterTilte, Integer chapterNumber, String text, Long novelId) {
		this.id = id;
		this.chapterTilte = chapterTilte;
		this.chapterNumber = chapterNumber;
		this.text = text;
		this.novelId = novelId;
	}
	
	public ChapterDTO(Chapter chapter) {
		this.id = chapter.getId();
		this.chapterTilte = chapter.getChapterTilte();
		this.chapterNumber = chapter.getChapterNumber();
		this.text = chapter.getText();
		this.novelId = chapter.getNovel().getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChapterTilte() {
		return chapterTilte;
	}

	public void setChapterTilte(String chapterTilte) {
		this.chapterTilte = chapterTilte;
	}

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(Integer chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getNovelId() {
		return novelId;
	}

	public void setNovelId(Long novelId) {
		this.novelId = novelId;
	}
	
	
}
