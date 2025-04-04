package com.novelsbr.backend.domain.entities;

import java.time.LocalDate;

import com.novelsbr.backend.domain.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String username;
	
	private String email;
	
	private LocalDate dateRegistrion = LocalDate.now();
	
	public User(UserDTO userDTO) {
		this.name = userDTO.getName();
		this.username = userDTO.getUsername();
		this.email = userDTO.getEmail();
		this.dateRegistrion = userDTO.getDateRegistrion();
	}
	
	public User(Long id, String name, String username, String email, LocalDate dateRegistrion) {
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
