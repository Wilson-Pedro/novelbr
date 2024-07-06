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

import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> save(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findAll(@PathVariable Long id) {
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
		return ResponseEntity.ok(userService.update(user, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
