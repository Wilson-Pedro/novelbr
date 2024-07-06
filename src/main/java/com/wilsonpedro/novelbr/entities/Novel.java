package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "TBL_NOVEL")
public class Novel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String synopsis;
	
	private String author;
	
	private List<String> genders = new ArrayList<>();
	
	private List<String> chapters = new ArrayList<>();
	
	public Novel() {
	}

	public Novel(Long id, String title, String synopsis, String author) {
		this.id = id;
		this.title = title;
		this.synopsis = synopsis;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	public List<String> getGenders() {
		return genders;
	}

	public void setGenders(List<String> genders) {
		this.genders = genders;
	}

	public List<String> getChapters() {
		return chapters;
	}

	public void setChapters(List<String> chapters) {
		this.chapters = chapters;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, chapters, genders, id, synopsis, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Novel other = (Novel) obj;
		return Objects.equals(author, other.author) && Objects.equals(chapters, other.chapters)
				&& Objects.equals(genders, other.genders) && Objects.equals(id, other.id)
				&& Objects.equals(synopsis, other.synopsis) && Objects.equals(title, other.title);
	}
}
