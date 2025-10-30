package com.novelsbr.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novelsbr.backend.domain.entities.Genre;
import com.novelsbr.backend.repositories.GenreRepository;
import com.novelsbr.backend.services.GenreService;
import com.novelsbr.backend.services.NovelService;

@Service
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private NovelService novelService;

	@Override
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Genre> findGendersByNovelId(Long novelId) {
		return novelService.findById(novelId).getGenders().stream().toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Genre> findAllGenders() {
		return genreRepository.findAllGenders().stream()
				.map(x -> new Genre(x.getGenreType()))
				.toList();
	}

}
