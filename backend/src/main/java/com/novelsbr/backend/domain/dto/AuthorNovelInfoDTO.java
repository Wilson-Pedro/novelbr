package com.novelsbr.backend.domain.dto;

import java.io.Serializable;

import com.novelsbr.backend.domain.projections.AuthorNovelMinProjection;
import com.novelsbr.backend.enums.NovelStatusType;

public class AuthorNovelInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long authorId;
	
	private Long novelId;
	
	private String novelName;
	
	private String novelStatus;
	
	private String username;
	
	private String imageUri;
	
	private Integer year;
	
	private String synopsis;
	
	public AuthorNovelInfoDTO() {
	}

	public AuthorNovelInfoDTO(AuthorNovelMinProjection projection) {
		this.authorId = projection.getAuthorId();
		this.novelId = projection.getNovelId();
		this.novelName = projection.getNovelName();
		this.novelStatus = NovelStatusType.toEnum(projection.getNovelStatusId()).getStatus();
		this.username = projection.getUsername();
		this.imageUri = projection.getImageUri();
		this.year = projection.getDateRegistrion().getYear();
		this.synopsis = projection.getSynopsis();
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

	public String getNovelStatus() {
		return novelStatus;
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
	
	public Integer getYear() {
		return year;
	}
}
