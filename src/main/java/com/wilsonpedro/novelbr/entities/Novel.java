package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;
import java.util.HashSet;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TBL_NOVEL")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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
}
