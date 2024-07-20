package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;

import com.wilsonpedro.novelbr.dto.ChapterDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name= "TBL_CHAPTER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Chapter implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String chapterTilte;
	
	@NotNull
	private Integer chapterNumber;
	
	@Column(columnDefinition = "TEXT")
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "novel_id")
	private Novel novel;
	
	public Chapter(ChapterDTO chapterRequestDTO) {
		this.chapterTilte = chapterRequestDTO.getChapterTilte();
		this.chapterNumber = chapterRequestDTO.getChapterNumber();
		this.text = chapterRequestDTO.getText();
	}
}
