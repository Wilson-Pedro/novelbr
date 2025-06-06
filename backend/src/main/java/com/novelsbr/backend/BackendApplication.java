package com.novelsbr.backend;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.dto.NovelDTO;
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
import com.novelsbr.backend.services.ChapterService;
import com.novelsbr.backend.services.NovelService;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	NovelRepository novelRepository;
	
	@Autowired
	NovelService novelService;
	
	@Autowired
	GenderRepository genderRepository;
	
	@Autowired
	ChapterRepository chapterRepository;
	
	@Autowired
	ChapterService chapterService;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Set<Gender> genders = new HashSet<>();
		for(GenderType type : GenderType.values()) {
			genders.add(new Gender(null, type));
		}
		genderRepository.saveAll(genders);
		
		List<String> gendersStr = genders.stream().map(x -> x.getGenderType().getType()).toList();

		List<Author> authors = List.of(
				//new Author(null, "João", "All Star", "joao@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Ana", "J. Key", "ana@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Júlia", "Light", "julia@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Roberto", "S. Elppa", "roberto@gmail.com", "1234", UserRole.AUTHOR)
			);
		
		List<Novel> novels = List.of(
//				new Novel(null, "A casa ao Lado", authors.get(0), genders, 
//						"Ao se mudar para uma cidade pacata em busca de recomeço, Laura aluga uma casa simples ao lado de uma propriedade antiga e abandonada. Logo, começa a ouvir sussurros durante a noite e vê sombras se movendo pelas janelas da casa vizinha. Os moradores evitam o assunto, mas uma lenda local fala de desaparecimentos ligados àquela casa. À medida que Laura investiga, descobre que a casa não está vazia e nunca esteve...",
//						"https://img.freepik.com/fotos-gratis/ilustracao-do-ceu-noturno-do-anime_23-2151684373.jpg?t=st=1745495843~exp=1745499443~hmac=0cfb3ba2c360f31102c8f3162258fddb8078cddf9512064c3643d0673914d2d8&w=360"),
//				
				new Novel(null, "Isto é vida", authors.get(0), genders, 
						"Depois de perder tudo em um trágico acidente, Caio se vê forçado a recomeçar do zero. No meio da dor e da solidão, ele encontra pessoas simples e momentos genuínos que o ensinam a enxergar a beleza nas pequenas coisas. Com o tempo, Caio descobre que o verdadeiro sentido da vida não está no que se perde, mas no que se constrói a cada novo dia.",
						"Isto é vida_2.jpg"),
				
				new Novel(null, "Trem para o nunca", authors.get(1), genders, 
						"Em uma estação esquecida pelo tempo, Gabriel embarca em um trem misterioso rumo ao Reino do Nunca, onde memórias ganham vida e arrependimentos tomam forma. A cada parada, ele enfrenta versões distorcidas de seu passado e tentações mágicas que o prendem ao que poderia ter sido. Para escapar, Gabriel precisa descobrir a verdade sobre si mesmo — antes que o \"Nunca\" o consuma para sempre.\n"
						+ "\n"
						+ "",
						"Trem para o Nunca.jpg"),
				
				new Novel(null, "Jornada para o Além", authors.get(2), genders, 
						"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza: a barreira entre o mundo dos vivos e o Além está se rompendo. Espíritos errantes vagam pela terra, e monstros há muito adormecidos despertam de seu torpor. Apenas uma chave pode selar a fenda entre os reinos, mas ela foi perdida há séculos, enterrada nos confins do desconhecido."
						+ "Elarys, uma jovem ladina meio-elfa, se vê envolvida no caos ao descobrir que carrega em seu sangue a marca dos Guardiões do Véu, uma linhagem há muito extinta. Ao lado de um grupo improvável — um mago caído em desgraça, um guerreiro orc renegado e uma draconiana com segredos sombrios — ela embarca em uma jornada perigosa pelas terras selvagens de Eldoria, enfrentando seres místicos, deuses esquecidos e seus próprios demônios internos."
						+ "A cada passo, o tempo se esgota, e a sombra do Além se alastra. O destino do mundo está nas mãos de quem nunca quis ser heroína. Será Elarys capaz de impedir o fim da realidade antes que tudo sucumba ao vazio eterno?",
						"Jornada para o Além.jpg")
				);

		
		//Chapter chapter = new Chapter(null, "Hellifen", "Em uma pequena vila...", novels.get(0));
		//Chapter chapter2 = new Chapter(null, "Hellifen", "Em uma pequena vila...", novels.get(1));
		//Chapter chapter3 = new Chapter(null, "Hellifen", "Em uma pequena vila...", novels.get(1));
		//Chapter chapter4 = new Chapter(null, "Hellifen", "Em uma pequena vila...", novels.get(0));
		//Chapter chapter5 = new Chapter(null, "Hellifen", "Em uma pequena vila...", novels.get(2));
		Author author = new Author(null, "João", "All Star", "joao@gmail.com", "1234", UserRole.AUTHOR);
		author = authorService.save(new AuthorDTO(author));
		NovelDTO novelDTO = new NovelDTO(null, "A casa ao Lado", author.getId(), gendersStr, 
				"Ao se mudar para uma cidade pacata em busca de recomeço, Laura aluga uma casa simples ao lado de uma propriedade antiga e abandonada. Logo, começa a ouvir sussurros durante a noite e vê sombras se movendo pelas janelas da casa vizinha. Os moradores evitam o assunto, mas uma lenda local fala de desaparecimentos ligados àquela casa. À medida que Laura investiga, descobre que a casa não está vazia e nunca esteve...",
				"A_casa_ao_lado.jpg");
		novelService.save(novelDTO);
		
		authorRepository.saveAll(authors);
		novelRepository.saveAll(novels);
		//chapterService.save(new ChapterDTO(chapter));
		//chapterService.save(new ChapterDTO(chapter2));
		//chapterService.save(new ChapterDTO(chapter3));
		//chapterService.save(new ChapterDTO(chapter4));
		//chapterService.save(new ChapterDTO(chapter5));
	}

}
