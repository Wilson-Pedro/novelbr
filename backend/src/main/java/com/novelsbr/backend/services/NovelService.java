package com.novelsbr.backend.services;

import java.util.List;

import com.novelsbr.backend.domain.dto.AuthorNovelInfoDTO;
import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.projections.CardNovelProjection;

public interface NovelService {

	Novel save(NovelDTO novelDTO);
	
	List<Novel> findAll();
	
	List<CardNovelProjection> findNovelCards();
	
	AuthorNovelInfoDTO findNovelInfoByNovelId(Long novelId);
}
