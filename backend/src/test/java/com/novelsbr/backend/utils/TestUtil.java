package com.novelsbr.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.CommentRepository;
import com.novelsbr.backend.repositories.GenreRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.repositories.NovelStatusRepository;

@Component
public class TestUtil {
	
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
	
	public void deleteAll() {
    	commentRepository.deleteAll();
    	chapterRepository.deleteAll();
    	novelRepository.deleteAll();
    	novelStatusRepository.deleteAll();
    	genreRepository.deleteAll();
    	authorRepository.deleteAll();
	}

}
