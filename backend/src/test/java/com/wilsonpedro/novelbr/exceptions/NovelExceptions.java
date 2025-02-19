package com.wilsonpedro.novelbr.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.enums.UserType;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EntityNotFoundException;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.ReaderCastException;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;
import com.wilsonpedro.novelbr.services.NovelServiceImpl;
import com.wilsonpedro.novelbr.services.interfaces.NovelService;

@SpringBootTest
class NovelExceptions {
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NovelService novelService;
	
	@Autowired
	NovelServiceImpl novelServiceImpl;
	
	Author author = new Author(null, "Cronos", UserType.AUTHOR, "cronos@gmail.com", "123");
	Novel novel = new Novel(null, "Againts the Gods", "The Gods...", author);

	@BeforeEach
	void setUp() throws Exception {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void entityNotFoundException() {
		
		assertThrows(EntityNotFoundException.class, () -> novelService.findById(70L));
	}
	
	@Test
	void readerCastException() {
		author.setUserType(UserType.READER);
		userRepository.save(author);
		
		assertThrows(ReaderCastException.class, 
				() -> novelServiceImpl.validadeAuthor(author.getId()));
	}
}
