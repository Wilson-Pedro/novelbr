package com.wilsonpedro.novelbr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.wilsonpedro.novelbr.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	boolean existsByPseudonym(String pseudonym);
	
	boolean existsByEmail(String email);

	UserDetails findByPseudonym(String pseudonym);
}
