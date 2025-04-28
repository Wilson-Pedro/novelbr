package com.novelsbr.backend.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.novelsbr.backend.domain.dto.NovelDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_NOVEL")
public class Novel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String novelName;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	@ManyToMany
	@JoinTable(
			name = "TBL_NOVEL_GENERO", 
			joinColumns = @JoinColumn(name = "novel_id"), 
			inverseJoinColumns = @JoinColumn(name = "genero_id")
	)
	private Set<Gender> genders;
	
	@Lob
	private String synopsis;
	
	private String imageUri;
	
	@CreationTimestamp
	private LocalDateTime dateRegistrion;
	
	public Novel() {
	}
	
	public Novel(NovelDTO novelDTO) {
		this.novelName = novelDTO.getNovelName();
		this.synopsis = novelDTO.getSynopsis();
		this.imageUri = novelDTO.getImageUri();
		this.dateRegistrion = novelDTO.getDateRegistrion();
	}

	public Novel(Long id, String novelName, Author author, Set<Gender> genders, String synopsis, String imageUri) {
		this.id = id;
		this.novelName = novelName;
		this.author = author;
		this.genders = genders;
		this.synopsis = synopsis;
		this.imageUri = imageUri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNovelName() {
		return novelName;
	}

	public void setNovelName(String novelName) {
		this.novelName = novelName;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Set<Gender> getGenders() {
		return genders;
	}

	public void setGenders(Set<Gender> genders) {
		this.genders = genders;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public LocalDateTime getDateRegistrion() {
		return dateRegistrion;
	}

}
