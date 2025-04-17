package com.novelsbr.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novelsbr.backend.domain.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByEmail(String email);
}
