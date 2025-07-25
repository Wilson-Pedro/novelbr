package com.novelsbr.backend.services;

import java.util.List;

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.ChapterTextDTO;
import com.novelsbr.backend.domain.dto.LastChaptersDTO;
import com.novelsbr.backend.domain.dto.NovelsChapterTitleDTO;
import com.novelsbr.backend.domain.entities.Chapter;

public interface ChapterService {

	Chapter save(ChapterDTO chapter);
	
	Chapter findById(Long id);
	
	ChapterTextDTO findChapterText(Integer chapterNumber, String novelName);
	
	List<NovelsChapterTitleDTO> findAllNovelsChapterTitleByNovelId(Long novelId);
	
	Integer findMaxChapterNumber(Long novelId);
	
	List<LastChaptersDTO> findLastChapters();
}
