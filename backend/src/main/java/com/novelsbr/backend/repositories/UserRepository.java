package com.novelsbr.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novelsbr.backend.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
