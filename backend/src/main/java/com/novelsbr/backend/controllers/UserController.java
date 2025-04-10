package com.novelsbr.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.UserDTO;
import com.novelsbr.backend.domain.entities.User;
import com.novelsbr.backend.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
		User user = userService.save(userDTO);
		return ResponseEntity.status(201).body(new UserDTO(user));
		
	}
	
}
