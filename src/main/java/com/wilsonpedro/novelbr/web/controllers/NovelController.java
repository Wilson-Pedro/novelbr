package com.wilsonpedro.novelbr.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilsonpedro.novelbr.dto.NovelDTO;
import com.wilsonpedro.novelbr.dto.ResquestIdDTO;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.services.interfaces.NovelService;
import com.wilsonpedro.novelbr.web.apis.NovelAPI;

import jakarta.validation.Valid;

@RestController
public class NovelController implements NovelAPI {
	
	@Autowired
	private NovelService novelService;
	
	@Override
	public ResponseEntity<NovelDTO> save(@Valid @RequestBody NovelDTO novelDTO) {
		Novel novelSaved = novelService.save(novelDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new NovelDTO(novelSaved));
	}
	
	@Override
	public ResponseEntity<List<NovelDTO>> findAll() {
		List<Novel> list = novelService.findAll();
		return ResponseEntity.ok(list.stream().map(x -> new NovelDTO(x)).toList());
	}
	
	@Override
	public ResponseEntity<Page<NovelDTO>> page(Pageable pageable) {
		Page<Novel> pages = novelService.findAll(pageable);
		return ResponseEntity.ok(pages.map(NovelDTO::new));
	}
	
	@Override
	public ResponseEntity<NovelDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new NovelDTO(novelService.findById(id)));
	}
	
	@Override
	public ResponseEntity<NovelDTO> update(@Valid @RequestBody NovelDTO novelDTO, @PathVariable Long id) {
		Novel novelUpdated = novelService.update(novelDTO, id);
		return ResponseEntity.ok(new NovelDTO(novelUpdated));
	}
	
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		novelService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Void> deleteAllByNovel(@RequestBody ResquestIdDTO resquestIdDTO) {
		novelService.deleteAllByNovel(resquestIdDTO.getRequestId());
		return ResponseEntity.noContent().build();
	}
}
