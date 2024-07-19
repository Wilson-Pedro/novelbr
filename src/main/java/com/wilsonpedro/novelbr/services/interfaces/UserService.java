package com.wilsonpedro.novelbr.services.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wilsonpedro.novelbr.entities.User;

public interface UserService {

	User save(User user);
	
	Page<User> findAll(Pageable pageable);
	
	List<User> findAll();
	
	User findById(Long id);
	
	User update(User user, Long id);
	
	void delete(Long id);
}
