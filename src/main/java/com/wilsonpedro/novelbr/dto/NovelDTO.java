package com.wilsonpedro.novelbr.dto;

import java.io.Serializable;

import com.wilsonpedro.novelbr.entities.Novel;

public class NovelDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String title;
	
	private String synopsis;
	
	private Long authorId;
	
	public NovelDTO() {
	}

	public NovelDTO(Long id, String title, String synopsis, Long authorId) {
		this.id = id;
		this.title = title;
		this.synopsis = synopsis;
		this.authorId = authorId;
	}
	
	public NovelDTO(Novel novel) {
		this.id = novel.getId();
		this.title = novel.getTitle();
		this.synopsis = novel.getSynopsis();
		this.authorId = novel.getAuthor().getId();
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

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
}
