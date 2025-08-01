package com.novelsbr.backend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.domain.dto.ChapterDTO;
import com.novelsbr.backend.domain.dto.NovelDTO;
import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.entities.NovelStatus;
import com.novelsbr.backend.enums.GenderType;
import com.novelsbr.backend.enums.NovelStatusType;
import com.novelsbr.backend.enums.UserRole;
import com.novelsbr.backend.repositories.AuthorRepository;
import com.novelsbr.backend.repositories.ChapterRepository;
import com.novelsbr.backend.repositories.GenderRepository;
import com.novelsbr.backend.repositories.NovelRepository;
import com.novelsbr.backend.repositories.NovelStatusRepository;
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
	NovelStatusRepository novelStatusRepository;
	
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
			genders.add(new Gender(type));
		}
		genderRepository.saveAll(genders);
		
		List<NovelStatus> novelStatsus = new ArrayList();
		for(NovelStatusType type : NovelStatusType.values()) {
			novelStatsus.add(new NovelStatus(type));
		}
		
		novelStatusRepository.saveAll(novelStatsus);
		
		List<String> gendersStr = genders.stream().map(x -> x.getGenderType().getType()).toList();

		List<Author> authors = List.of(
				new Author(null, "João", "All Star", "joao@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Ana", "J. Key", "ana@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Júlia", "Light", "julia@gmail.com", "1234", UserRole.AUTHOR),
				new Author(null, "Roberto", "S. Elppa", "roberto@gmail.com", "1234", UserRole.AUTHOR)
			);

		Author author1 = authorService.save(new AuthorDTO(authors.get(0)));
		Author author2 = authorService.save(new AuthorDTO(authors.get(1)));
		Author author3 = authorService.save(new AuthorDTO(authors.get(2)));
		Author author4 = authorService.save(new AuthorDTO(authors.get(3)));
		
		NovelDTO novelDTO1 = new NovelDTO(null, "A casa ao Lado", author1.getId(), 1, gendersStr, 
				"Ao se mudar para uma cidade pacata em busca de recomeço, Laura aluga uma casa simples ao lado de uma propriedade antiga e abandonada. Logo, começa a ouvir sussurros durante a noite e vê sombras se movendo pelas janelas da casa vizinha. Os moradores evitam o assunto, mas uma lenda local fala de desaparecimentos ligados àquela casa. À medida que Laura investiga, descobre que a casa não está vazia e nunca esteve...",
				"A_casa_ao_lado.jpg");
		
		NovelDTO novelDTO2 = new NovelDTO(null, "Isto é vida", author2.getId(), 1, gendersStr, 
				"Depois de perder tudo em um trágico acidente, Caio se vê forçado a recomeçar do zero. No meio da dor e da solidão, ele encontra pessoas simples e momentos genuínos que o ensinam a enxergar a beleza nas pequenas coisas. Com o tempo, Caio descobre que o verdadeiro sentido da vida não está no que se perde, mas no que se constrói a cada novo dia.",
				"Isto é vida_2.jpg");
		
		NovelDTO novelDTO3 = new NovelDTO(null, "Trem para o nunca", author3.getId(), 1, gendersStr, 
				"Em uma estação esquecida pelo tempo, Gabriel embarca em um trem misterioso rumo ao Reino do Nunca, onde memórias ganham vida e arrependimentos tomam forma. A cada parada, ele enfrenta versões distorcidas de seu passado e tentações mágicas que o prendem ao que poderia ter sido. Para escapar, Gabriel precisa descobrir a verdade sobre si mesmo — antes que o \"Nunca\" o consuma para sempre.\n",
				"Trem para o Nunca.jpg");
		
		NovelDTO novelDTO4 = new NovelDTO(null, "Jornada para o Além", author4.getId(), 1, gendersStr, 
				"Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza: a barreira entre o mundo dos vivos e o Além está se rompendo. Espíritos errantes vagam pela terra, e monstros há muito adormecidos despertam de seu torpor. Apenas uma chave pode selar a fenda entre os reinos, mas ela foi perdida há séculos, enterrada nos confins do desconhecido."
						+ "Elarys, uma jovem ladina meio-elfa, se vê envolvida no caos ao descobrir que carrega em seu sangue a marca dos Guardiões do Véu, uma linhagem há muito extinta. Ao lado de um grupo improvável — um mago caído em desgraça, um guerreiro orc renegado e uma draconiana com segredos sombrios — ela embarca em uma jornada perigosa pelas terras selvagens de Eldoria, enfrentando seres místicos, deuses esquecidos e seus próprios demônios internos."
						+ "A cada passo, o tempo se esgota, e a sombra do Além se alastra. O destino do mundo está nas mãos de quem nunca quis ser heroína. Será Elarys capaz de impedir o fim da realidade antes que tudo sucumba ao vazio eterno?",
				"Jornada para o Além.jpg");
		
		novelService.save(novelDTO1);
		novelService.save(novelDTO2);
		novelService.save(novelDTO3);
		novelService.save(novelDTO4);

		ChapterDTO chapterDTO = new ChapterDTO(null, "Começo", "Era uma vez...", 1L);
		
		chapterService.save(chapterDTO);
	}

}
