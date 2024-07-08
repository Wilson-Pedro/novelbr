package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wilsonpedro.novelbr.dto.NovelDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "TBL_NOVEL")
public class Novel implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String synopsis;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	private Set<String> genders = new HashSet<>();
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy="novel", fetch=FetchType.LAZY)
	private Set<Chapter> chapters = new HashSet<>();
	
	public Novel() {
	}

	public Novel(Long id, String title, String synopsis, Author author) {
		this.id = id;
		this.title = title;
		this.synopsis = synopsis;
		this.author = author;
	}
	
	public Novel(NovelDTO novelDTO) {
		this.title = novelDTO.getTitle();
		this.synopsis = novelDTO.getSynopsis();
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Set<String> getGenders() {
		return genders;
	}

	public void setGenders(Set<String> genders) {
		this.genders = genders;
	}

	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
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
