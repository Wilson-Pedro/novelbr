package com.novelsbr.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.LoginRequest;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.infra.security.TokenService;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.services.AuthorService;
import com.novelsbr.backend.services.ChapterService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChapterControllerTest {
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	ChapterService chapterService;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	Set<Gender> genders = new HashSet<>();
	
	static String URI = "/chapters";
	
	static String TOKEN = "";
	
	@Test
	@Order(1)
	void preparingTestEnvironment() {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
		
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(null, type));
		}
		
		genderRepository.saveAll(genders);
	}
	
	void getToken() {
		LoginRequest login = new LoginRequest("AllStar", "1234");
		
		var usernamePassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());		
		var authentication = authenticationManager.authenticate(usernamePassword);
		
		String token = tokenService.generateToken((Author) authentication.getPrincipal());
		assertNotNull(token);
		
		TOKEN = token;
	}
	
	@Test
	@Order(2)
	@Transactional
	void save() throws Exception {
		
		Author author = new Author(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
		author = authorService.save(new AuthorDTO(author));
		
		Novel novel = new Novel(null, 
				"Jornada para o Além", 
				author, 
				genders, 
				"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
				"https://wallpapercave.com/wp/wp5044832.jpg");
		novelRepository.save(novel);
		
		getToken();
		
		assertEquals(0, chapterRepository.count());
		
		Long novel_id = novelRepository.findAll().get(0).getId();
		
		ChapterDTO chapterDTO = new ChapterDTO(null, "Hellifen", "Em uma pequena vila...", novel_id);
		
		String jsonRequest = objectMapper.writeValueAsString(chapterDTO);
			
		mockMvc.perform(post(URI + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				.header("Authorization", "Bearer " + TOKEN))
				.andExpect(status().isCreated());
		
		assertEquals(1, chapterRepository.count());
	}
}
