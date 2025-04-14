package com.novelsbr.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novelsbr.backend.domain.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer>{

}
