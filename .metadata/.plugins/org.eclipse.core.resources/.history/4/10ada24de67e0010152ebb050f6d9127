package com.novelsbr.backend.domain.dto;

import java.io.Serializable;

import com.novelsbr.backend.enums.NovelStatusType;

public class NovelStatusDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String genderType;
	
	public NovelStatusDTO() {
	}

	public NovelStatusDTO(NovelStatusType genderType) {
		this.id = genderType.getCode();
		this.genderType = genderType.getStatus();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public String getGenderType() {
		return genderType;
	}

	@Override
	public String toString() {
		return "NovelStatusDTO [id=" + id + ", genderType=" + genderType + "]";
	}
}
