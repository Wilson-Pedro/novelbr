package com.wilsonpedro.novelbr.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wilsonpedro.novelbr.dto.NovelDTO;
import com.wilsonpedro.novelbr.entities.Novel;

public interface NovelService {

	Novel save(Novel novel);
	
	Novel save(NovelDTO novelDTO);
	
	Page<Novel> findAll(Pageable pageable);
	
	List<Novel> findAll();
	
	Novel findById(Long id);
	
	Novel update(NovelDTO novelDTO, Long id);
	
	void delete(Long id);
	
	void deleteAllByNovel(Long authorId);
}
