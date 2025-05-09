package com.novelsbr.backend.services;

import java.util.List;

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.NovelsChapterTitleDTO;
import com.novelsbr.backend.domain.entities.Chapter;

public interface ChapterService {

	Chapter save(ChapterDTO chapter);
	
	List<NovelsChapterTitleDTO> findAllNovelsChapterTitleByAuthorId(Long authorId);
}
