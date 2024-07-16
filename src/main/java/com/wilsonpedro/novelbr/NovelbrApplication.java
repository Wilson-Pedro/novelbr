package com.wilsonpedro.novelbr;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wilsonpedro.novelbr.entities.Author;
import com.wilsonpedro.novelbr.entities.Chapter;
import com.wilsonpedro.novelbr.entities.Novel;
import com.wilsonpedro.novelbr.enums.UserType;
import com.wilsonpedro.novelbr.repositories.ChapterRepository;
import com.wilsonpedro.novelbr.repositories.NovelRepository;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@SpringBootApplication
public class NovelbrApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NovelRepository novelRepository;
	
	@Autowired
	private ChapterRepository chapterRepository;

	public static void main(String[] args) {
		SpringApplication.run(NovelbrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Author author1 = new Author(null, "little fox", UserType.AUTHOR, "littlefox@gmail.com", "1234");

		Novel novel1 = new Novel(null, "Againts the Gods", "The Gods...", author1);
		
		Chapter cpt1 = new Chapter(null, "Begins", 1, "In Those Days, the Gods...", novel1);
		Chapter cpt2 = new Chapter(null, "Angry", 2, "After returning...", novel1);
		Chapter cpt3 = new Chapter(null, "Old Book", 3, "Looking the old book...", novel1);
		
		userRepository.save(author1);
		novelRepository.save(novel1);
		chapterRepository.saveAll(Arrays.asList(cpt1, cpt2, cpt3));
		
	}
}
