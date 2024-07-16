package com.wilsonpedro.novelbr.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.enums.UserType;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EntityNotFoundException;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;
import com.wilsonpedro.novelbr.services.ChapterService;

@SpringBootTest
class ChapterExceptions {
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ChapterService chapterService;
	
	Author author = new Author(null, "Cronos", UserType.AUTHOR, "cronos@gmail.com", "123");
	Novel novel = new Novel(null, "Againts the Gods", "The Gods...", author);
	Chapter chapter = new Chapter(null, "Begins", 1, "In Those Days, the Gods...", novel);

	@BeforeEach
	void setUp() throws Exception {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void entityNotFoundException() {
		
		assertThrows(EntityNotFoundException.class, () -> chapterService.findById(70L));
	}
	
}
