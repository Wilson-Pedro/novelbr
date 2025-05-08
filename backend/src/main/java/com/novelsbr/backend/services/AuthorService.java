package com.novelsbr.backend.services;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.entities.Author;

public interface AuthorService {

	Author save(AuthorDTO authorDTO);
	
	Author findById(Long id);
	
	Author findByUsername(String username);
	
	void validadeRegistration(AuthorDTO authorDTO);
	
	boolean existsByUsername(String username);
}
