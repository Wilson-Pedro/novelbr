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

import com.wilsonpedro.novelbr.dto.ChapterDTO;
import com.wilsonpedro.novelbr.dto.ResquestIdDTO;
import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.services.interfaces.ChapterService;
import com.wilsonpedro.novelbr.web.apis.ChapterAPI;

import jakarta.validation.Valid;

@RestController
public class ChapterController implements ChapterAPI {
	
	@Autowired
	private ChapterService chapterService;
	
	@Override
	public ResponseEntity<ChapterDTO> save(@Valid @RequestBody ChapterDTO chapterRequestDTO) {
		Chapter chapterSaved = chapterService.save(chapterRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ChapterDTO(chapterSaved));
	}
	
	@Override
	public ResponseEntity<List<ChapterDTO>> findAll() {
		List<Chapter> list = chapterService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(
				list.stream().map(x -> new ChapterDTO(x)).toList());
	}
	
	@Override
	public ResponseEntity<Page<ChapterDTO>> pages(Pageable pageable) {
		Page<Chapter> pages = chapterService.findAll(pageable);
		return ResponseEntity.ok(pages.map(ChapterDTO::new));
	}
	
	@Override
	public ResponseEntity<ChapterDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new ChapterDTO(chapterService.findById(id)));
	}
	
	@Override
	public ResponseEntity<ChapterDTO> update(@Valid @RequestBody ChapterDTO chapterDTO, @PathVariable Long id) {
		Chapter chapterUpdated = chapterService.update(chapterDTO, id);
		return ResponseEntity.ok(new ChapterDTO(chapterUpdated));
	}
	
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		chapterService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Void> deleteAllByNovel(@RequestBody ResquestIdDTO resquestIdDTO) {
		chapterService.deleteAllByNovel(resquestIdDTO.getRequestId());
		return ResponseEntity.noContent().build();
	}
}
