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

import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootTest
class UserServiceTest {
	
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
	void save() {
		assertEquals(0, userRepository.count());
		
		User userSaved = userService.save(user);
		
		assertNotNull(userSaved.getId());
		assertEquals("Cronos", userSaved.getPseudonym());
		assertEquals("cronos@gmail.com", userSaved.getEmail());
		assertEquals("123", userSaved.getPassword());
		
		assertEquals(1, userRepository.count());
	}
	
	@Test
	void findAll() {
		userRepository.save(new User(null, "Cronos1", "cronos1@gmail.com", "123"));
		userRepository.save(new User(null, "Cronos2", "cronos2@gmail.com", "123"));
		userRepository.save(new User(null, "Cronos3", "cronos3@gmail.com", "123"));
		
		Pageable pageable = PageRequest.of(0, 2);
		Page<User> pages = userService.findAll(pageable);
		
		assertNotNull(pages);
		assertThat(pages.getContent().size()).isEqualTo(2);
		assertThat(pages.getTotalElements()).isEqualTo(3);
	}
	
	@Test
	void Pages() {
		userRepository.save(user);
		
		List<User> users = userService.findAll();
		
		assertEquals(users.size(), userRepository.count());
	}
	
	@Test
	void findById() {
		userRepository.save(user);
		Long id = userRepository.findAll().get(0).getId();
		
		User userFinded = userService.findById(id);
		assertEquals("Cronos", userFinded.getPseudonym());
		assertEquals("cronos@gmail.com", userFinded.getEmail());
		assertEquals("123", userFinded.getPassword());
	}

	@Test
	void update() {
		userRepository.save(user);
		Long id = userRepository.findAll().get(0).getId();
		user.setPseudonym("Cronos BR");
		
		User userUpdated = userService.update(user, id);
		assertEquals("Cronos BR", userUpdated.getPseudonym());
		
		assertEquals(1, userRepository.count());
	}
	
	@Test
	void delete() {
		userRepository.save(user);
		assertEquals(1, userRepository.count());
		
		Long id = userRepository.findAll().get(0).getId();
		userService.delete(id);
		
		assertEquals(0, userRepository.count());
	}
}
