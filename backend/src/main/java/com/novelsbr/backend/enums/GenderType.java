package com.novelsbr.backend.enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public enum GenderType {

	ADVENTURE(1, "Aventura"),
	DYSTOPIA(2, "Distopia"),
	FANTASY(3, "Fantasia"),
	HISTORICAL(4, "Histórico"),
	HORROR(5, "Horror"),
	LGBTQ(6, "Yaoi"),
	MAGICAL(7, "Mágico"),
	MYSTERY(8, "Mistério"),
	ADULT(9, "Adulto"),
	ROMANCE(10, "Romance"),
	SCIENCE_FICTION(11, "Ficção Científica"),
	THRILLER(12, "Thriller"),
	WESTERN(13, "Ocidente"),
	SUSPENSEFUL(14, "Suspense"),
	ACTION(15, "Ação"),
	MEDIEVAL(16, "Medieval"),
	SLICE_OF_LIFE(17, "Slice of life"),
	SHOUJO(18, "Shoujo"),
	TERROR(19, "Terror"),
	DRAMA(20, "Drama"),
	POLITICAL(21, "Político");
	
	
	private Integer cod;
	
	private String type;
	
	private GenderType(Integer cod, String type) {
		this.cod = cod;
		this.type = type;
	}

	public Integer getCod() {
		return cod;
	}

	public String getType() {
		return type;
	}
	
	public static GenderType toEnum(String type) {
		return Stream.of(GenderType.values())
				.filter(genderType -> genderType.getType().equals(type))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException
						("Formato de Pagamento inválido: " + type));
	}
	
	public static List<GenderType> stringToGender(Collection<String> types) {
		List<GenderType> genderTypes = new ArrayList<>();
		for(String type : types) {
			genderTypes.add(toEnum(type));
		}
		return genderTypes;
	}
}
