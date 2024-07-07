package com.wilsonpedro.novelbr.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity(name = "TBL_AUTHOR")
@JsonTypeName("author")
public class Author extends User{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "author", fetch=FetchType.LAZY)
	private Set<Novel> novels = new HashSet<>();

	public Author() {
		super();
	}

	public Author(Long id, String pseudonym, String email, String password) {
		super(id, pseudonym, email, password);
	}

	public Set<Novel> getNovels() {
		return novels;
	}

	public void setNovels(Set<Novel> novels) {
		this.novels = novels;
	}
}
