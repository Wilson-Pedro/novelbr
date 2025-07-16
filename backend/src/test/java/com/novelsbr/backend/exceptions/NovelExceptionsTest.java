package com.novelsbr.backend.exceptions;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.novelsbr.backend.domain.entities.NovelStatus;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.enums.NovelStatusType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.repositories.NovelStatusRepository;
import com.novelsbr.backend.services.NovelService;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NovelExceptionsTest {
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	NovelStatusRepository novelStatusRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	NovelService novelService;
	
	@Autowired
	ChapterRepository chapterRepository;
	
	Set<Gender> genders = new HashSet<>();
	List<NovelStatus> novelStatsus = new ArrayList();
	
	Author author = new Author(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
	
	Novel novel = new Novel(null, 
			"A casa ao Lado", 
			author, 
			new NovelStatus(NovelStatusType.IN_COURSE), 
			genders, 
			"Ao se mudar para uma cidade pacata em busca de recomeço, Laura aluga uma casa simples ao lado de uma propriedade antiga e abandonada. Logo, começa a ouvir sussurros durante a noite e vê sombras se movendo pelas janelas da casa vizinha. Os moradores evitam o assunto, mas uma lenda local fala de desaparecimentos ligados àquela casa. À medida que Laura investiga, descobre que a casa não está vazia e nunca esteve...",
			"https://img.freepik.com/fotos-gratis/ilustracao-do-ceu-noturno-do-anime_23-2151684373.jpg?t=st=1745495843~exp=1745499443~hmac=0cfb3ba2c360f31102c8f3162258fddb8078cddf9512064c3643d0673914d2d8&w=360");
	
	@Test
	@Order(1)
	void setUp() {
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
	void entityNotFoundException01() {
		assertThrows(NotFoundException.class, () -> novelService.findById(70L));
	}

	@Test
	void entityNotFoundException02() {
		assertThrows(NotFoundException.class, () -> novelService.findNovelInfoByNovelId(70L));
	}
	
	@Test
	void existingNovelException() {
		assertThrows(ExistingNovelException.class, () -> novelService.save(new NovelDTO(novel)));
	}
}
