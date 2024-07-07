package com.wilsonpedro.novelbr.dto;

import java.io.Serializable;

import com.wilsonpedro.novelbr.entities.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String pseudonym;
	
	private String email;
	
	private String password;
	
	public UserDTO() {
	}

	public UserDTO(String pseudonym, String email, String password) {
		this.pseudonym = pseudonym;
		this.email = email;
		this.password = password;
	}
	
	public UserDTO(User user) {
		this.pseudonym = user.getPseudonym();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
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
