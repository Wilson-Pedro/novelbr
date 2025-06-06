package com.novelsbr.backend.domain.projections;

public interface AuthorNovelMinProjection {

	Long getNovelId();
	
	Long getAuthorId();
	
	String getNovelName();
	
	String getUsername();
	
	String getImageUri();
	
	String getSynopsis();
}
