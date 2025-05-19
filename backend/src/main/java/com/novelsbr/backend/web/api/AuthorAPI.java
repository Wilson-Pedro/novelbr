package com.novelsbr.backend.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelsbr.backend.domain.dto.AuthorDTO;

@RequestMapping("/authors")	
public interface AuthorAPI {

	@PostMapping("/")
	public ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO authorDTO);
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthorDTO> findById(@PathVariable Long id);
}
