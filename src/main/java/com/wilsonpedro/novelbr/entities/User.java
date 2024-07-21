package com.wilsonpedro.novelbr.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "TBL_USER")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable, UserDetails{
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
	
	public User(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.pseudonym = userDTO.getPseudonym();
		this.userType = UserType.toUserType(userDTO.getUserType());
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
	}
	
	public boolean isAuthor() {
		return userType.equals(UserType.AUTHOR);
	}
	
	public boolean isAReader() {
		return userType.equals(UserType.READER);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.userType == UserType.AUTHOR) 
			return List.of(new SimpleGrantedAuthority("ROLE_AUTHOR"), new SimpleGrantedAuthority("ROLE_READER"));
		else return List.of(new SimpleGrantedAuthority("ROLE_READER"));
	}

	@Override
	public String getUsername() {
		return pseudonym;
	}
}
