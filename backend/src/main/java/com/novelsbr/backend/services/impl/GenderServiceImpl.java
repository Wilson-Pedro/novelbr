package com.novelsbr.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.services.GenderService;
import com.novelsbr.backend.services.NovelService;

import jakarta.transaction.Transactional;

@Service
public class GenderServiceImpl implements GenderService {
	
	@Autowired
	private GenderRepository genderRepository;
	
	@Autowired
	private NovelService novelService;

	@Override
	public List<Gender> findAll() {
		return genderRepository.findAll();
	}

	@Override
	@Transactional
	public List<String> findGendersByNovelId(Long novelId) {
		return novelService.findById(novelId).getGenders().stream()
				.map(x -> x.getGenderType().getType()).toList();
	}

	@Override
	public List<Gender> findAllGenders() {
		return genderRepository.findAllGenders().stream()
				.map(x -> new Gender(x.getGenderType()))
				.toList();
	}

}
