package com.wilsonpedro.novelbr.dto;

import java.io.Serializable;

import com.wilsonpedro.novelbr.entities.User;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String pseudonym;
	
	@NotBlank
	private String userType;
	
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	
	@NotBlank
	private String password;
	
	public UserDTO() {
	}

	public UserDTO(Long id, String pseudonym, String userType, String email, String password) {
		this.pseudonym = pseudonym;
		this.userType = userType;
		this.email = email;
		this.password = password;
	}
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.pseudonym = user.getPseudonym();
		this.userType = user.getUserType().getDescription();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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
}
