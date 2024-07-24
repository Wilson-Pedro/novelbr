package com.wilsonpedro.novelbr.web.apis;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wilsonpedro.novelbr.dto.UserDTO;

import jakarta.validation.Valid;

@RequestMapping("/users")
public interface UserAPI {

	@PostMapping(produces = "application/json", path="/")
	ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO);
	
	@GetMapping(produces = "application/json")
	ResponseEntity<List<UserDTO>> findAll();
	
	@GetMapping(produces = "application/json", path="/pages")
	ResponseEntity<Page<UserDTO>> pages(Pageable pageable);
	
	@GetMapping(produces = "application/json", path="/{id}")
	ResponseEntity<UserDTO> findById(@PathVariable Long id);
	
	@PutMapping(produces = "application/json", path="/{id}")
	ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id);
	
	@DeleteMapping(produces = "application/json", path="/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id);
	
	@PatchMapping(produces = "application/json", path="/{id}/toAuthor")
	ResponseEntity<Void> toAuthor(@PathVariable Long id);
}
