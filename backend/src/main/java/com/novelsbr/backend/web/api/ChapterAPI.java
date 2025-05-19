package com.novelsbr.backend.web.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.ChapterTextDTO;
import com.novelsbr.backend.domain.dto.NovelsChapterTitleDTO;

@RequestMapping("/chapters")
public interface ChapterAPI {

	@PostMapping("/")
	public ResponseEntity<ChapterDTO> save(@RequestBody ChapterDTO chapterDTO);
	
	@GetMapping("/novelsTile/novel/{novelId}")
	public ResponseEntity<List<NovelsChapterTitleDTO>> findNovelsChapterTilte(
			@PathVariable Long novelId);
	
	@GetMapping("/{novelName}/{chapterNumber}")
	public ResponseEntity<ChapterTextDTO> findChapterText(
			@PathVariable Integer chapterNumber, @PathVariable String novelName);
}
