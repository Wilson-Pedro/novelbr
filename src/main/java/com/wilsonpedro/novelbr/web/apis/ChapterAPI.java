package com.wilsonpedro.novelbr.web.apis;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wilsonpedro.novelbr.dto.ChapterDTO;
import com.wilsonpedro.novelbr.dto.ResquestIdDTO;

import jakarta.validation.Valid;

@RequestMapping("/chapters")
public interface ChapterAPI {

	@PostMapping(produces = "application/json", path = "/")
	ResponseEntity<ChapterDTO> save(@Valid @RequestBody ChapterDTO chapterRequestDTO);
	
	@GetMapping(produces = "application/json", path = "")
	ResponseEntity<List<ChapterDTO>> findAll();
	
	@GetMapping(produces = "application/json", path = "/pages")
	ResponseEntity<Page<ChapterDTO>> pages(Pageable pageable);
	
	@GetMapping(produces = "application/json", path = "/{id}")
	ResponseEntity<ChapterDTO> findById(@PathVariable Long id);
	
	@PutMapping(produces = "application/json", path = "/{id}")
	ResponseEntity<ChapterDTO> update(@Valid @RequestBody ChapterDTO chapterDTO, @PathVariable Long id);
	
	@DeleteMapping(produces = "application/json", path = "/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id);
	
	@DeleteMapping(produces = "application/json", path = "/deleteAllByNovel")
	ResponseEntity<Void> deleteAllByNovel(@RequestBody ResquestIdDTO resquestIdDTO);
}
