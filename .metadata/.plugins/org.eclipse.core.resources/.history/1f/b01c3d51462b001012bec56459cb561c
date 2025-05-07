package com.novelsbr.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NovelServiceTest {

	@Autowired
	NovelService novelService;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	Author author = new Author(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
	
	NovelDTO novelDTO = new NovelDTO();
	
	Set<Gender> genders = new HashSet<>();
	List<String> gendersStr = new ArrayList<>();

	@Test
	@Order(1)
	void preparingTestEnvironment() {
		novelRepository.deleteAll();
		genderRepository.deleteAll();
		authorRepository.deleteAll();
		
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(null, type));
		}
		
		for(Gender gender : genders) {
			gendersStr.add(gender.getGenderType().getType());
		}
		
		genderRepository.saveAll(genders);
		authorRepository.save(author);
	}
	
	@Test
	@Order(2)
	void save() {
		
		Long author_id = authorRepository.findAll().get(0).getId();
		
		novelDTO = new NovelDTO(null, 
				"Jornada para o Além", 
				author_id, 
				gendersStr, 
				"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
				"https://wallpapercave.com/wp/wp5044832.jpg");
		
		assertEquals(0, novelRepository.count());
		
		Novel novel = novelService.save(novelDTO);
		assertEquals("Jornada para o Além", novel.getNovelName());
		assertEquals("Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...", novel.getSynopsis());
		assertEquals("https://wallpapercave.com/wp/wp5044832.jpg", novel.getImageUri());
		
		assertEquals(1, novelRepository.count());
	}
	
	@Test
	void findAll() {
		
		List<Novel> novels = novelService.findAll();
		
		assertEquals(novels.size(), novelRepository.count());
	}
}
