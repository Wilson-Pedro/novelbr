package com.novelsbr.backend.domain.projections;

public interface CardNovelProjection {

	Long getNovelId();
	
	Long getAuthorId();
	
	String getNovelName();
	
	String getUsername();
	
	String getImageUri();
}
