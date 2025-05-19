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

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.ChapterTextDTO;
import com.novelsbr.backend.domain.dto.NovelsChapterTitleDTO;
import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.services.ChapterService;
import com.novelsbr.backend.web.api.ChapterAPI;

@RestController
@CrossOrigin("http://localhost:3000")
public class ChapterController implements ChapterAPI {
	
	@Autowired
	private ChapterService chapterService;

	@PostMapping("/")
	public ResponseEntity<ChapterDTO> save(@RequestBody ChapterDTO chapterDTO) {
		Chapter chapter = chapterService.save(chapterDTO);
		return ResponseEntity.status(201).body(new ChapterDTO(chapter));
	}
	
	@GetMapping("/novelsTile/novel/{novelId}")
	public ResponseEntity<List<NovelsChapterTitleDTO>> findNovelsChapterTilte(
			@PathVariable Long novelId) {
		return ResponseEntity.ok(chapterService.findAllNovelsChapterTitleByNovelId(novelId));
	}
	
	@GetMapping("/{novelName}/{chapterNumber}")
	public ResponseEntity<ChapterTextDTO> findChapterText(
			@PathVariable Integer chapterNumber, @PathVariable String novelName) {
		return ResponseEntity.ok(chapterService.findChapterText(chapterNumber, novelName));
	}
}
