package com.novelsbr.backend.domain.dto;

import java.io.Serializable;

import com.novelsbr.backend.domain.projections.AuthorNovelMinProjection;

public class AuthorNovelInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long authorId;
	
	private Long novelId;
	
	private String novelName;
	
	private String username;
	
	private String imageUri;
	
	private String synopsis;
	
	public AuthorNovelInfoDTO() {
	}

	public AuthorNovelInfoDTO(AuthorNovelMinProjection authorNovelMinProjection) {
		this.authorId = authorNovelMinProjection.getAuthorId();
		this.novelId = authorNovelMinProjection.getNovelId();
		this.novelName = authorNovelMinProjection.getNovelName();
		this.username = authorNovelMinProjection.getUsername();
		this.imageUri = authorNovelMinProjection.getImageUri();
		this.synopsis = authorNovelMinProjection.getSynopsis();
	}

	public Long getAuthorId() {
		return authorId;
	}

	public Long getNovelId() {
		return novelId;
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

	public String getSynopsis() {
		return synopsis;
	}
}
