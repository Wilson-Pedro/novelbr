package com.novelsbr.backend.domain.projections;

import java.time.LocalDateTime;

public interface AuthorNovelMinProjection {

	Long getNovelId();
	
	Long getAuthorId();
	
	String getNovelName();
	
	Integer getNovelStatusId();
	
	String getUsername();
	
	String getImageUri();
	
	String getSynopsis();
	
	LocalDateTime getDateRegistrion();
}
