package com.novelsbr.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class GenderServiceTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	GenderService genderService;
	
	List<Gender> genders = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		novelRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
	}

	@Test
	@Transactional
	void save() {
		assertEquals(0, genderRepository.count());
		
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(null, type));
		}
		genderRepository.saveAll(genders);
		
		assertTrue(genderRepository.count() > 0);
		assertEquals(genders.size(), genderRepository.count());
	}
}
