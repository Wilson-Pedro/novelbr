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

import com.wilsonpedro.novelbr.dto.ChapterDTO;
import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootTest
class ChapterServiceTest {
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ChapterService chapterService;
	
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
	void save() {
		assertEquals(0, chapterRepository.count());
		
		userRepository.save(author);
		novelRepository.save(novel);
		Chapter chapterSaved = chapterService.save(new ChapterDTO(chapter));
		
		assertNotNull(chapterSaved.getId());
		assertEquals("Begins", chapterSaved.getChapterTilte());
		assertEquals(1, chapterSaved.getChapterNumber());
		assertEquals("In Those Days, the Gods...", chapterSaved.getText());
		
		assertEquals(1, chapterRepository.count());
	}
	
	
	@Test
	void Pages() {
		userRepository.save(author);
		novelRepository.save(novel);
		
		chapterRepository.save(new Chapter(null, "Begins", 1, "In Those Days, the Gods...", novel));
		chapterRepository.save(new Chapter(null, "Begins", 2, "In Those Days, the Gods...", novel));
		chapterRepository.save(new Chapter(null, "Begins", 3, "In Those Days, the Gods...", novel));
		
		Pageable pageable = PageRequest.of(0, 2);
		Page<Chapter> pages = chapterService.findAll(pageable);
		
		assertNotNull(pages);
		assertThat(pages.getContent().size()).isEqualTo(2);
		assertThat(pages.getTotalElements()).isEqualTo(3);
	}
	
	@Test
	void findAll() {
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		
		List<Chapter> novels = chapterService.findAll();
		
		assertEquals(novels.size(), chapterRepository.count());
	}
	
	@Test
	void findById() {
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		
		Long id = chapterRepository.findAll().get(0).getId();
		
		Chapter chapterFinded = chapterService.findById(id);
		assertEquals("Begins", chapterFinded.getChapterTilte());
		assertEquals(1, chapterFinded.getChapterNumber());
		assertEquals("In Those Days, the Gods...", chapterFinded.getText());
	}

	@Test
	void update() {
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		
		Long id = chapterRepository.findAll().get(0).getId();
		
		chapter.setChapterTilte("Start");
		
		Chapter chapterUpdated = chapterService.update(new ChapterDTO(chapter), id);
		assertEquals("Start", chapterUpdated.getChapterTilte());
		
		assertEquals(1, chapterRepository.count());
	}
	
	@Test
	void delete() {
		userRepository.save(author);
		novelRepository.save(novel);
		chapterRepository.save(chapter);
		
		assertEquals(1, chapterRepository.count());
		
		Long id = chapterRepository.findAll().get(0).getId();
		chapterService.delete(id);
		
		assertEquals(0, chapterRepository.count());
	}
}