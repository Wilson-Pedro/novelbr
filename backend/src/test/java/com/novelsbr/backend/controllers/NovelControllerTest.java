package com.novelsbr.backend.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.entities.User;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.repositories.UserRepository;
import com.novelsbr.backend.services.NovelService;

@SpringBootTest
@AutoConfigureMockMvc
class NovelControllerTest {
	
	@Autowired
	NovelService novelService;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	static String URI = "/novels";
	
	User user = new User(null, "João", "AllStar", "joao@gmail.com", "1234");
	
	NovelDTO novelDTO = new NovelDTO();
	
	Set<Gender> genders = new HashSet<>();

	@BeforeEach
	void setUp() {
		novelRepository.deleteAll();
		userRepository.deleteAll();
		genderRepository.deleteAll();
		
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(null, type));
		}
		genderRepository.saveAll(genders);
		userRepository.save(user);
	}
	
	@Test
	void save() throws Exception {
		
		novelDTO = new NovelDTO(null, 
				"Jornada para o Além", 
				user, 
				genders, 
				"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
				"https://wallpapercave.com/wp/wp5044832.jpg");
		
		assertEquals(0, novelRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(novelDTO);
		
		mockMvc.perform(post(URI + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated());
		
		assertEquals(1, novelRepository.count());
	}

}
