package com.novelsbr.backend.domain.dto;

import com.novelsbr.backend.domain.entities.Author;
import com.novelsbr.backend.enums.UserRole;

public class AuthorDTO {

	private Long id;
	
	private String name;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private UserRole role;
	
	public AuthorDTO() {
	}
	
	public AuthorDTO(Author author) {
		this.id = author.getId();
		this.name = author.getName();
		this.username = author.getUsername();
		this.email = author.getEmail();
		this.password = author.getPassword();
		this.role = author.getRole();
	}
	
	public AuthorDTO(String name, String username, String email) {
		this.name = name;
		this.username = username;
		this.email = email;
	}
	
	public AuthorDTO(Long id, String name, String username, String email, String password) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public AuthorDTO(Long id, String name, String username, String email, String password, UserRole role) {
		this(id, name, username, email, password);
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
