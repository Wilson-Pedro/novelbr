package com.novelsbr.backend.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.enums.CommentBy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_COMMENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	@Enumerated(EnumType.STRING)
	private CommentBy commentBy;
	
	@Column(columnDefinition = "TEXT")
	private String text;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_father_id")
	@JsonBackReference
	private Comment commentFather;
	
	@OneToMany(mappedBy = "commentFather", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Comment> comments = new ArrayList<>();
	
	@CreationTimestamp
	private LocalDateTime dateRegistration;
	
	public Comment() {
	}
	
	public Comment(CommentDTO commentDTO) {
		this.id = commentDTO.getId() == null ? null : commentDTO.getId();
		this.commentBy = CommentBy.toEnum(commentDTO.getCommentByCode());
		this.text = commentDTO.getText();
		this.dateRegistration = commentDTO.getDateRegistration();
	}

	public Comment(Long id, Author author, CommentBy commentBy, String text, LocalDateTime dateRegistration) {
		this.id = id;
		this.author = author;
		this.commentBy = commentBy;
		this.text = text;
		this.dateRegistration = dateRegistration;
	}

	public Comment(Long id, Author author, CommentBy commentBy, String text, Comment commentFather,
			LocalDateTime dateRegistration) {
		this(id, author, commentBy, text, dateRegistration);
		this.commentFather = commentFather == null ? null : commentFather;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public CommentBy getCommentBy() {
		return commentBy;
	}

	public void setCommentBy(CommentBy commentBy) {
		this.commentBy = commentBy;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Comment getCommentFather() {
		return commentFather == null ? null : commentFather;
	}

	public void setCommentFather(Comment commentFather) {
		this.commentFather = commentFather;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public LocalDateTime getDateRegistration() {
		return dateRegistration;
	}
	
	public boolean isNovel() {
		return this.commentBy.equals(CommentBy.NOVEL);
	}
	
	public boolean isChapter() {
		return this.commentBy.equals(CommentBy.CHAPTER);
	}
	
	public abstract Long getEntityId();

	@Override
	public String toString() {
		return "Comment [id=" + id + ", author=" + author + ", commentType=" + commentBy + ", text=" + text
				+ ", commentFather=" + commentFather + ", comments=" + comments + ", dateRegistration="
				+ dateRegistration + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, commentBy, dateRegistration, id, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		return Objects.equals(author, other.author) && commentBy == other.commentBy
				&& Objects.equals(dateRegistration, other.dateRegistration) && Objects.equals(id, other.id)
				&& Objects.equals(text, other.text);
	}
}
