package com.novelsbr.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.domain.projections.NovelsChapterTitleProjection;

public interface ChapterRepository extends JpaRepository<Chapter, Integer> {
	
	@Query(nativeQuery = true, value = """
			SELECT c.id AS chapter_id,  n.id AS novel_id, a.id AS author_id,  c.title 
			FROM TBL_CHAPTER c
			INNER JOIN TBL_NOVEL n ON n.id = c.novel_id 
			INNER JOIN TBL_AUTHOR a ON a.id = n.author_id 
			WHERE a.id = :authorId
			""")
	List<NovelsChapterTitleProjection> findAllNovelsChapterTitleByAuthorId(Long authorId);

}
