package com.novelsbr.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.domain.entities.Chapter;
import com.novelsbr.backend.domain.entities.Comment;
import com.novelsbr.backend.domain.entities.Genre;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.domain.entities.NovelStatus;
import com.novelsbr.backend.domain.projections.CommentProjection;
import com.novelsbr.backend.enums.CommentBy;
import com.novelsbr.backend.enums.GenreType;
import com.novelsbr.backend.enums.NovelStatusType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.CommentRepository;
import com.novelsbr.backend.repositories.GenreRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.repositories.NovelStatusRepository;
import com.novelsbr.backend.utils.TestUtil;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceTest {

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    NovelRepository novelRepository;

    @Autowired
    NovelStatusRepository novelStatusRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;
    
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

    Chapter chapter = new Chapter(null, "Começo", "Excelente começo!", novel);

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
    	author = authorRepository.save(author);
    	novelRepository.save(novel);
    	chapterRepository.save(chapter);
    }

    @Test
    @Order(2)
    void save() {
    	
    	Long authorId = authorRepository.findAll().get(0).getId();
    	Long novelId = novelRepository.findAll().get(0).getId();
    	Long chapterId = chapterRepository.findAll().get(0).getId();
    	
		CommentDTO commentNovelDTO = new CommentDTO(null, authorId, author.getUsername(), 1, novelId, "História Incrível");
		CommentDTO commentChapterDTO = new CommentDTO(null, authorId, author.getUsername(), 2, chapterId, "História Incrível");
	
		Comment commentNovelSaved = commentService.save(commentNovelDTO);
		assertEquals(CommentBy.NOVEL, commentNovelSaved.getCommentBy());
		assertEquals("História Incrível", commentNovelSaved.getBodyText());
		assertEquals(commentNovelSaved.getAuthor().getId(), authorId);
	
		Comment commentChapterSaved = commentService.save(commentChapterDTO);
	   	assertEquals(CommentBy.CHAPTER, commentChapterSaved.getCommentBy());
	   	assertEquals("História Incrível", commentChapterSaved.getBodyText());
	   	assertEquals(commentChapterSaved.getAuthor().getId(), authorId);
	
	   	assertEquals(2, commentRepository.count());
    }

    @Test
    @Order(3)
    void findAll() {

       List<Comment> comments = commentService.findAll();

        assertFalse(comments.isEmpty());
       assertEquals(commentRepository.count(), comments.size());

    }

    @Test
    @Order(4)
    void findAllNovelsByEntityId() {

       Long novelId = novelRepository.findAll().get(0).getId();

       List<CommentProjection> commentsByNovel = commentService.findAllNovelsByEntityId(novelId);

       assertEquals(1, commentsByNovel.size());
    }

    @Test
    @Order(5)
    void findAllChaptersByEntityId() {

       Long chapterId = chapterRepository.findAll().get(0).getId();

       List<CommentProjection> commentsByChapter = commentService.findAllChaptersByEntityId(chapterId);

       assertEquals(1, commentsByChapter.size());
    }

    @Test
    @Order(6)
    void update() {

       Comment comment = commentRepository.findAll().get(0);

       CommentDTO commentChapterDTO = new CommentDTO(comment);
       commentChapterDTO.setBodyText("Obra prima");

       Comment commentUpdated = commentService.update(commentChapterDTO, comment.getId());
       assertEquals("Obra prima", commentUpdated.getBodyText());
    }

    @Test
    @Order(7)
    void delete() {

       Long commentId = commentRepository.findAll().get(0).getId();

       commentService.delete(commentId);
       
       assertEquals(1, commentRepository.count());
    }
}