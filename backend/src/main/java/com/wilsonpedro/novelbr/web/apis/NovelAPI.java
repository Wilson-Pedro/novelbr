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

import com.wilsonpedro.novelbr.dto.NovelDTO;
import com.wilsonpedro.novelbr.dto.ResquestIdDTO;

import jakarta.validation.Valid;

@RequestMapping("/novels")
public interface NovelAPI {

	@PostMapping(produces = "application/json", path="/")
	ResponseEntity<NovelDTO> save(@Valid @RequestBody NovelDTO novelDTO);
	
	@GetMapping(produces = "application/json", path="")
	ResponseEntity<List<NovelDTO>> findAll();
	
	@GetMapping(produces = "application/json", path="/pages")
	ResponseEntity<Page<NovelDTO>> page(Pageable pageable);
	
	@GetMapping(produces = "application/json", path="/{id}")
	ResponseEntity<NovelDTO> findById(@PathVariable Long id);
	
	@PutMapping(produces = "application/json", path="/{id}")
	ResponseEntity<NovelDTO> update(@Valid @RequestBody NovelDTO novelDTO, @PathVariable Long id);
	
	@DeleteMapping(produces = "application/json", path="/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id);
	
	@DeleteMapping(produces = "application/json", path="/deleteAllByAuthor")
	ResponseEntity<Void> deleteAllByNovel(@RequestBody ResquestIdDTO resquestIdDTO);
}
