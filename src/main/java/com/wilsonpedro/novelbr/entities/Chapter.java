package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;
import java.util.Objects;

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

@Entity(name= "TBL_CHAPTER")
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
	
	public Chapter() {
	}

	public Chapter(Long id, String chapterTilte, Integer chapterNumber, String text, Novel novel) {
		this.id = id;
		this.chapterTilte = chapterTilte;
		this.chapterNumber = chapterNumber;
		this.text = text;
		this.novel = novel;
	}
	
	public Chapter(ChapterDTO chapterRequestDTO) {
		this.chapterTilte = chapterRequestDTO.getChapterTilte();
		this.chapterNumber = chapterRequestDTO.getChapterNumber();
		this.text = chapterRequestDTO.getText();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChapterTilte() {
		return chapterTilte;
	}

	public void setChapterTilte(String chapterTilte) {
		this.chapterTilte = chapterTilte;
	}

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(Integer chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Novel getNovel() {
		return novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chapterNumber, chapterTilte, id, novel, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chapter other = (Chapter) obj;
		return Objects.equals(chapterNumber, other.chapterNumber) && Objects.equals(chapterTilte, other.chapterTilte)
				&& Objects.equals(id, other.id) && Objects.equals(novel, other.novel)
				&& Objects.equals(text, other.text);
	}
}
