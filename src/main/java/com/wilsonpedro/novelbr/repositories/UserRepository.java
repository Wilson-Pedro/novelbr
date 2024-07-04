package com.wilsonpedro.novelbr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilsonpedro.novelbr.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
