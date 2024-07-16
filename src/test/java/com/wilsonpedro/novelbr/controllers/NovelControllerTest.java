package com.wilsonpedro.novelbr.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilsonpedro.novelbr.dto.NovelDTO;
import com.wilsonpedro.novelbr.dto.ResquestIdDTO;
import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.enums.UserType;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class NovelControllerTest {

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
	
	Author author = new Author(null, "Cronos", UserType.AUTHOR, "cronos@gmail.com", "123");
	Novel novel = new Novel(null, "Againts the Gods", "The Gods...", author);

	@BeforeEach
	void setUp() throws Exception {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	void save() throws Exception{
		userRepository.save(author);
		
		assertEquals(0, novelRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(new NovelDTO(novel));
		
		mockMvc.perform(post("/novels/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(jsonPath("$.title", equalTo("Againts the Gods")))
				.andExpect(jsonPath("$.synopsis", equalTo("The Gods...")))
				.andExpectAll(status().isCreated());
		
		assertEquals(1, novelRepository.count());
	}
	
	@Test
	void pages() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		
		novelRepository.save(new Novel(null, "Againts the Gods 1", "The Gods...", author));
		novelRepository.save(new Novel(null, "Againts the Gods 2", "The Gods...", author));
		novelRepository.save(new Novel(null, "Againts the Gods 3", "The Gods...", author));
		
		mockMvc.perform(get("/novels/pages")
				.param("page", "0")
				.param("size", "2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void findAll() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		
		mockMvc.perform(get("/novels"))
				.andExpect(status().isOk());
	}
	
	@Test
	void findById() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		
		Long id = novelRepository.findAll().get(0).getId();
		
		mockMvc.perform(get("/novels/{id}", id))
				.andExpect(jsonPath("$.title", equalTo("Againts the Gods")))
				.andExpect(jsonPath("$.synopsis", equalTo("The Gods...")))
				.andExpect(status().isOk());
	}

	@Test
	void update() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		novel.setTitle("Againts the Gods 2");
		
		Long id = novelRepository.findAll().get(0).getId();
		String jsonRequest = objectMapper.writeValueAsString(new NovelDTO(novel));
		
		mockMvc.perform(put("/novels/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(jsonPath("$.title", equalTo("Againts the Gods 2")))
				.andExpect(status().isOk());
		
		assertEquals(1, novelRepository.count());
	}
	
	@Test
	void delete() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		
		assertEquals(1, novelRepository.count());
		
		Long id = novelRepository.findAll().get(0).getId();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/novels/{id}", id))
				.andExpect(status().isNoContent());
		
		assertEquals(0, novelRepository.count());
	}
	
	@Test
	void deleteAllByAuthorId() throws Exception{
		Author author2 = new Author(null, "Cronos 2", UserType.AUTHOR, "cronos2@gmail.com", "123");
		
		userRepository.saveAll(List.of(author, author2));
		
		novelRepository.save(new Novel(null, "Againts the Gods1", "The Gods...", author2));
		novelRepository.save(new Novel(null, "Againts the Gods2", "The Gods...", author));
		novelRepository.save(new Novel(null, "Againts the Gods3", "The Gods...", author));
		
		assertEquals(3, novelRepository.count());
		Long authorId = userRepository.findAll().get(0).getId();
		
		String jsonRequest = objectMapper.writeValueAsString(new ResquestIdDTO(authorId));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/novels/deleteAllByAuthor")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isNoContent());
		
		assertEquals(1, novelRepository.count());
	}
}
