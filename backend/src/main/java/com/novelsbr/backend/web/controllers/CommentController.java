package com.novelsbr.backend.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.services.CommentService;
import com.novelsbr.backend.web.api.CommentAPI;

@RestController
@CrossOrigin("http://localhost:3000")
public class CommentController implements CommentAPI {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/")
	public ResponseEntity<Void> save(CommentDTO commentDTO) {
		commentService.save(commentDTO);
		return ResponseEntity.status(201).build();
	}

}
