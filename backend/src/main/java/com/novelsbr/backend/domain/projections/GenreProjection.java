package com.novelsbr.backend.domain.projections;

import com.novelsbr.backend.enums.GenreType;

public interface GenreProjection {

	Integer getId();
	
	GenreType getGenreType();
}
