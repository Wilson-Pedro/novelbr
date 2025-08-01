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
class GenderServiceTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	NovelStatusRepository novelStatusRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	GenderService genderService;
	
	@Autowired
	ChapterRepository chapterRepository;
	
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
		genderRepository.deleteAll();
		authorRepository.deleteAll();
		Integer id = 1;
		
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(type));
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
	void findAll() {
		List<Gender> genders = genderService.findAll();
		assertEquals(genders.size(), genderRepository.count());
	}
	
	@Test
	void findGendersByNovelId() {
		Long novelId = novelRepository.findAll().get(0).getId();
		
		List<String> gendersFinded = genderService.findGendersByNovelId(novelId);
		assertNotNull(gendersFinded);
	}
}
