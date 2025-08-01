package com.novelsbr.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.domain.projections.ChapterTextProjection;
import com.novelsbr.backend.domain.projections.LastChaptersProjection;
import com.novelsbr.backend.domain.projections.NovelsChapterTitleProjection;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
	
	@Query(nativeQuery = true, value = """
			SELECT c.id AS chapter_id,  n.id AS novel_id, a.id AS author_id,  c.title 
			FROM TBL_CHAPTER c
			INNER JOIN TBL_NOVEL n ON n.id = c.novel_id 
			INNER JOIN TBL_AUTHOR a ON a.id = n.author_id 
			WHERE n.id = :novelId
			""")
	List<NovelsChapterTitleProjection> findAllNovelsChapterTitleByNovelId(Long novelId);
	
	@Query(nativeQuery = true, value = """
			SELECT n.id AS novel_id, a.id AS author_id, n.novel_name, a.username, c.title, c.chapter_number, c.date_registration
			FROM TBL_NOVEL AS n 
			INNER JOIN TBL_AUTHOR AS a ON a.id = n.author_id 
			INNER JOIN TBL_CHAPTER c ON c.novel_id = n.id 
			ORDER BY c.date_registration DESC
			LIMIT 10
				""")
	List<LastChaptersProjection> findLastChapters();
	
	@Query(nativeQuery = true, value = """
				SELECT MAX(c.chapter_number) FROM TBL_CHAPTER c WHERE c.NOVEL_ID = :novelId
			""")
	Integer findMaxChapterNumber(Long novelId);
	
	@Query(nativeQuery = true, value = """
			SELECT c.title, c.chapter_number, c.chapter_text, c.novel_id, n.novel_name 
			FROM TBL_CHAPTER c 
			INNER JOIN TBL_NOVEL n ON n.id = c.novel_id 
			WHERE c.chapter_number = :chapterNumber AND n.novel_name = :novelName
			""")
	ChapterTextProjection findChapterText(Integer chapterNumber, String novelName);
}
