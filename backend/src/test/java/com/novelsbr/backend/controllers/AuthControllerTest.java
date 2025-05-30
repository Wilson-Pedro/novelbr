package com.novelsbr.backend.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.dto.LoginRequest;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.services.AuthorService;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthControllerTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	AuthorDTO auhtorDTO = new AuthorDTO(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
	
	static String URI = "/auth";
	
	@Test
	@Order(1)
	void save() throws Exception {
		novelRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
		
		assertEquals(0, authorRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(auhtorDTO);
		
		mockMvc.perform(post("/authors/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", equalTo("João")))
				.andExpect(jsonPath("$.username", equalTo("AllStar")))
				.andExpect(jsonPath("$.email", equalTo("joao@gmail.com")));
		
		
		assertEquals(1, authorRepository.count());
	}
	
	@Test
	@Order(2)
	void login() throws Exception {
		
		LoginRequest login = new LoginRequest("AllStar", "1234");
		
		String jsonRequest = objectMapper.writeValueAsString(login);
		
		mockMvc.perform(post(URI + "/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk());
	}

}
