package com.novelsbr.backend.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.GenreDTO;
import com.novelsbr.backend.domain.entities.Genre;
import com.novelsbr.backend.services.GenreService;
import com.novelsbr.backend.web.api.GenderAPI;

@RestController
@CrossOrigin("http://localhost:3000")
public class GenderController implements GenderAPI {

	@Autowired
	GenreService genreService;
	
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll() {
		List<GenreDTO> gendersDTO = genreService.findAllGenders().stream()
				.map(x -> new GenreDTO(x)).toList();
		return ResponseEntity.ok(gendersDTO);
	}
	
	@GetMapping("/novel/{novelId}")
	public ResponseEntity<List<GenreDTO>> findGendersByNovelId(@PathVariable Long novelId) {
		List<Genre> genres = genreService.findGendersByNovelId(novelId);
		List<GenreDTO> dtos = genres.stream().map(x ->  new GenreDTO(x)).toList();
		return ResponseEntity.ok(dtos);
	}
}
