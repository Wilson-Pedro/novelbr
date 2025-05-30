package com.novelsbr.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.novelsbr.backend.domain.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	UserDetails findByUsername(String username);
	
	boolean existsByUsername(String username);
}
