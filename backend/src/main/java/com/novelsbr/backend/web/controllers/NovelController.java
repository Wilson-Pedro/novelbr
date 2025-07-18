package com.novelsbr.backend.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.AuthorNovelInfoDTO;
import com.novelsbr.backend.domain.dto.CardNovelDTO;
import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.services.NovelService;
import com.novelsbr.backend.web.api.NovelAPI;

@RestController
@CrossOrigin("http://localhost:3000")
public class NovelController implements NovelAPI {
	
	@Autowired
	private NovelService novelService;
	
	@GetMapping
	public ResponseEntity<List<NovelDTO>> findAll() {
		List<NovelDTO> novlesDTO = novelService.findAll()
				.stream().map(x -> new NovelDTO(x)).toList();
		return ResponseEntity.ok(novlesDTO);
	}

	@PostMapping("/")
	public ResponseEntity<NovelDTO> save(@RequestBody NovelDTO novelDTO) {
		Novel novel = novelService.save(novelDTO);
		return ResponseEntity.status(201).body(new NovelDTO(novel));
	}
	
	@GetMapping("/novelCards")
	public ResponseEntity<List<CardNovelDTO>> findNovelCards() {
		return ResponseEntity.ok(novelService.findNovelCards());
	}
	
	@GetMapping("/novelCards/author/{username}")
	public ResponseEntity<List<CardNovelDTO>> findNovelCardsByUsername(@PathVariable String username) {
		return ResponseEntity.ok(novelService.findNovelCardsByUsername(username));
	}
	
	@GetMapping("/novelCards/{novelId}")
	public ResponseEntity<AuthorNovelInfoDTO> findNovelInfoByNovelId(@PathVariable Long novelId) {
		return ResponseEntity.ok(novelService.findNovelInfoByNovelId(novelId));
	}
	
	@GetMapping("/search/{novelName}")
	public ResponseEntity<List<NovelDTO>> searchNovel(@PathVariable String novelName) {
		List<NovelDTO> novlesDTO = novelService.searchNovel(novelName)
				.stream().map(x -> new NovelDTO(x)).toList();
		return ResponseEntity.ok(novlesDTO);
	}
}
