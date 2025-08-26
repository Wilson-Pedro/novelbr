package com.novelsbr.backend.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.ChapterTextDTO;
import com.novelsbr.backend.domain.dto.LastChaptersDTO;
import com.novelsbr.backend.domain.dto.NovelsChapterTitleDTO;
import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.domain.records.ChapterNumberDTO;
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
	
	@GetMapping("/novelsTitle/novel/{novelId}")
	public ResponseEntity<List<NovelsChapterTitleDTO>> findNovelsChapterTilte(
			@PathVariable Long novelId) {
		return ResponseEntity.ok(chapterService.findAllNovelsChapterTitleByNovelId(novelId));
	}
	
	@GetMapping("/pages/novelsTitle/{novelId}")
	public Page<NovelsChapterTitleDTO> chapterPagesByNovel(
			@PathVariable Long novelId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2") int size) {
		Page<Chapter> pages = chapterService.findChapterPagesByNovel(page, size, novelId);
		Page<NovelsChapterTitleDTO> pagesDto = pages
				.map(x -> new NovelsChapterTitleDTO(x, pages.getTotalPages()));
		return pagesDto;
	}
	
	@GetMapping("/{novelName}/{chapterNumber}") 
	public ResponseEntity<ChapterTextDTO> findChapterText(
			@PathVariable Integer chapterNumber, @PathVariable String novelName) {
		return ResponseEntity.ok(chapterService.findChapterText(chapterNumber, novelName));
	}
	
	@GetMapping("/chapterNumber/novel/{novelId}") 
	public ResponseEntity<ChapterNumberDTO> findMaxChapterNumberByNovelId(
			@PathVariable Long novelId) {
		Integer chapterNumber = chapterService.findMaxChapterNumber(novelId);
		ChapterNumberDTO chapterNumberDTO = new ChapterNumberDTO(chapterNumber);
		return ResponseEntity.ok(chapterNumberDTO);
	}
	
	@GetMapping("/lastChapters") 
	public ResponseEntity<List<LastChaptersDTO>> findLastChapters() {
		return ResponseEntity.ok(chapterService.findLastChapters());
	}
}
