package com.novelsbr.backend.web.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.novelsbr.backend.domain.dto.GenderDTO;

@RequestMapping("/genders")
public interface GenderAPI {

	@GetMapping
	public ResponseEntity<List<GenderDTO>> findAll();
	
	@GetMapping("/novel/{novelId}")
	public ResponseEntity<List<String>> findGendersByNovelId(@PathVariable Long novelId);
}
