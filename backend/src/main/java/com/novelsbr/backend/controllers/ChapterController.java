package com.novelsbr.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.services.ChapterService;

@RestController
@RequestMapping("/chapters")
@CrossOrigin("*")
public class ChapterController {
	
	@Autowired
	private ChapterService chapterService;

	@PostMapping("/")
	public ResponseEntity<ChapterDTO> save(@RequestBody ChapterDTO chapterDTO) {
		Chapter chapter = chapterService.save(chapterDTO);
		return ResponseEntity.status(201).body(new ChapterDTO(chapter));
	}
}
