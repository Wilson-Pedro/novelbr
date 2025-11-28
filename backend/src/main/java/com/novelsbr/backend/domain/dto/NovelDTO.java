package com.novelsbr.backend.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.novelsbr.backend.domain.entities.Genre;
import com.novelsbr.backend.domain.entities.Novel;

public class NovelDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String novelName;
	
	private Long authorId;
	
	private Integer novelStatusCode;
	
	private List<String> genders = new ArrayList<>();
	
	private String synopsis;
	
	private String imageUri = "";
	
	private LocalDateTime dateRegistrion;
	
	public NovelDTO() {
	}
	
	public NovelDTO(Novel novel) {
		this.id = novel.getId();
		this.novelName = novel.getNovelName();
		this.authorId = novel.getAuthor().getId();
		this.novelStatusCode = novel.getNovelStatus().getNovelStatusType().getCode();
		this.genders = genderToString(novel.getGenders());
		this.synopsis = novel.getSynopsis();
		this.imageUri = novel.getImageUri();
		this.dateRegistrion = novel.getDateRegistration();
	}
	
	public NovelDTO(Long id, String novelName, Long authorId, Integer novelStatusCode, List<String> genders, String synopsis, String imageUri) {
		this.id = id;
		this.novelName = novelName;
		this.authorId = authorId;
		this.novelStatusCode = novelStatusCode;
		this.genders = genders;
		this.synopsis = synopsis;
		this.imageUri = imageUri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNovelName() {
		return novelName;
	}

	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Integer getNovelStatusCode() {
		return novelStatusCode;
	}

	public void setNovelStatusCode(Integer novelStatusCode) {
		this.novelStatusCode = novelStatusCode;
	}

	public List<String> getGenders() {
		return genders;
	}

	public void setGenders(List<String> genders) {
		this.genders = genders;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public LocalDateTime getDateRegistrion() {
		return dateRegistrion;
	}
	
	public List<String> genderToString(Collection<Genre> genders) {
		return genders.stream().map(x -> x.getGenreType().getType()).toList();
	}
}
