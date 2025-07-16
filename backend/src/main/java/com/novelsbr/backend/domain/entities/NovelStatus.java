package com.novelsbr.backend.domain.entities;

import java.io.Serializable;

import com.novelsbr.backend.domain.dto.NovelStatusDTO;
import com.novelsbr.backend.enums.NovelStatusType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TBL_NOVEL_STATUS")
public class NovelStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	private NovelStatusType novelStatusType;
	
	public NovelStatus() {
	}

	public NovelStatus(NovelStatusType novelStatusType) {
		this.id = novelStatusType.getCode();
		this.novelStatusType = novelStatusType;
	}

	public NovelStatus(NovelStatusDTO dto) {
		this.id = dto.getId();
		this.novelStatusType = NovelStatusType.toEnum(dto.getGenderType());
	}

	public Integer getId() {
		return id;
	}

	public NovelStatusType getNovelStatusType() {
		return novelStatusType;
	}
}
