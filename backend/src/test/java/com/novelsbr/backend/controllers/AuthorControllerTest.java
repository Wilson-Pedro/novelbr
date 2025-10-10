package com.novelsbr.backend.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.dto.LoginRequest;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.infra.security.TokenService;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.services.AuthorService;
import com.novelsbr.backend.utils.TestUtil;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthorControllerTest {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
    @Autowired
    TestUtil testUtil;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	AuthorDTO userDTO = new AuthorDTO(null, "Lucas", "Purple", "lucas@gmail.com", "1234", UserRole.AUTHOR);
	
	Author author = new Author(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
	
	static String URI = "/authors";
	
	static String TOKEN = "";
	
	@Test
	@Order(1)
	void deleteAll() {
		testUtil.deleteAll();
	}
	
	@Test
	@Order(2)
	void getToken() {
		author = authorService.save(new AuthorDTO(author));
		LoginRequest login = new LoginRequest("AllStar", "1234");
		
		var usernamePassword = new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword());		
		var authentication = authenticationManager.authenticate(usernamePassword);
		
		String token = tokenService.generateToken((Author) authentication.getPrincipal());
		assertNotNull(token);
		
		TOKEN = token;
	}

	
	@Test
	@Order(3)
	void save() throws Exception {
		assertEquals(1, authorRepository.count());
		
		String jsonRequest = objectMapper.writeValueAsString(userDTO);
		
		mockMvc.perform(post(URI + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.name", equalTo("Lucas")))
				.andExpect(jsonPath("$.username", equalTo("Purple")))
				.andExpect(jsonPath("$.email", equalTo("lucas@gmail.com")));
		
		
		assertEquals(2, authorRepository.count());
	}
	
	@Test
	void findById() throws Exception {
		
		Long authorId = authorRepository.findAll().get(1).getId();
		
		mockMvc.perform(get(URI + "/" + authorId)
				.header("Authorization", "Bearer " + TOKEN)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo("Lucas")))
				.andExpect(jsonPath("$.username", equalTo("Purple")))
				.andExpect(jsonPath("$.email", equalTo("lucas@gmail.com")));
	}
}
