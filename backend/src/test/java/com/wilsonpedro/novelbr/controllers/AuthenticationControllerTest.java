package com.wilsonpedro.novelbr.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.wilsonpedro.novelbr.dto.AuthenticationDTO;
import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.enums.UserType;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
class AuthenticationControllerTest {
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;


	@Test
	@Order(1)
	void register() throws Exception {
		
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
		
		assertEquals(0, userRepository.count());
		
		User user = new User(null, "Cronos", UserType.AUTHOR, "cronos@gmail.com", "123");
		
		String jsonRequest = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated());
		
		assertEquals(1, userRepository.count());
	}

	@Test
	@Order(2)
	void login() throws Exception {
		
		assertEquals(1, userRepository.count());
		
		AuthenticationDTO authenticationDTO = new AuthenticationDTO("Cronos", "123");
		
		String jsonRequest =  objectMapper.writeValueAsString(authenticationDTO);
		
		mockMvc.perform(post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk());
	}
}
