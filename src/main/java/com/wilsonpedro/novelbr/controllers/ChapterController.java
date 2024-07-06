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

import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.services.ChapterService;

@RestController
@RequestMapping("/chapters")
public class ChapterController {
	
	@Autowired
	private ChapterService chapterService;
	
	@PostMapping("/")
	public ResponseEntity<Chapter> save(@RequestBody Chapter chapter) {
		return ResponseEntity.status(HttpStatus.CREATED).body(chapterService.save(chapter));
	}
	
	@GetMapping
	public ResponseEntity<List<Chapter>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(chapterService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Chapter> findAll(@PathVariable Long id) {
		Optional<Chapter> userFinded = chapterService.findById(id);
		if(userFinded.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(userFinded.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Chapter> update(@RequestBody Chapter chapter, @PathVariable Long id) {
		return ResponseEntity.ok(chapterService.update(chapter, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		chapterService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
