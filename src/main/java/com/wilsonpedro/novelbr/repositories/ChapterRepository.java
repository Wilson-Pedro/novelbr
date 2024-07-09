package com.wilsonpedro.novelbr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.entities.Novel;

public interface ChapterRepository extends JpaRepository<Chapter, Long>{
	
	void deleteAllByNovel(Novel novel);

}
