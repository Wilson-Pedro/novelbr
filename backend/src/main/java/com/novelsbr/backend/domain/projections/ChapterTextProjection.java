package com.novelsbr.backend.domain.projections;

public interface ChapterTextProjection {

	Integer getChapterNumber();
	
	String getTitle();
	
	String getChapterText();
	
	Long getNovelId();
	
	String getNovelName();
}
