package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;
import java.util.Objects;

import com.wilsonpedro.novelbr.dto.UserDTO;
import com.wilsonpedro.novelbr.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "TBL_USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true)
	private String pseudonym;
	
	@NotNull
	private UserType userType;
	
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	
	@NotBlank
	private String password;
	
	public User() {
	}

	public User(Long id, String pseudonym, UserType userType, String email, String password) {
		this.id = id;
		this.pseudonym = pseudonym;
		this.userType = userType;
		this.email = email;
		this.password = password;
	}
	
	public User(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.pseudonym = userDTO.getPseudonym();
		this.userType = UserType.toUserType(userDTO.getUserType());
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
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
	
	public boolean isAuthor() {
		return userType.equals(UserType.AUTHOR);
	}
	
	public boolean isAReader() {
		return userType.equals(UserType.READER);
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
