package com.novelsbr.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.exceptions.ExistingAuthorException;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.services.AuthorService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author save(AuthorDTO authorDTO) {
		validadeRegistration(authorDTO);
		Author authorSaved = new Author(authorDTO);
		authorSaved.setPassword(new BCryptPasswordEncoder().encode(authorDTO.getPassword()));
		authorSaved.setRole(UserRole.AUTHOR);
		return authorRepository.save(authorSaved);
	}

	@Override
	public Author findByUsername(String username) {
		return authorRepository.findAll().stream()
				.filter(user -> user.getUsername().equals(username))
				.findFirst()
				.orElseThrow(EntityNotFoundException::new);
	}
	
	@Override
	public void validadeRegistration(AuthorDTO authorDTO) {
		if(existsByUsername(authorDTO.getUsername())) throw new ExistingAuthorException();
	}

	@Override
	public boolean existsByUsername(String username) {
		return authorRepository.existsByUsername(username);
	}
}
