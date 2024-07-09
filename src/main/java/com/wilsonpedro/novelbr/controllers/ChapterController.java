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

import com.wilsonpedro.novelbr.dto.ChapterDTO;
import com.wilsonpedro.novelbr.dto.ResquestIdDTO;
import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.services.ChapterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/chapters")
public class ChapterController {
	
	@Autowired
	private ChapterService chapterService;
	
	@PostMapping("/")
	public ResponseEntity<ChapterDTO> save(@Valid @RequestBody ChapterDTO chapterRequestDTO) {
		Chapter chapterSaved = chapterService.save(chapterRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ChapterDTO(chapterSaved));
	}
	
	@GetMapping
	public ResponseEntity<List<ChapterDTO>> findAll() {
		List<Chapter> list = chapterService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(
				list.stream().map(x -> new ChapterDTO(x)).toList());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ChapterDTO> findAll(@PathVariable Long id) {
		return ResponseEntity.ok(new ChapterDTO(chapterService.findById(id)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ChapterDTO> update(@Valid @RequestBody ChapterDTO chapterDTO, @PathVariable Long id) {
		Chapter chapterUpdated = chapterService.update(chapterDTO, id);
		return ResponseEntity.ok(new ChapterDTO(chapterUpdated));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		chapterService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/deleteAllByNovel")
	public ResponseEntity<Void> deleteAllByNovel(@RequestBody ResquestIdDTO resquestIdDTO) {
		chapterService.deleteAllByNovel(resquestIdDTO.getRequestId());
		return ResponseEntity.noContent().build();
	}
}
