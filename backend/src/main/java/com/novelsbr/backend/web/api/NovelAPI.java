package com.novelsbr.backend.web.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelsbr.backend.domain.dto.AuthorNovelInfoDTO;
import com.novelsbr.backend.domain.dto.CardNovelDTO;
import com.novelsbr.backend.domain.dto.NovelDTO;

@RequestMapping("/novels")
public interface NovelAPI {
	
	
	@GetMapping()
	public ResponseEntity<List<NovelDTO>> findAll();
	
	@PostMapping("/")
	public ResponseEntity<NovelDTO> save(@RequestBody NovelDTO novelDTO);
	
	@GetMapping("/novelCards")
	public ResponseEntity<List<CardNovelDTO>> findNovelCards();

	@GetMapping("/novelCards/author/{username}")
	public ResponseEntity<List<CardNovelDTO>> findNovelCardsByUsername(@PathVariable String username);
	
	@GetMapping("/novelCards/{novelId}")
	public ResponseEntity<AuthorNovelInfoDTO> findNovelInfoByNovelId(@PathVariable Long novelId);

	@GetMapping("/search")
	public ResponseEntity<List<NovelDTO>> searchNovel(@PathVariable String novelName);
}
