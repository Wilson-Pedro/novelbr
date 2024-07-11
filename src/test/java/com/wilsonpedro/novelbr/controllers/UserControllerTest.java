package com.wilsonpedro.novelbr.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

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
	
	User user = new User(null, "Cronos", "cronos@gmail.com", "123");

	@BeforeEach
	void setUp() throws Exception {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	void save() throws Exception{
		assertEquals(0, userRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(post("/users/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(jsonPath("$.pseudonym", equalTo("Cronos")))
				.andExpect(jsonPath("$.email", equalTo("cronos@gmail.com")))
				.andExpect(jsonPath("$.password", equalTo("123")))
				.andExpect(status().isCreated());
		
		assertEquals(1, userRepository.count());
	}
	
	@Test
	void pages() throws Exception{
		userRepository.save(new User(null, "Cronos 1", "cronos1@gmail.com", "123"));
		userRepository.save(new User(null, "Cronos 2", "cronos2@gmail.com", "123"));
		userRepository.save(new User(null, "Cronos 3", "cronos3@gmail.com", "123"));
		
		mockMvc.perform(get("/users/pages")
				.param("page", "0")
				.param("size", "2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void findAll() throws Exception{
		userRepository.save(user);
		
		mockMvc.perform(get("/users"))
				.andExpect(status().isOk());
	}
	
	@Test
	void findById() throws Exception{
		userRepository.save(user);
		
		Long id = userRepository.findAll().get(0).getId();
		
		mockMvc.perform(get("/users/{id}", id))
				.andExpect(jsonPath("$.pseudonym", equalTo("Cronos")))
				.andExpect(jsonPath("$.email", equalTo("cronos@gmail.com")))
				.andExpect(jsonPath("$.password", equalTo("123")))
				.andExpect(status().isOk());
	}

	@Test
	void update() throws Exception{
		userRepository.save(user);
		user.setPseudonym("Cronos 2");
		
		Long id = userRepository.findAll().get(0).getId();
		String jsonRequest = objectMapper.writeValueAsString(user);
		
		mockMvc.perform(put("/users/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(jsonPath("$.pseudonym", equalTo("Cronos 2")))
				.andExpect(status().isOk());
		
		assertEquals(1, userRepository.count());
	}
	
	@Test
	void delete() throws Exception{
		userRepository.save(user);
		assertEquals(1, userRepository.count());
		
		Long id = userRepository.findAll().get(0).getId();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id))
				.andExpect(status().isNoContent());
		
		assertEquals(0, userRepository.count());
	}
}
