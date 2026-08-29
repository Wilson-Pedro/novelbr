package com.novelsbr.backend.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.services.AuthorService;
import com.novelsbr.backend.web.api.AuthorAPI;

@RestController
@CrossOrigin(origins = "${cors.allowed.origins}")
public class AuhtorController implements AuthorAPI {

	@Autowired
	private AuthorService authorService;

	@Operation(
			summary = "Cadastrar autor"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Autor cadastrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao cadastrar autor"),
			@ApiResponse(responseCode = "500", description = "Error ao cadastrar autor"),
	})
	@PostMapping("/")
	public ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO authorDTO) {
		Author author = authorService.save(authorDTO);
		return ResponseEntity.status(201).body(new AuthorDTO(author));	
	}

	@Operation(
			summary = "Buscar Autor"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar autor"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar autor"),
	})
	@GetMapping("/{id}")
	public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new AuthorDTO(authorService.findById(id)));
	}

	@Operation(
			summary = "Buscar Autor por username"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Autor encontrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar autor por username"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar autor username"),
	})
	@GetMapping("/username/{username}")
	public ResponseEntity<AuthorDTO> findByUsername(@PathVariable String username) {
		Author author = authorService.findByUsername(username);
		AuthorDTO dto = new AuthorDTO(author.getName(), author.getUsername(), author.getEmail());
		return ResponseEntity.ok(dto);
	}
}
