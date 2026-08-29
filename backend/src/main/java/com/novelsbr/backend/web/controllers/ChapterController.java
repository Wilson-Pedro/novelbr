package com.novelsbr.backend.web.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@CrossOrigin(origins = "${cors.allowed.origins}")
public class ChapterController implements ChapterAPI {
	
	@Autowired
	private ChapterService chapterService;

	@Operation(
			summary = "Cadastrar capítulo"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Capítulo cadastrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao cadastrar capítulo"),
			@ApiResponse(responseCode = "500", description = "Error ao cadastrar capítulo"),
	})
	@PostMapping("/")
	public ResponseEntity<ChapterDTO> save(@RequestBody ChapterDTO chapterDTO) {
		Chapter chapter = chapterService.save(chapterDTO);
		return ResponseEntity.status(201).body(new ChapterDTO(chapter));
	}


	@Operation(
			summary = "Buscar títulos de capítulos da novel"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Títulos de capítulo buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar títulos"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar títulos"),
	})
	@GetMapping("/novelsTitle/novel/{novelId}")
	public ResponseEntity<List<NovelsChapterTitleDTO>> findNovelsChapterTilte(
			@PathVariable Long novelId) {
		return ResponseEntity.ok(chapterService.findAllNovelsChapterTitleByNovelId(novelId));
	}

	@Operation(
			summary = "Buscar capítulos por novel"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Capítulos da novel buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar capítulos de novel"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar capítulos de novel"),
	})
	@GetMapping("/pages/novelsTitle/{novelId}")
	public Page<NovelsChapterTitleDTO> chapterPagesByNovel(
			@PathVariable Long novelId,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "12") int size) {
		Page<Chapter> pages = chapterService.findChapterPagesByNovel(page, size, novelId);
		Page<NovelsChapterTitleDTO> pagesDto = pages
				.map(x -> new NovelsChapterTitleDTO(x, pages.getTotalPages()));
		return pagesDto;
	}

	@Operation(
			summary = "Buscar texto do capítulo"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Texto do capítulo buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar texto do capítulo"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar capítulos do capítulo"),
	})
	@GetMapping("/{novelName}/{chapterNumber}") 
	public ResponseEntity<ChapterTextDTO> findChapterText(
			@PathVariable Integer chapterNumber, @PathVariable String novelName) {
		return ResponseEntity.ok(chapterService.findChapterText(chapterNumber, novelName));
	}

	@Operation(
			summary = "Buscar número do capítulo"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Número do capítulo buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar número do capítulo"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar número do capítulo"),
	})
	@GetMapping("/chapterNumber/novel/{novelId}") 
	public ResponseEntity<ChapterNumberDTO> findMaxChapterNumberByNovelId(
			@PathVariable Long novelId) {
		Integer chapterNumber = chapterService.findMaxChapterNumber(novelId);
		ChapterNumberDTO chapterNumberDTO = new ChapterNumberDTO(chapterNumber);
		return ResponseEntity.ok(chapterNumberDTO);
	}

	@Operation(
			summary = "Buscar últimos capítulos"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "últimos capítulos buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar últimos capítulos"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar últimos capítulos"),
	})
	@GetMapping("/lastChapters") 
	public ResponseEntity<List<LastChaptersDTO>> findLastChapters() {
		return ResponseEntity.ok(chapterService.findLastChapters());
	}
}
