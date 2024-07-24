package com.wilsonpedro.novelbr.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilsonpedro.novelbr.dto.UserDTO;
import com.wilsonpedro.novelbr.entities.User;
import com.wilsonpedro.novelbr.services.interfaces.UserService;
import com.wilsonpedro.novelbr.web.apis.UserAPI;

import jakarta.validation.Valid;

@RestController
public class UserController implements UserAPI {
	
	@Autowired
	private UserService userService;
	
	@Override
	public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO) {
		User userSaved = userService.save(new User(userDTO));
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(userSaved));
	}
	
	@Override
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok(list.stream().map(x -> new UserDTO(x)).toList());
	}

	@Override
	public ResponseEntity<Page<UserDTO>> pages(Pageable pageable) {
		Page<User> page = userService.findAll(pageable);
		return ResponseEntity.ok(page.map(UserDTO::new));
	}
	
	@Override
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(new UserDTO(userService.findById(id)));
	}
	
	@Override
	public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) {
		User userUpdated = userService.update(new User(userDTO), id);
		return ResponseEntity.ok(new UserDTO(userUpdated));
	}
	
	@Override
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
