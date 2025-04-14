package com.novelsbr.backend.enums;

public enum GenderType {

	ADVENTURE(1, "Adventure"),
	DYSTOPIA(2, "Dystopia"),
	FANTASY(3, "Fantasy"),
	HISTORICAL(4, "Historical"),
	HORROR(5, "Horror"),
	LGBTQ(6, "LGBTQ"),
	MAGICAL(7, "Magical"),
	MYSTERY(8, "Mistery"),
	ADULT(9, "New Adult"),
	ROMANCE(10, "Romance"),
	SCIENCE_FICTION(11, "Science Fiction"),
	THRILLER(12, "Thriller"),
	WESTERN(13, "Western"),
	SUSPENSEFUL(14, "Suspenseful"),
	ACTION(15, "Action"),
	MEDIEVAL(16, "Medieval");
	
	private Integer cod;
	
	private String description;
	
	private GenderType(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
