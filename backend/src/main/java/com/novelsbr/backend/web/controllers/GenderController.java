package com.novelsbr.backend.web.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@CrossOrigin(origins = "${cors.allowed.origins}")
public class GenderController implements GenderAPI {

	@Autowired
	GenreService genreService;

	@Operation(
			summary = "Listar gêneros"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Gêneros listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao listar gêneros"),
			@ApiResponse(responseCode = "500", description = "Error ao listar gêneros"),
	})
	@GetMapping
	public ResponseEntity<List<GenreDTO>> findAll() {
		List<GenreDTO> gendersDTO = genreService.findAllGenders().stream()
				.map(GenreDTO::new).toList();
		return ResponseEntity.ok(gendersDTO);
	}

	@Operation(
			summary = "Buscar gêneros por novel"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Gêneros buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar gênros por novel"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar gênros por novel"),
	})
	@GetMapping("/novel/{novelId}")
	public ResponseEntity<List<GenreDTO>> findGendersByNovelId(@PathVariable Long novelId) {
		List<Genre> genres = genreService.findGendersByNovelId(novelId);
		List<GenreDTO> dtos = genres.stream().map(GenreDTO::new).toList();
		return ResponseEntity.ok(dtos);
	}
}
