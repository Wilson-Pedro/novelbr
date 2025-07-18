package com.novelsbr.backend.domain.entities;

import java.io.Serializable;

import com.novelsbr.backend.domain.dto.GenderDTO;
import com.novelsbr.backend.enums.GenderType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_GENDER")
public class Gender implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private GenderType genderType;
	
	public Gender() {
	}

	public Gender(GenderType genderType) {
		this.id = genderType.getCode();
		this.genderType = genderType;
	}
	
	public Gender(GenderDTO genderDTO) {
		this.id = genderDTO.getId();
		this.genderType = GenderType.toEnum(genderDTO.getGenderType());
	}

	public Integer getId() {
		return id;
	}

	public GenderType getGenderType() {
		return genderType;
	}

	@Override
	public String toString() {
		return "Gender [id=" + id + ", genderType=" + genderType + "]";
	}
}
