package com.novelsbr.backend.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.services.AuthorService;
import com.novelsbr.backend.web.api.AuthorAPI;

@RestController
@CrossOrigin("http://localhost:3000")
public class AuhtorController implements AuthorAPI {

	@Autowired
	private AuthorService authorService;
	
	@PostMapping("/")
	public ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO authorDTO) {
		Author author = authorService.save(authorDTO);
		return ResponseEntity.status(201).body(new AuthorDTO(author));	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new AuthorDTO(authorService.findById(id)));
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<AuthorDTO> findByUsername(@PathVariable String username) {
		Author author = authorService.findByUsername(username);
		AuthorDTO dto = new AuthorDTO(author.getName(), author.getUsername(), author.getEmail());
		return ResponseEntity.ok(dto);
	}
}
