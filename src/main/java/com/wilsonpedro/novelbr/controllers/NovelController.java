package com.wilsonpedro.novelbr.controllers;

import java.util.List;
import java.util.Optional;

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

import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.services.NovelService;

@RestController
@RequestMapping("/novels")
public class NovelController {
	
	@Autowired
	private NovelService novelService;
	
	@PostMapping("/")
	public ResponseEntity<Novel> save(@RequestBody Novel novel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(novelService.save(novel));
	}
	
	@GetMapping
	public ResponseEntity<List<Novel>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(novelService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Novel> findAll(@PathVariable Long id) {
		Optional<Novel> userFinded = novelService.findById(id);
		if(userFinded.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(userFinded.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Novel> update(@RequestBody Novel novel, @PathVariable Long id) {
		return ResponseEntity.ok(novelService.update(novel, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		novelService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
