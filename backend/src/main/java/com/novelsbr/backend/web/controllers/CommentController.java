package com.novelsbr.backend.web.controllers;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.domain.entities.Comment;
import com.novelsbr.backend.domain.projections.CommentProjection;
import com.novelsbr.backend.services.CommentService;
import com.novelsbr.backend.utils.mapper.CommentMapper;
import com.novelsbr.backend.web.api.CommentAPI;

@RestController
@CrossOrigin(origins = "${cors.allowed.origins}")
public class CommentController implements CommentAPI {
	
	@Autowired
	private CommentService commentService;

	@Operation(
			summary = "Listar comentários"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Comentários listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar comentários"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar comentários"),
	})
	@GetMapping
	public ResponseEntity<List<CommentDTO>> findAll() {
		List<Comment> comments = commentService.findAll();
		List<CommentDTO> dtos = comments.stream().map(x -> CommentMapper.toDTO(x)).toList();
		return ResponseEntity.ok(dtos);
	}

	@Operation(
			summary = "Buscar comentários por novel"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Comentários buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar comentários por novel"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar comentários por novel"),
	})
	@GetMapping("/novels/{novelId}")
	public ResponseEntity<List<CommentDTO>> findAllNovelsCommentsByNovelId(
			@PathVariable Long novelId) {
		List<CommentProjection> comments = commentService.findAllNovelsByEntityId(novelId);
		List<CommentDTO> dtos = comments.stream().map(x -> new CommentDTO(x)).toList();
		return ResponseEntity.ok(dtos);
	}

	@Operation(
			summary = "Buscar comentários por capítulo"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Comentários buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar comentários por capítulo"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar comentários por capítulo"),
	})
	@GetMapping("/chapters/{chapterId}")
	public ResponseEntity<List<CommentDTO>> findAllChaptersCommentsByNovelId(
			@PathVariable Long chapterId) {
		List<CommentProjection> comments = commentService.findAllChaptersByEntityId(chapterId);
		List<CommentDTO> dtos = comments.stream().map(x -> new CommentDTO(x)).toList();
		return ResponseEntity.ok(dtos);
	}

	@Operation(
			summary = "Cadastrar comentário"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Comentário cadastrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao cadastrar comentário"),
			@ApiResponse(responseCode = "500", description = "Error ao cadastrar comentário"),
	})
	@PostMapping("/")
	public ResponseEntity<CommentDTO> save(@RequestBody CommentDTO commentDTO) {
		Comment commentSaved = commentService.save(commentDTO);
		return ResponseEntity.status(201).body(CommentMapper.toDTO(commentSaved));
	}

	@Operation(
			summary = "Buscar comentário por id"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Comentário buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao buscar comentário por id"),
			@ApiResponse(responseCode = "500", description = "Error ao buscar comentário por id"),
	})
	@PutMapping("/{id}")
	public ResponseEntity<CommentDTO> update(
			@RequestBody CommentDTO commentDTO, @PathVariable Long id) {
		Comment commentUpdated = commentService.update(commentDTO, id);
		return ResponseEntity.ok(CommentMapper.toDTO(commentUpdated));
	}

	@Operation(
			summary = "Deletar comentário"
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Comentário deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Error ao deletar comentário"),
			@ApiResponse(responseCode = "500", description = "Error ao deletar comentário"),
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		commentService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
