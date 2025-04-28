package com.novelsbr.backend.domain.projections;

import com.novelsbr.backend.enums.GenderType;

public interface GenderProjection {

	Integer getId();
	
	GenderType getGenderType();
}
