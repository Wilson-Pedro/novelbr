package com.wilsonpedro.novelbr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilsonpedro.novelbr.dto.NovelDTO;
import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EntityNotFoundException;
import com.wilsonpedro.novelbr.repositories.NovelRepository;

@Service
public class NovelService {

	@Autowired
	private NovelRepository novelRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public Novel save(Novel novel) {
		novelRepository.deleteAllById(null);
		return novelRepository.save(novel);
	}
	
	@Transactional
	public Novel save(NovelDTO novelDTO) {
		Novel novelSaved = new Novel(novelDTO);
		Author author = (Author) userService.findById(novelDTO.getAuthorId());
		novelSaved.setAuthor(author);
		return novelRepository.save(novelSaved);
	}
	
	public Page<Novel> findAll(Pageable pageable) {
		return novelRepository.findAll(pageable);
	}

	public List<Novel> findAll() {
		return novelRepository.findAll();
	}

	public Novel findById(Long id) {
		return novelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Transactional
	public Novel update(NovelDTO novelDTO, Long id) {
		Novel novelFinded = findById(id);
		Author author = (Author) userService.findById(novelDTO.getAuthorId());
		novelFinded.setTitle(novelDTO.getTitle());
		novelFinded.setSynopsis(novelDTO.getSynopsis());
		novelFinded.setAuthor(author);
		return novelRepository.save(novelFinded);
	}

	@Transactional
	public void delete(Long id) {
		novelRepository.delete(findById(id));
	}
	
	@Transactional
	public void deleteAllByNovel(Long requestId) {
		Author author = (Author) userService.findById(requestId);
		novelRepository.deleteAllByAuthor(author);
	}
}
