package com.wilsonpedro.novelbr.enums;

import java.util.stream.Stream;

public enum UserType {

	AUTHOR(1, "Author"),
	READER(2, "Reader");
		
	private Integer cod;
	
	private String description;
	
	private UserType(Integer cod, String description) {
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
	
	public static UserType toUserType(String description) {
		return Stream.of(UserType.values())
				.filter(x -> x.getDescription().equals(description))
				.findFirst()
				.orElseThrow(() -> 
				new IllegalArgumentException("Tipo de Usuário Inválido: " + description));
	}
}
