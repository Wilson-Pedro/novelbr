package com.wilsonpedro.novelbr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.exceptionhandler.exceptions.EntityNotFoundException;
import com.wilsonpedro.novelbr.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Transactional
	public User update(User user, Long id) {
		User userFinded = findById(id);
		userFinded.setPseudonym(user.getPseudonym());
		userFinded.setEmail(user.getEmail());
		userFinded.setPassword(user.getPassword());
		return userRepository.save(userFinded);
	}

	@Transactional
	public void delete(Long id) {
		userRepository.delete(findById(id));
		
	}
}
