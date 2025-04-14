package com.novelsbr.backend.services;

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.entities.Chapter;

public interface ChapterService {

	Chapter save(ChapterDTO chapter);
}
