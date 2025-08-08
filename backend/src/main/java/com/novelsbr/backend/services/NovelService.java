package com.novelsbr.backend.services;

import java.util.List;

import com.novelsbr.backend.domain.dto.AuthorNovelInfoDTO;
import com.novelsbr.backend.domain.dto.CardNovelDTO;
import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.domain.entities.Novel;

public interface NovelService {

	Novel save(NovelDTO novelDTO);
	
	Novel findById(Long id);
	
	List<Novel> findAll();
	
	List<Novel> searchNovel(String novelName);
	
	List<CardNovelDTO> findNovelCards();
	
	List<CardNovelDTO> findNovelCardsByUsername(String username);
	
	AuthorNovelInfoDTO findNovelInfoByNovelId(Long novelId);
	
	boolean existsByNovelName(String novelName);
	
	void changeNovelStatus(Long novelId, Integer novelStatusId);
}
