package com.wilsonpedro.novelbr.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wilsonpedro.novelbr.dto.AuthenticationDTO;
import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.enums.UserType;
import com.wilsonpedro.novelbr.infra.security.TokenService;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationTest {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private ChapterRepository chapterRepository;
	
	@Autowired
	private NovelRepository novelRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Test
	@Order(1)
	void register() {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		userRepository.deleteAll();
		
		assertEquals(0, userRepository.count());
		
		String encryptedPassword = new BCryptPasswordEncoder().encode("123");
		
		assertNotEquals(encryptedPassword, "123");
		
		User user = new User(null, "Cronos", UserType.AUTHOR, "cronos@gmail.com", encryptedPassword);
		
		userRepository.save(user);
		
		assertEquals(UserType.AUTHOR, user.getUserType());
		assertEquals(1, userRepository.count());
	}

	@Test
	void login() {
		AuthenticationDTO authenticationDTO = new AuthenticationDTO("Cronos", "123");
		var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = this.tokenService.generateToken((User) auth.getPrincipal());
		
		assertNotNull(token);
	}
}
