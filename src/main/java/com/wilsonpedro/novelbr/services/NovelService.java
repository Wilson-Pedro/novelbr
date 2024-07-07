package com.wilsonpedro.novelbr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		return novelRepository.save(novel);
	}
	
	@Transactional
	public Novel save(NovelDTO novelDTO) {
		Novel novelSaved = new Novel(novelDTO);
		Author author = (Author) userService.findById(novelDTO.getAuthorId());
		novelSaved.setAuthor(author);
		return novelRepository.save(novelSaved);
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
}
