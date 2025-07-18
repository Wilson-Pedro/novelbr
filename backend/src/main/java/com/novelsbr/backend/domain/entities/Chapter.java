package com.novelsbr.backend.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_CHAPTER")
public class Chapter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private Integer chapterNumber;
	
	@Column(columnDefinition = "TEXT")
	private String chapterText;
	
	@ManyToOne
	@JoinColumn(name = "novel_id")
	private Novel novel;
	
	@CreationTimestamp
	private LocalDateTime dateRegistration;
	
	public Chapter() {
	}

	public Chapter(Long id, String title, String chapterText, Novel novel) {
		this.id = id;
		this.title = title;
		this.chapterText = chapterText;
		this.novel = novel;
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

	public Integer getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(Integer chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public String getChapterText() {
		return chapterText;
	}

	public Novel getNovel() {
		return novel;
	}

	public void setNovel(Novel novel) {
		this.novel = novel;
	}

	public void setChapterText(String chapterText) {
		this.chapterText = chapterText;
	}

	public LocalDateTime getDateRegistration() {
		return dateRegistration;
	}

	@Override
	public String toString() {
		return "Chapter [id=" + id + ", title=" + title + ", chapterNumber=" + chapterNumber + ", chapterText="
				+ chapterText + ", dateRegistration=" + dateRegistration + "]";
	}
}
