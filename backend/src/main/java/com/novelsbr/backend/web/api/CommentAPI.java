package com.novelsbr.backend.web.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelsbr.backend.domain.dto.CommentDTO;

@RequestMapping("/comments")
public interface CommentAPI {

	@PostMapping("/")
	ResponseEntity<Void> save(@RequestBody CommentDTO commentDTO);
	
	@GetMapping
	public ResponseEntity<List<CommentDTO>> findAll();
}
