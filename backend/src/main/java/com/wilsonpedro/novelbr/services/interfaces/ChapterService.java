package com.wilsonpedro.novelbr.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wilsonpedro.novelbr.dto.ChapterDTO;
import com.wilsonpedro.novelbr.entities.Chapter;

public interface ChapterService {

	Chapter save(ChapterDTO chaptertDTO);
	
	Page<Chapter> findAll(Pageable pageable);
	
	List<Chapter> findAll();
	
	Chapter findById(Long id);
	
	Chapter update(ChapterDTO ChapterDTO, Long id);
	
	void delete(Long id);
	
	void deleteAllByNovel(Long novelId);
}
