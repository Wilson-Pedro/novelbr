package com.novelsbr.backend.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.novelsbr.backend.domain.dto.AuthorNovelInfoDTO;
import com.novelsbr.backend.domain.dto.CardNovelDTO;
import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.projections.CardNovelProjection;

public interface NovelService {

	Novel save(NovelDTO novelDTO);
	
	Novel findById(Long id);
	
	Novel findNovelByNovelName(String novelName);
	
	List<Novel> findAll();
	
	Page<Novel> searchNovel(String novelName, int page, int size);
	
	Page<CardNovelProjection> findNovelCardsByGenders(List<String> genders, int page, int size);
	
	List<CardNovelDTO> findNovelCards();
	
	List<CardNovelDTO> findNovelCardsByUsername(String username);
	
	AuthorNovelInfoDTO findNovelInfoByNovelId(Long novelId);
	
	boolean existsByNovelName(String novelName);
	
	void changeNovelStatus(Long novelId, Integer novelStatusId);
	
	void changeNovelImageUri(Long novelId, String imageUri, MultipartFile file);
}
