package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name= "TBL_CHAPTER")
public class Chapter implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String chapterTilte;
	
	private Integer chapterNumber;
	
	private String text;
	
	private String novel;

	public Chapter(Long id, String chapterTilte, Integer chapterNumber, String text, String novel) {
		this.id = id;
		this.chapterTilte = chapterTilte;
		this.chapterNumber = chapterNumber;
		this.text = text;
		this.novel = novel;
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

	public String getNovel() {
		return novel;
	}

	public void setNovel(String novel) {
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