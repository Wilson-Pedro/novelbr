package com.wilsonpedro.novelbr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Novel;

public interface NovelRepository extends JpaRepository<Novel, Long>{
	
	void deleteAllByAuthor(Author Auhtor);

}
