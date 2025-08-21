package com.novelsbr.backend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.novelsbr.backend.domain.dto.LoginRequest;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.entities.NovelStatus;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.enums.NovelStatusType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.infra.security.TokenService;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.repositories.NovelStatusRepository;
import com.novelsbr.backend.services.AuthorService;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenderControllerTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	NovelStatusRepository novelStatusRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	Set<Gender> genders = new HashSet<>();
	List<NovelStatus> novelStatsus = new ArrayList();
	
	Author author = new Author(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
	
	static String URI = "/genders";
	
	static String TOKEN = "";

	@Test
	@Order(1)
	void preparingTestEnvironment() {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		novelStatusRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
		
		for(NovelStatusType type : NovelStatusType.values()) {
			novelStatsus.add(new NovelStatus(type));
		}
		
		novelStatusRepository.saveAll(novelStatsus);
	}
	
	@Test
	@Order(2)
	void getToken() {
		author = authorService.save(new AuthorDTO(author));	
		System.out.println("Author 1: " + author);
		LoginRequest login = new LoginRequest("AllStar", "1234");
		
		var usernamePassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());		
		var authentication = authenticationManager.authenticate(usernamePassword);
		
		String token = tokenService.generateToken((Author) authentication.getPrincipal());
		
		assertNotNull(token);
		
		TOKEN = token;
	}
	
	@Test
	@Order(3)
	void findAll() throws Exception {
		
		Integer id = 1;
		
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(type));
			id++;
		}
		
		genderRepository.saveAll(genders);
		
		mockMvc.perform(get(URI)
				.header("Authorization", "Bearer " + TOKEN))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(genderRepository.count()));
		
		
		assertTrue(genderRepository.count() > 0);
		assertEquals(genders.size(), genderRepository.count());
	}
	
	@Test
	@Transactional
	void findGendersByNovelId() throws Exception {
		
		Author author2 = new Author(null, "Lucas", "Purple", "lucas@gmail.com", "1234", UserRole.AUTHOR);
		
		Novel novel = new Novel(null, 
				"Jornada para o Além", 
				author2, 
				new NovelStatus(NovelStatusType.IN_COURSE),
				genders, 
				"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
				"https://wallpapercave.com/wp/wp5044832.jpg");
		
		authorRepository.save(author2);
		novelRepository.save(novel);
		
		Long novelId = novelRepository.findAll().get(0).getId();
		
		mockMvc.perform(get(URI + "/novel/" + novelId)
				.header("Authorization", "Bearer " + TOKEN))
				.andExpect(status().isOk());
	}
}
