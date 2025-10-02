package com.novelsbr.backend.web.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelsbr.backend.domain.dto.GenreDTO;

@RequestMapping("/genres")
public interface GenderAPI {

	@GetMapping
	ResponseEntity<List<GenreDTO>> findAll();
	
	@GetMapping("/novel/{novelId}")
	ResponseEntity<List<String>> findGendersByNovelId(@PathVariable Long novelId);
}
