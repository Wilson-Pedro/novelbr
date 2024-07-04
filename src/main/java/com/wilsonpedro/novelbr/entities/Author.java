package com.wilsonpedro.novelbr.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Entity;

@Entity(name = "TBL_AUTHOR")
@JsonTypeName("motorista")
public class Author extends User{
	private static final long serialVersionUID = 1L;
	
	private List<String> novels = new ArrayList<>();

	public Author() {
		super();
	}

	public Author(Long id, String pseudonym, String email, String password) {
		super(id, pseudonym, email, password);
	}

	public List<String> getNovels() {
		return novels;
	}

	public void setNovels(List<String> novels) {
		this.novels = novels;
	}
}
