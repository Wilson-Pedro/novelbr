package com.novelsbr.backend;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.entities.Novel;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.services.AuthorService;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	ChapterRepository chapterRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Set<Gender> genders = new HashSet<>();
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(null, type));
		}
		
//		Author author = new Author(null, "João", "AllStar", "joao@gmail.com", "1234", UserRole.AUTHOR);
		List<Author> authors = List.of(
				new Author(null, "João", "All Star", "joao@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Ana", "J. Key", "ana@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Júlia", "Light", "julia@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Roberto", "S. Elppa", "roberto@gmail.com", "1234", UserRole.AUTHOR)
			);
		
		List<Novel> novels = List.of(
				new Novel(null, "A casa ao Lado", authors.get(0), genders, 
						"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
						"https://img.freepik.com/fotos-gratis/ilustracao-do-ceu-noturno-do-anime_23-2151684373.jpg?t=st=1745495843~exp=1745499443~hmac=0cfb3ba2c360f31102c8f3162258fddb8078cddf9512064c3643d0673914d2d8&w=360"),
				
				new Novel(null, "Isto é vida", authors.get(1), genders, 
						"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
						"https://applescoop.org/image/wallpapers/iphone/70184864306871153-32889826787073441.jpg"),
				
				new Novel(null, "Trem para o nunca", authors.get(2), genders, 
						"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
						"https://image.tensorartassets.com/posts/images/613920095827259840/a8412668-1b0e-472c-8274-d839f283be58.jpg"),
				
				new Novel(null, "Jornada para o Além", authors.get(3), genders, 
						"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
						"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQS7rbrAsm_5hxNqowk-5RjYI_leD2ZUof1IA8YJouoUUupVhO6RMkwjHiMKeF37szqiik&usqp=CAU")
				);
//		
//		Novel novel = new Novel(null, 
//				"Jornada para o Além", 
//				author, 
//				genders, 
//				"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
//				"https://wallpapercave.com/wp/wp5044832.jpg");
//		
//		Chapter chapter = new Chapter(null, "Hellifen", "Em uma pequena vila...", novel);
//		
		genderRepository.saveAll(genders);
		authorRepository.saveAll(authors);
		novelRepository.saveAll(novels);
//		chapterRepository.save(chapter);
	}

}
