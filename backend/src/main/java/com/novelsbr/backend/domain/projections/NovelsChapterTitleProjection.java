package com.novelsbr.backend.domain.projections;

public interface NovelsChapterTitleProjection {

	Long getChapterId();
	
	Long getNovelId();
	
	Long getAuthorId();
	
	String getTitle();
}
