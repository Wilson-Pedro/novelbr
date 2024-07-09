package com.wilsonpedro.novelbr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilsonpedro.novelbr.dto.UserDTO;
import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO) {
		User userSaved = userService.save(new User(userDTO));
		return ResponseEntity.ok(new UserDTO(userSaved));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).toList();
		return ResponseEntity.status(HttpStatus.OK).body(listDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findAll(@PathVariable Long id) {
		return ResponseEntity.ok(new UserDTO(userService.findById(id)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) {
		User userUpdated = userService.update(new User(userDTO), id);
		return ResponseEntity.ok(new UserDTO(userUpdated));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
