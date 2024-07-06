package com.wilsonpedro.novelbr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;

@Service
public class ChapterService {

	@Autowired
	private ChapterRepository chapterRepository;
	
	@Transactional
	public Chapter save(Chapter user) {
		return chapterRepository.save(user);
	}

	public List<Chapter> findAll() {
		return chapterRepository.findAll();
	}

	public Optional<Chapter> findById(Long id) {
		return chapterRepository.findById(id);
	}

	@Transactional
	public Chapter update(Chapter chapter, Long id) {
		Chapter chapterFinded = findById(id).get();
		chapterFinded.setChapterTilte(chapter.getChapterTilte());
		chapterFinded.setChapterNumber(chapter.getChapterNumber());
		chapterFinded.setText(chapter.getText());
		chapterFinded.setNovel(chapter.getNovel());
		return chapterRepository.save(chapterFinded);
	}

	@Transactional
	public void delete(Long id) {
		chapterRepository.delete(findById(id).get());
		
	}
}
