package com.wilsonpedro.novelbr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilsonpedro.novelbr.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
}
