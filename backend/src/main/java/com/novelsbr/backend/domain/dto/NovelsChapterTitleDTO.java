package com.novelsbr.backend.domain.dto;

import com.novelsbr.backend.domain.projections.NovelsChapterTitleProjection;

public class NovelsChapterTitleDTO {

	private Long chapterId;
	
	private Long novelId;
	
	private Long authorId;
	
	private String title;
	
	public NovelsChapterTitleDTO() {
	}

	public NovelsChapterTitleDTO(NovelsChapterTitleProjection projection) {
		this.chapterId = projection.getChapterId();
		this.novelId = projection.getNovelId();
		this.authorId = projection.getAuthorId();
		this.title = projection.getTitle();
	}

	public Long getChapterId() {
		return chapterId;
	}

	public Long getNovelId() {
		return novelId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public String getTitle() {
		return title;
	}
}
