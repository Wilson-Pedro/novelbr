package com.wilsonpedro.novelbr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilsonpedro.novelbr.dto.ChapterDTO;
import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EntityNotFoundException;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;

@Service
public class ChapterService {

	@Autowired
	private ChapterRepository chapterRepository;
	
	@Autowired
	private NovelService novelService;
	
	@Transactional
	public Chapter save(Chapter chapter) {
		return chapterRepository.save(chapter);
	}
	
	@Transactional
	public Chapter save(ChapterDTO chapterRequestDTO) {
		Novel novel = novelService.findById(chapterRequestDTO.getNovelId());
		Chapter chapterSaved = new Chapter(chapterRequestDTO);
		chapterSaved.setNovel(novel);
		return chapterRepository.save(chapterSaved);
	}

	public List<Chapter> findAll() {
		return chapterRepository.findAll();
	}

	public Chapter findById(Long id) {
		return chapterRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Transactional
	public Chapter update(ChapterDTO ChapterDTO, Long id) { 
		Chapter chapterFinded = findById(id);
		Novel novel = novelService.findById(ChapterDTO.getNovelId());
		chapterFinded.setChapterTilte(ChapterDTO.getChapterTilte());
		chapterFinded.setChapterNumber(ChapterDTO.getChapterNumber());
		chapterFinded.setText(ChapterDTO.getText());
		chapterFinded.setNovel(novel);
		return chapterRepository.save(chapterFinded);
	}

	@Transactional
	public void delete(Long id) {
		chapterRepository.delete(findById(id));
		
	}
}
