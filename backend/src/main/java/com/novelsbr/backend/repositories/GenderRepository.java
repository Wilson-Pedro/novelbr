package com.novelsbr.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.projections.GenderProjection;

public interface GenderRepository extends JpaRepository<Gender, Integer>{
	
	@Query(nativeQuery = true, value = """
			SELECT id, gender_type FROM TBL_GENDER ORDER BY GENDER_TYPE
			""")
	List<GenderProjection> findAllGenders();
}
