package com.wilsonpedro.novelbr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.repositories.NovelRepository;

@Service
public class NovelService {

	@Autowired
	private NovelRepository novelRepository;
	
	@Transactional
	public Novel save(Novel novel) {
		return novelRepository.save(novel);
	}

	public List<Novel> findAll() {
		return novelRepository.findAll();
	}

	public Optional<Novel> findById(Long id) {
		return novelRepository.findById(id);
	}

	@Transactional
	public Novel update(Novel novel, Long id) {
		Novel novelFinded = findById(id).get();
		novelFinded.setTitle(novel.getTitle());
		novelFinded.setSynopsis(novel.getSynopsis());
		return novelRepository.save(novelFinded);
	}

	@Transactional
	public void delete(Long id) {
		novelRepository.delete(findById(id).get());
		
	}
}
