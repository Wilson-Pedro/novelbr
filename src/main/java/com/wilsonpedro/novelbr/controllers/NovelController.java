package com.wilsonpedro.novelbr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilsonpedro.novelbr.dto.NovelDTO;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.services.NovelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/novels")
public class NovelController {
	
	@Autowired
	private NovelService novelService;
	
	@PostMapping("/")
	public ResponseEntity<NovelDTO> save(@Valid @RequestBody NovelDTO novelDTO) {
		Novel novelSaved = novelService.save(novelDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new NovelDTO(novelSaved));
	}
	
	@GetMapping
	public ResponseEntity<List<NovelDTO>> findAll() {
		List<Novel> list = novelService.findAll();
		return ResponseEntity.ok(list.stream().map(x -> new NovelDTO(x)).toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NovelDTO> findAll(@PathVariable Long id) {
		return ResponseEntity.ok(new NovelDTO(novelService.findById(id)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<NovelDTO> update(@Valid @RequestBody NovelDTO novelDTO, @PathVariable Long id) {
		Novel novelUpdated = novelService.update(novelDTO, id);
		return ResponseEntity.ok(new NovelDTO(novelUpdated));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		novelService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
