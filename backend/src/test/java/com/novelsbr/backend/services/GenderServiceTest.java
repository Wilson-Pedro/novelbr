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

import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.domain.entities.Genre;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.entities.NovelStatus;
import com.novelsbr.backend.enums.GenreType;
import com.novelsbr.backend.enums.NovelStatusType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.GenreRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.repositories.NovelStatusRepository;
import com.novelsbr.backend.utils.TestUtil;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GenderServiceTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	NovelStatusRepository novelStatusRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	GenreService genreService;
	
	@Autowired
	ChapterRepository chapterRepository;
	
    @Autowired
    TestUtil testUtil;
	
	Set<Genre> genders = new HashSet<>();
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
		testUtil.deleteAll();
		
		for(GenreType type : GenreType.values()) {
			genders.add(new Genre(type));
		}
		
		for(NovelStatusType type : NovelStatusType.values()) {
			novelStatsus.add(new NovelStatus(type));
		}
		
		novelStatusRepository.saveAll(novelStatsus);
		genreRepository.saveAll(genders);
		authorRepository.save(author);
		novelRepository.save(novel);
	}
	
	@Test
	void findAll() {
		List<Genre> genders = genreService.findAll();
		assertEquals(genders.size(), genreRepository.count());
	}
	
	@Test
	void findGendersByNovelId() {
		Long novelId = novelRepository.findAll().get(0).getId();
		
		List<Genre> gendersFinded = genreService.findGendersByNovelId(novelId);
		assertNotNull(gendersFinded);
	}
}
