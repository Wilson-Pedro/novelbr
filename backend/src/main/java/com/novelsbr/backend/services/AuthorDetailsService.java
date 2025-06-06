package com.novelsbr.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.novelsbr.backend.repositories.AuthorRepository;

@Service
public class AuthorDetailsService implements UserDetailsService {
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return authorRepository.findByUsername(username);
	}
	
}
