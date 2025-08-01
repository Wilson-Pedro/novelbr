package com.novelsbr.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novelsbr.backend.domain.entities.NovelStatus;
import com.novelsbr.backend.enums.NovelStatusType;

public interface NovelStatusRepository extends JpaRepository<NovelStatus, Integer>{
	
	Optional<NovelStatus> findByNovelStatusType(NovelStatusType type);
}
