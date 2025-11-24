package com.novelsbr.backend.domain.projections;

public interface ChapterTextProjection {
	
	Long getChapterId();

	Integer getChapterNumber();
	
	String getTitle();
	
	String getChapterText();
	
	Long getNovelId();
	
	String getNovelName();
}
