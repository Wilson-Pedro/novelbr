package com.wilsonpedro.novelbr.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EmailExistsException;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EntityNotFoundException;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.PseudonymExistsException;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;
import com.wilsonpedro.novelbr.services.UserService;

@SpringBootTest
class UserExceptions {
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	User user = new User(null, "Cronos", "cronos@gmail.com", "123");

	@BeforeEach
	void setUp() throws Exception {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void entityNotFoundException() {
		
		assertThrows(EntityNotFoundException.class, () -> userService.findById(70L));
	}
	
	@Test
	void emailExistsException() {
		userRepository.save(user);
		
		
		assertThrows(EmailExistsException.class, 
				() -> userService.save(new User(null, "Cronos 2", "cronos@gmail.com", "123")));
	}

	@Test
	void pseudonymExistsException() {
		userRepository.save(user);
		
		
		assertThrows(PseudonymExistsException.class, 
				() -> userService.save(new User(null, "Cronos", "cronos2@gmail.com", "123")));
	}
}