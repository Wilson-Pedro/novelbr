package com.novelsbr.backend.services;

import java.util.List;

import com.novelsbr.backend.domain.entities.Gender;

public interface GenderService {

	List<Gender> findAll();
}
