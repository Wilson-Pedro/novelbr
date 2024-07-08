package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;
import java.util.Objects;

import com.wilsonpedro.novelbr.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "TBL_USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String pseudonym;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	public User() {
	}

	public User(Long id, String pseudonym, String email, String password) {
		this.id = id;
		this.pseudonym = pseudonym;
		this.email = email;
		this.password = password;
	}
	
	public User(UserDTO userDTO) {
		this.pseudonym = userDTO.getPseudonym();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
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

	@Override
	public int hashCode() {
		return Objects.hash(email, password, pseudonym);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(pseudonym, other.pseudonym);
	}
}
