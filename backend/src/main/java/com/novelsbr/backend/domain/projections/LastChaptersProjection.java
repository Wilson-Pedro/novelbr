package com.novelsbr.backend.domain.projections;

import java.time.LocalDateTime;

public interface LastChaptersProjection {

	Long getNovelId();
	
	Long getAuthorId();
	
	String getNovelName();
	
	String getUsername();
	
	String getTitle();
	
	Integer getChapterNumber();
	
	LocalDateTime getDateRegistration();
}
