package com.novelsbr.backend.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.novelsbr.backend.domain.entities.NovelStatus;
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

	public AuthorNovelInfoDTO(Long authorId, Long novelId, String novelName, NovelStatus novelStatus, String username, LocalDateTime dateRegistration, String imageUri, String synopsis) {
		this.authorId = authorId;
		this.novelId = novelId;
		this.novelName = novelName;
		this.novelStatus = novelStatus.getNovelStatusType().getStatus();
		this.username = username;
		this.imageUri = imageUri;
		this.year = dateRegistration.getYear();
		this.synopsis = synopsis;
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

	@Override
	public String toString() {
		return "AuthorNovelInfoDTO{" +
				"authorId=" + authorId +
				", novelId=" + novelId +
				", novelName='" + novelName + '\'' +
				", novelStatus='" + novelStatus + '\'' +
				", username='" + username + '\'' +
				", imageUri='" + imageUri + '\'' +
				", year=" + year +
				", synopsis='" + synopsis + '\'' +
				'}';
	}
}
