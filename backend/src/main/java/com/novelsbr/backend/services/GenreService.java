package com.novelsbr.backend.services;

import java.util.List;

import com.novelsbr.backend.domain.entities.Genre;

public interface GenreService {

	List<Genre> findAll();
	
	List<Genre> findGendersByNovelId(Long novelId);
	
	List<Genre> findAllGenders();
}
