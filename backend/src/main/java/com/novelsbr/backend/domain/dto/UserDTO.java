package com.novelsbr.backend.domain.dto;

import java.time.LocalDate;

import com.novelsbr.backend.domain.entities.User;

public class UserDTO {

	private Long id;
	
	private String name;
	
	private String username;
	
	private String email;
	
	private LocalDate dateRegistrion;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.dateRegistrion = user.getDateRegistrion();
	}

	public UserDTO(Long id, String name, String username, String email, LocalDate dateRegistrion) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.dateRegistrion = dateRegistrion;
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

	public LocalDate getDateRegistrion() {
		return dateRegistrion;
	}

	public void setDateRegistrion(LocalDate dateRegistrion) {
		this.dateRegistrion = dateRegistrion;
	}
}
