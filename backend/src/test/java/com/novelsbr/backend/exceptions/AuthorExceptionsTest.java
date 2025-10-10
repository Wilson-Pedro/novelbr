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
import com.novelsbr.backend.services.AuthorService;
import com.novelsbr.backend.utils.TestUtil;

@SpringBootTest
class AuthorExceptionsTest {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorService authorService;
	
    @Autowired
    TestUtil testUtil;
	
	@BeforeEach
	void setUp() {
		testUtil.deleteAll();
	}
	
	@Test
	void existingAuthorExceptionTest() {
		Author author = new Author(null, "João", "All Star", "joao@gmail.com", "1234", UserRole.AUTHOR);
		authorRepository.save(author);
		assertThrows(ExistingAuthorException.class, () -> authorService.save(new AuthorDTO(author)));
	}
}
