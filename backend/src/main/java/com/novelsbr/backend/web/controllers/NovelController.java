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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.novelsbr.backend.domain.dto.AuthorNovelInfoDTO;
import com.novelsbr.backend.domain.dto.CardNovelDTO;
import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.domain.dto.PageResponseDTO;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.records.ChangeStatusNovelRequest;
import com.novelsbr.backend.services.NovelService;
import com.novelsbr.backend.web.api.NovelAPI;

@RestController
@CrossOrigin(origins = "${cors.allowed.origins}")
public class NovelController implements NovelAPI {
	
	@Autowired
	private NovelService novelService;

	@Operation(
			summary = "Listar Novels"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novels listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao listar novels"),
			@ApiResponse(responseCode = "500", description = "Error ao listar novels"),
	})
	@GetMapping
	public ResponseEntity<List<NovelDTO>> findAll() {
		List<NovelDTO> novlesDTO = novelService.findAll()
				.stream().map(NovelDTO::new).toList();
		return ResponseEntity.ok(novlesDTO);
	}

	@Operation(
			summary = "Cadastrar Novel"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novel cadastrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao cadastrar novel"),
			@ApiResponse(responseCode = "500", description = "Error ao cadastrar novel"),
	})
	@PostMapping("/")
	public ResponseEntity<NovelDTO> save(@RequestBody NovelDTO novelDTO) {
		Novel novel = novelService.save(novelDTO);
		return ResponseEntity.status(201).body(new NovelDTO(novel));
	}

	@Operation(
			summary = "Listar Novel cards"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novel cards listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao listar novel cards"),
			@ApiResponse(responseCode = "500", description = "Error ao listar novel cards"),
	})
	@GetMapping("/novelCards")
	public ResponseEntity<List<CardNovelDTO>> findNovelCards() {
		return ResponseEntity.ok(novelService.findNovelCards());
	}

	@Operation(
			summary = "Buscar Novels por autor"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novels buscadas com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar novel por autor"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar novel por autor"),
	})
	@GetMapping("/novelCards/author/{username}")
	public ResponseEntity<List<CardNovelDTO>> findNovelCardsByUsername(@PathVariable String username) {
		return ResponseEntity.ok(novelService.findNovelCardsByUsername(username));
	}


	@Operation(
			summary = "Buscar Novel Info por Novel"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novel info buscada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar novel info"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar novel info"),
	})
	@GetMapping("/novelCards/{novelId}")
	public ResponseEntity<AuthorNovelInfoDTO> findNovelInfoByNovelId(@PathVariable Long novelId) {
		return ResponseEntity.ok(novelService.findNovelInfoByNovelId(novelId));
	}

	@Operation(
			summary = "Buscar Novel por nome"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novel buscada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar novel por nome"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar novel por nome"),
	})
	@GetMapping("/{novelName}")
	public ResponseEntity<NovelDTO> findNovelByNovelName(@PathVariable String novelName) {
		Novel novel = novelService.findNovelByNovelName(novelName);
		return ResponseEntity.ok(new NovelDTO(novel));
	}

	@Operation(
			summary = "Buscar Novels por gêneros"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novel buscada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar novels por gênro"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar novels por gênro"),
	})
	@GetMapping("/genders")
	public ResponseEntity<Page<CardNovelDTO>> findNovelCardsByGenders(
			@RequestParam(required = false) List<String> genders,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		if(genders.isEmpty() || genders == null) {
			return ResponseEntity.ok(novelService.findAll(page, size)
					.map(CardNovelDTO::new));
		}
		Page<CardNovelDTO> cardNovels = novelService.findNovelCardsByGenders(genders, page, size);
		return ResponseEntity.ok(cardNovels);
	}

	@Operation(
			summary = "Buscar Novel cards por nome"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novel cards buscada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar novel cards por nome"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar novel cards por nome"),
	})
	@GetMapping("/search/{novelName}")
	public ResponseEntity<PageResponseDTO<CardNovelDTO>> searchNovel(
			@PathVariable String novelName,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		
		Page<CardNovelDTO> novlesDTO = novelService.searchNovel(novelName, page, size)
				.map(CardNovelDTO::new);
		return ResponseEntity.ok(new PageResponseDTO<>(novlesDTO));
	}

	@Operation(
			summary = "Novels Paginadas"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novel paginadas com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao paginar novels"),
			@ApiResponse(responseCode = "500", description = "Error ao paginar novels"),
	})
	@GetMapping("/pages")
	public ResponseEntity<PageResponseDTO<CardNovelDTO>> pages(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Page<CardNovelDTO> novlesDTO = novelService.findAll(page, size)
				.map(CardNovelDTO::new);
		return ResponseEntity.ok(new PageResponseDTO<>(novlesDTO));
	}

	@Operation(
			summary = "Atualizar Novel status"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Novel status atualizada com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao atualizar novel status"),
			@ApiResponse(responseCode = "500", description = "Error ao atualizar novel status\""),
	})
	@PatchMapping("/changeNovelStatus")
	public ResponseEntity<Void> changeNovelStatus(@RequestBody ChangeStatusNovelRequest request) {
		novelService.changeNovelStatus(request.novelId(), request.novelStatusId());
		return ResponseEntity.noContent().build();
	}

	@Operation(
			summary = "Atualizar capa de Novel"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Capa da Novel atualizada com sucesso."),
			@ApiResponse(responseCode = "400", description = "Error ao atualizar capa da novel"),
			@ApiResponse(responseCode = "500", description = "Error ao atualizar capa da novel"),
	})
	@PatchMapping("/changeNovelImageUri")
	public ResponseEntity<Void> changeNovelImageUri(
			@RequestParam("file") MultipartFile file,
			@RequestParam("novelId") Long novelId,
			@RequestParam("imageUri") String imageUri) {
		novelService.changeNovelImageUri(novelId, imageUri, file);
		return ResponseEntity.noContent().build();
	}
}
