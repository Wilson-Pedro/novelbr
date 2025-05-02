package com.novelsbr.backend.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.services.AuthorService;

@SpringBootTest
class AuthorExceptionsTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	AuthorService authorService;
	
	@BeforeEach
	void setUp() {
		novelRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
	}
	
	@Test
	void existingAuthorExceptionTest() {
		Author author = new Author(null, "João", "All Star", "joao@gmail.com", "1234", UserRole.AUTHOR);
		authorRepository.save(author);
		assertThrows(ExistingAuthorException.class, () -> authorService.save(new AuthorDTO(author)));
	}
}
