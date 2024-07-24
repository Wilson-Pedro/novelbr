package com.wilsonpedro.novelbr.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilsonpedro.novelbr.dto.AuthenticationDTO;
import com.wilsonpedro.novelbr.dto.UserDTO;
import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.enums.UserType;
import com.wilsonpedro.novelbr.infra.security.TokenService;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	TokenService tokenService;
	
	User user = new User(null, "Cronos", UserType.AUTHOR, "cronos@gmail.com", "123");
	User userLogin = new User(null, "Pablo", UserType.ADMIN, "pablo@gmail.com", "123");

	@BeforeEach
	void setUp() throws Exception {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	void save() throws Exception{
		register(userLogin);
		String token = getToken(new AuthenticationDTO(userLogin.getPseudonym(), userLogin.getPassword()));
		
		String jsonRequest = objectMapper.writeValueAsString(new UserDTO(user));
		
		
		mockMvc.perform(post("/users/")
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(jsonPath("$.pseudonym", equalTo("Cronos")))
				.andExpect(jsonPath("$.email", equalTo("cronos@gmail.com")))
				.andExpect(jsonPath("$.password", equalTo("123")))
				.andExpect(status().isCreated());
		
		assertEquals(2, userRepository.count());
	}
	
	@Test
	void pages() throws Exception{
		register(userLogin);
		String token = getToken(new AuthenticationDTO(userLogin.getPseudonym(), userLogin.getPassword()));
		
		userRepository.save(new User(null, "Cronos 1", UserType.AUTHOR, "cronos1@gmail.com", "123"));
		userRepository.save(new User(null, "Cronos 2", UserType.AUTHOR, "cronos2@gmail.com", "123"));
		userRepository.save(new User(null, "Cronos 3", UserType.AUTHOR, "cronos3@gmail.com", "123"));
		
		mockMvc.perform(get("/users/pages")
				.header("Authorization", "Bearer " + token)
				.param("page", "0")
				.param("size", "3")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void findAll() throws Exception{
		register(userLogin);
		String token = getToken(new AuthenticationDTO(userLogin.getPseudonym(), userLogin.getPassword()));
		
		userRepository.save(user);
		
		mockMvc.perform(get("/users")
				.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}
	
	@Test
	void findById() throws Exception{
		register(userLogin);
		String token = getToken(new AuthenticationDTO(userLogin.getPseudonym(), userLogin.getPassword()));
		
		userRepository.save(user);
		
		Long id = userRepository.findAll().get(1).getId();
		
		mockMvc.perform(get("/users/{id}", id)
				.header("Authorization", "Bearer " + token))
				.andExpect(jsonPath("$.pseudonym", equalTo("Cronos")))
				.andExpect(jsonPath("$.email", equalTo("cronos@gmail.com")))
				.andExpect(jsonPath("$.password", equalTo("123")))
				.andExpect(status().isOk());
	}

	@Test
	void update() throws Exception{
		register(userLogin);
		String token = getToken(new AuthenticationDTO(userLogin.getPseudonym(), userLogin.getPassword()));
		
		userRepository.save(user);
		user.setPseudonym("Cronos 2");
		
		Long id = userRepository.findAll().get(1).getId();
		String jsonRequest = objectMapper.writeValueAsString(new UserDTO(user));
		
		mockMvc.perform(put("/users/{id}", id)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(jsonPath("$.pseudonym", equalTo("Cronos 2")))
				.andExpect(status().isOk());
	}
	
	@Test
	void delete() throws Exception{
		register(userLogin);
		String token = getToken(new AuthenticationDTO(userLogin.getPseudonym(), userLogin.getPassword()));
		
		userRepository.save(user);
		assertEquals(2, userRepository.count());
		
		Long id = userRepository.findAll().get(1).getId();
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", id)
				.header("Authorization", "Bearer " + token))
				.andExpect(status().isNoContent());
		
		assertEquals(1, userRepository.count());
	}
	
	private void register(User user) {
		String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		User userRegister = new User(user.getId(), user.getPseudonym(), user.getUserType(), user.getEmail(), encryptedPassword);
		userRepository.save(userRegister);
	}
	
	private String getToken(AuthenticationDTO authenticationDTO) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		return this.tokenService.generateToken((User) auth.getPrincipal());
	}
}
