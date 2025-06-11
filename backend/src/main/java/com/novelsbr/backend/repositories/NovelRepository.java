package com.novelsbr.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.projections.AuthorNovelMinProjection;
import com.novelsbr.backend.domain.projections.CardNovelProjection;

public interface NovelRepository extends JpaRepository<Novel, Long> {
	
	boolean existsByNovelName(String novelName);

	@Query(nativeQuery = true, value = """
			SELECT n.id AS novel_id, a.id AS author_id, n.novel_name, a.username, n.image_uri
			FROM TBL_NOVEL AS n 
			INNER JOIN TBL_AUTHOR AS a ON a.id = n.author_id
			LIMIT 4
			""")
	List<CardNovelProjection> findNovelCards();
	
	@Query(nativeQuery = true, value = """
			SELECT n.id AS novel_id, a.id AS author_id, n.novel_name, a.username, n.image_uri 
			FROM TBL_NOVEL AS n 
			INNER JOIN TBL_AUTHOR AS a ON a.id = n.author_id 
			WHERE a.username = :username
			""")
	List<CardNovelProjection> findNovelCardsByUsername(String username);
	
	@Query(nativeQuery = true, value = """
			SELECT n.id AS novel_id, a.id AS author_id, n.novel_name, a.username, n.image_uri, n.synopsis 
			FROM TBL_NOVEL AS n 
			INNER JOIN TBL_AUTHOR AS a ON a.id = n.author_id 
			WHERE n.id = :novelId	
			""")
	Optional<AuthorNovelMinProjection> findNovelInfoByNovelId(Long novelId);
	
	List<Novel> findByNovelNameContainingIgnoreCase(String novelName);
}
