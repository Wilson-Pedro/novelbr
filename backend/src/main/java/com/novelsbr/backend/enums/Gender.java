package com.novelsbr.backend.enums;

public enum Gender {

	ADVENTURE(1, "Adventure"),
	DYSTOPIA(2, "Dystopia"),
	FANTASY(3, "Fantasy"),
	HISTORICAL(4, "Historical"),
	HORROR(5, "Horror"),
	LGBTQ(6, "LGBTQ"),
	MAGICAL_REALISM(7, "Magical Realism"),
	MYSTERY(8, "Mistery"),
	NEW_ADULT(9, "New Adult"),
	ROMANCE(10, "Romance"),
	SCIENCE_FICTION(11, "Science Fiction"),
	THRILLER(12, "Thriller"),
	WESTERN(13, "Western"),
	YOUNG_ADULT(14, "Young Adult"),
	ACTION(15, "Action");
	
	private Integer cod;
	
	private String description;
	
	private Gender(Integer cod, String description) {
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
