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
import com.wilsonpedro.novelbr.dto.ChapterDTO;
import com.wilsonpedro.novelbr.dto.ResquestIdDTO;
import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class ChapterControllerTest {

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
	
	Author author = new Author(null, "Cronos", "cronos@gmail.com", "123");
	Novel novel = new Novel(null, "Againts the Gods", "The Gods...", author);
	Chapter chapter = new Chapter(null, "Begins", 1, "In Those Days, the Gods...", novel);

	@BeforeEach
	void setUp() throws Exception {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	void save() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		
		assertEquals(0, chapterRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(new ChapterDTO(chapter));
		
		mockMvc.perform(post("/chapters/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(jsonPath("$.chapterTilte", equalTo("Begins")))
				.andExpect(jsonPath("$.chapterNumber", equalTo(1)))
				.andExpect(jsonPath("$.text", equalTo("In Those Days, the Gods...")))
				.andExpectAll(status().isCreated());
		
		assertEquals(1, chapterRepository.count());
	}
	
	@Test
	void pages() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		
		chapterRepository.save(new Chapter(null, "Begins", 1, "In Those Days, the Gods...", novel));
		chapterRepository.save(new Chapter(null, "Begins", 2, "In Those Days, the Gods...", novel));
		chapterRepository.save(new Chapter(null, "Begins", 3, "In Those Days, the Gods...", novel));
		
		mockMvc.perform(get("/chapters/pages")
				.param("page", "0")
				.param("size", "2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void findAll() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		
		mockMvc.perform(get("/chapters"))
				.andExpect(status().isOk());
	}
	
	@Test
	void findById() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		
		Long id = chapterRepository.findAll().get(0).getId();
		
		mockMvc.perform(get("/chapters/{id}", id))
				.andExpect(jsonPath("$.chapterTilte", equalTo("Begins")))
				.andExpect(jsonPath("$.chapterNumber", equalTo(1)))
				.andExpect(jsonPath("$.text", equalTo("In Those Days, the Gods...")))
				.andExpect(status().isOk());
	}

	@Test
	void update() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		chapter.setChapterTilte("Start");
		
		Long id = chapterRepository.findAll().get(0).getId();
		String jsonRequest = objectMapper.writeValueAsString(new ChapterDTO(chapter));
		
		mockMvc.perform(put("/chapters/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(jsonPath("$.chapterTilte", equalTo("Start")))
				.andExpect(status().isOk());
		
		assertEquals(1, chapterRepository.count());
	}
	
	@Test
	void delete() throws Exception{
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		
		assertEquals(1, chapterRepository.count());
		
		Long id = chapterRepository.findAll().get(0).getId();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/chapters/{id}", id))
				.andExpect(status().isNoContent());
		
		assertEquals(0, chapterRepository.count());
	}
	
	@Test
	void deleteAllByNovelId() throws Exception{
		Author author2 = new Author(null, "Cronos 2", "cronos2@gmail.com", "123");
		Novel novel2 = new Novel(null, "Againts the Gods 2", "In Those Days...", author2);
		
		userRepository.saveAll(List.of(author, author2));
		novelRepository.saveAll(List.of(novel, novel2));
		
		chapterRepository.save(new Chapter(null, "Begins", 1, "In Those Days, the Dogs...", novel2));
		chapterRepository.save(new Chapter(null, "Begins", 2, "In Those Days, the Cats...", novel));
		chapterRepository.save(new Chapter(null, "Begins", 3, "In Those Days, the Frogs...", novel));
		
		assertEquals(3, chapterRepository.count());
		
		Long novelId = userRepository.findAll().get(0).getId();
		
		String jsonRequest = objectMapper.writeValueAsString(new ResquestIdDTO(novelId));
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/chapters/deleteAllByNovel")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isNoContent());
		
		assertEquals(1, chapterRepository.count());
	}
}
