package com.novelsbr.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class AuthorServiceTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	ChapterRepository chapterRepository;
	
	AuthorDTO author = new AuthorDTO(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
	
	@BeforeEach
	void setUp() {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
	}

	@Test
	@Transactional
	void save() {
		assertEquals(0, authorRepository.count());
		
		Author userSaved = authorService.save(author);
		assertNotNull(userSaved.getId());
		assertEquals("João", userSaved.getName());
		assertEquals("AllStar", userSaved.getUsername());
		assertEquals("joao@gmail.com", userSaved.getEmail());
		
		assertEquals(1, authorRepository.count());
	}
}
