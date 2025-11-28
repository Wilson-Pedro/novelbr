package com.novelsbr.backend.domain.dto;

import java.io.Serializable;

import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.projections.CardNovelProjection;

public class CardNovelDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long novelId;
	
	private Long authorId;
	
	private String novelName;
	
	private String username;
	
	private String imageUri;
	
	public CardNovelDTO() {
	}

	public CardNovelDTO(Long novelId, Long authorId, String novelName, String username, String imageUri) {
		this.authorId = authorId;
		this.novelId = novelId;
		this.novelName = novelName;
		this.username = username;
		this.imageUri = imageUri;
	}

	public CardNovelDTO(CardNovelProjection cardNovelProjection) {
		this.novelId = cardNovelProjection.getNovelId();
		this.authorId = cardNovelProjection.getAuthorId();
		this.novelName = cardNovelProjection.getNovelName();
		this.username = cardNovelProjection.getUsername();
		this.imageUri = cardNovelProjection.getImageUri();
	}
	
	public CardNovelDTO(Novel novel) {
		this.novelId = novel.getId();
		this.authorId = novel.getAuthor().getId();
		this.novelName = novel.getNovelName();
		this.username = novel.getAuthor().getUsername();
		this.imageUri = novel.getImageUri();
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

	public String getImageUri() {
		return imageUri;
	}
}
