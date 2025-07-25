package com.novelsbr.backend.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.novelsbr.backend.domain.dto.AuthorDTO;
import com.novelsbr.backend.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_AUTHOR")
public class Author implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String username;
	
	private String email;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	@CreationTimestamp
	private LocalDateTime dateRegistrion;
	
	public Author() {
	}
	
	public Author(AuthorDTO authorDTO) {
		this.name = authorDTO.getName();
		this.username = authorDTO.getUsername();
		this.email = authorDTO.getEmail();
		this.password = authorDTO.getPassword();
		this.role = authorDTO.getRole();
	}
	
	public Author(Long id, String name, String username, String email, String password, UserRole role) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.role == UserRole.AUTHOR) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_AUTHOR"), 
					new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
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

	public LocalDateTime getDateRegistrion() {
		return dateRegistrion;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", password="
				+ password + ", dateRegistrion=" + dateRegistrion + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateRegistrion, email, id, name, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(dateRegistrion, other.dateRegistrion) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && role == other.role
				&& Objects.equals(username, other.username);
	}
}
