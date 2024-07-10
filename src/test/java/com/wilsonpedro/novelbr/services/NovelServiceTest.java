package com.wilsonpedro.novelbr.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.wilsonpedro.novelbr.dto.NovelDTO;
import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootTest
class NovelServiceTest {
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NovelService novelService;
	
	Author author = new Author(null, "Cronos", "cronos@gmail.com", "123");
	Novel novel = new Novel(null, "Againts the Gods", "The Gods...", author);

	@BeforeEach
	void setUp() throws Exception {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void saveCase01() {
		assertEquals(0, userRepository.count());
		
		userRepository.save(author);
		Novel novelSaved = novelService.save(novel);
		
		assertNotNull(novelSaved.getId());
		assertEquals("Againts the Gods", novelSaved.getTitle());
		assertEquals("The Gods...", novelSaved.getSynopsis());
		
		assertEquals(1, novelRepository.count());
	}
	
	@Test
	void saveCase02() {
		assertEquals(0, userRepository.count());
		
		userRepository.save(author);
		Novel novelSaved = novelService.save(new NovelDTO(novel));
		
		assertNotNull(novelSaved.getId());
		assertEquals("Againts the Gods", novelSaved.getTitle());
		assertEquals("The Gods...", novelSaved.getSynopsis());
		
		assertEquals(1, novelRepository.count());
	}
	
	@Test
	void pages() {
		userRepository.save(author);
		novelRepository.save(new Novel(null, "Againts the Gods1", "The Gods...", author));
		novelRepository.save(new Novel(null, "Againts the Gods2", "The Gods...", author));
		novelRepository.save(new Novel(null, "Againts the Gods3", "The Gods...", author));
		
		Pageable pageable = PageRequest.of(0, 2);
		Page<Novel> pages = novelService.findAll(pageable);
		
		assertNotNull(pages);
		assertThat(pages.getContent().size()).isEqualTo(2);
		assertThat(pages.getTotalElements()).isEqualTo(3);
	}
	
	@Test
	void findAll() {
		userRepository.save(author);
		novelRepository.save(novel);
		
		List<Novel> novels = novelService.findAll();
		
		assertEquals(novels.size(), novelRepository.count());
	}
	
	@Test
	void findById() {
		userRepository.save(author);
		novelRepository.save(novel);
		Long id = novelRepository.findAll().get(0).getId();
		
		Novel novelFinded = novelService.findById(id);
		assertEquals("Againts the Gods", novelFinded.getTitle());
		assertEquals("The Gods...", novelFinded.getSynopsis());
	}

	@Test
	void update() {
		userRepository.save(author);
		novelRepository.save(novel);
		Long id = novelRepository.findAll().get(0).getId();
		novel.setTitle("Againts the Gods 2");
		
		Novel novelUpdated = novelService.update(new NovelDTO(novel), id);
		assertEquals("Againts the Gods 2", novelUpdated.getTitle());
		
		assertEquals(1, novelRepository.count());
	}
	
	@Test
	void delete() {
		userRepository.save(author);
		novelRepository.save(novel);
		assertEquals(1, novelRepository.count());
		
		Long id = novelRepository.findAll().get(0).getId();
		novelService.delete(id);
		
		assertEquals(0, novelRepository.count());
	}
}
