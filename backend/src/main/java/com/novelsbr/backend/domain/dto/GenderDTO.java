package com.novelsbr.backend.domain.dto;

import java.io.Serializable;

import com.novelsbr.backend.domain.entities.Gender;

public class GenderDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String genderType;
	
	public GenderDTO() {
	}

	public GenderDTO(Gender gender) {
		this.id = gender.getId();
		this.genderType = gender.getGenderType().getType();
	}

	public Integer getId() {
		return id;
	}

	public String getGenderType() {
		return genderType;
	}
}
