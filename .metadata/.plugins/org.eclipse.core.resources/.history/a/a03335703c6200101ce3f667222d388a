package com.novelsbr.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.ChapterTextDTO;
import com.novelsbr.backend.domain.dto.LastChaptersDTO;
import com.novelsbr.backend.domain.dto.NovelsChapterTitleDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.entities.NovelStatus;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.enums.NovelStatusType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.repositories.NovelStatusRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChapterServiceTest {
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	ChapterService chapterService;

	@Autowired
	NovelService novelService;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	NovelStatusRepository novelStatusRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	Set<Gender> genders = new HashSet<>();
	List<NovelStatus> novelStatsus = new ArrayList();
	
	Author author = new Author(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
	
	Novel novel = new Novel(null, 
			"Jornada para o Além", 
			author,
			new NovelStatus(NovelStatusType.IN_COURSE),
			genders, 
			"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
			"https://wallpapercave.com/wp/wp5044832.jpg");

	@Test
	@Order(1)
	void preparingTestEnvironment() {
		chapterRepository.deleteAll();
		novelRepository.deleteAll();
		novelStatusRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
		Integer id = 1;
		
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(id, type));
			id++;
		}
		
		for(NovelStatusType type : NovelStatusType.values()) {
			novelStatsus.add(new NovelStatus(type));
		}
		
		novelStatusRepository.saveAll(novelStatsus);
		genderRepository.saveAll(genders);
		authorRepository.save(author);
		novelRepository.save(novel);
	}
	
	@Test
	@Order(2)
	void save() {
		
		Long novelId = novelRepository.findAll().get(0).getId();
		
		assertEquals(0, chapterRepository.count());
		
		ChapterDTO chapterDTO = new ChapterDTO(null, "Hellifen", "Em uma pequena vila...", novelId);
		Chapter chapter = chapterService.save(chapterDTO);
		assertEquals("Hellifen", chapter.getTitle());
		assertEquals("Em uma pequena vila...", chapter.getChapterText());
		assertNotNull(chapter.getId());
		assertEquals(novelId, chapter.getNovel().getId());
		
		assertEquals(1, chapterRepository.count());
	}
	
	@Test
	@Order(3)
	void findAllNovelsChapterTitleByNovelId() {
		
		Long novelId = novelRepository.findAll().get(0).getId();
		ChapterDTO chapterDTO = new ChapterDTO(null, "Hellifen", "O dia mal havia começado...", novelId);
		
		chapterService.save(chapterDTO);
		
		List<NovelsChapterTitleDTO> novels = chapterService.findAllNovelsChapterTitleByNovelId(novelId);
		
		assertEquals(novels.size(), 2);
	}
	
	@Test
	@Order(4)
	void findMaxChapterNumber() {
		
		Long novelId = novelRepository.findAll().get(0).getId();
		
		Integer chapterNumber = chapterService.findMaxChapterNumber(novelId);
		
		assertEquals(chapterNumber, 2);
	}
	
	@Test
	void findChapterText() {
		
		Chapter chapter = chapterRepository.findAll().get(1);
		Integer chapterNumber = chapter.getChapterNumber();
		String novelName = chapter.getNovel().getNovelName();
		ChapterTextDTO chapterText = chapterService.findChapterText(chapterNumber, novelName);
		
		assertEquals(chapterText.getChapterNumber(), chapterNumber);
		assertEquals(chapterText.getTitle(), chapter.getTitle());
		assertEquals(chapterText.getNovelId(), chapter.getNovel().getId());
		assertEquals(chapterText.getNovelName(), novelName);
	}
	
	@Test
	void findLastChapters() {
		List<LastChaptersDTO> lastChapters = chapterService.findLastChapters();
		
		assertEquals(lastChapters.size(), 2);
	}
}
