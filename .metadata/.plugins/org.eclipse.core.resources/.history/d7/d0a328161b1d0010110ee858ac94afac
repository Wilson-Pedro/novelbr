package com.novelsbr.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;

@SpringBootTest
@AutoConfigureMockMvc
class GenderControllerTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	List<Gender> genders = new ArrayList<>();
	
	static String URI = "/genders";
	
	@BeforeEach
	void setUp() {
		novelRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
	}
	
	@Test
	void findAll() throws Exception {
		assertEquals(0, genderRepository.count());
		
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(null, type));
		}
		genderRepository.saveAll(genders);
		
		mockMvc.perform(get(URI))
				.andExpect(status().isOk());
		
		
		assertTrue(genderRepository.count() > 0);
		assertEquals(genders.size(), genderRepository.count());
	}
}
