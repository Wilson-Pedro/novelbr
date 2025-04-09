package com.novelsbr.backend;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.enums.Gender;
import com.novelsbr.backend.services.NovelService;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	
//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private NovelService novelService;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		User user = new User(null, "João", "AllStar", "joao@gmail.com", "1234");
//		userRepository.save(user);
		
		Set<Gender> genders = new HashSet<>();
		genders.add(Gender.ADVENTURE);
		genders.add(Gender.FANTASY);
		genders.add(Gender.ACTION);
		genders.add(Gender.MYSTERY);
		
		NovelDTO novelDTO = new NovelDTO(null, 
				"Jornada para o Além", 
				"All Star", 
				genders, 
				"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza...",
				"https://wallpapercave.com/wp/wp5044832.jpg");
		
		this.novelService.save(novelDTO);
	}

}
