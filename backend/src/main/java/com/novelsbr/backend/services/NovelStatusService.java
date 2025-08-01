package com.novelsbr.backend.services;

import com.novelsbr.backend.domain.entities.NovelStatus;
import com.novelsbr.backend.enums.NovelStatusType;

public interface NovelStatusService {
	
	NovelStatus findById(Integer id);
	
	NovelStatus findByNovelStatusType(NovelStatusType type);
}
