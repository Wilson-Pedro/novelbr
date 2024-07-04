package com.wilsonpedro.novelbr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilsonpedro.novelbr.entities.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long>{

}
