package com.novelsbr.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.ChapterTextDTO;
import com.novelsbr.backend.domain.dto.LastChaptersDTO;
import com.novelsbr.backend.domain.dto.NovelsChapterTitleDTO;
import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.exceptions.NotFoundException;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.services.ChapterService;
import com.novelsbr.backend.services.NovelService;
import com.novelsbr.backend.utils.htmlsanitizer.HtmlSanitizerUtil;

@Service
public class ChapterServiceImpl implements ChapterService {
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	@Autowired
	private NovelService novelService;

	@Override
	public Chapter save(ChapterDTO chapterDTO) {
		Novel novel = novelService.findById(chapterDTO.getNovelId());
		Chapter chapter = new Chapter();
		Integer chapterNumber = findMaxChapterNumber(novel.getId());
		
		chapter.setTitle(HtmlSanitizerUtil.sanitize(chapterDTO.getTitle()));
		chapter.setChapterText(HtmlSanitizerUtil.sanitize(chapterDTO.getChapterText()));
		
		chapter.setNovel(novel);
		chapter.setChapterNumber(chapterNumber == null ? 1 : chapterNumber + 1);
		
		return chapterRepository.save(chapter);
	}

	@Override
	public List<NovelsChapterTitleDTO> findAllNovelsChapterTitleByNovelId(Long novelId) {
		return chapterRepository.findAllNovelsChapterTitleByNovelId(novelId)
				.stream().map(NovelsChapterTitleDTO::new).toList();
	}

	@Override
	public ChapterTextDTO findChapterText(Integer chapterNumber, String novelName) {
		return new ChapterTextDTO(chapterRepository.findChapterText(chapterNumber, novelName));
	}

	@Override
	public Integer findMaxChapterNumber(Long novelId) {
		return chapterRepository.findMaxChapterNumber(novelId);
	}

	@Override
	public List<LastChaptersDTO> findLastChapters() {
		return chapterRepository.findLastChapters().stream()
				.map(LastChaptersDTO::new).toList();
	}

	@Override
	public Chapter findById(Long id) {
		return chapterRepository.findById(id).orElseThrow(
				() -> new NotFoundException("Chapter not found."));
	}
}
