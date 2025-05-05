package com.novelsbr.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.GenderDTO;
import com.novelsbr.backend.services.GenderService;

@RestController
@RequestMapping("/genders")
@CrossOrigin("http://localhost:3000")
public class GenderController {

	@Autowired
	GenderService genderService;
	
	@GetMapping
	public ResponseEntity<List<GenderDTO>> findAll() {
		List<GenderDTO> gendersDTO = genderService.findAllGenders().stream()
				.map(x -> new GenderDTO(x)).toList();
		return ResponseEntity.ok(gendersDTO);
	}
	
	@GetMapping("/novel/{novelId}")
	public ResponseEntity<List<String>> findGendersByNovelId(@PathVariable Long novelId) {
		List<String> genders = genderService.findGendersByNovelId(novelId);
		return ResponseEntity.ok(genders);
	}
}
