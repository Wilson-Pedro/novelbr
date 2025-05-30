package com.novelsbr.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.novelsbr.backend.domain.entities.Gender;
import com.novelsbr.backend.domain.projections.GenderProjection;

public interface GenderRepository extends JpaRepository<Gender, Integer>{

	@Query(nativeQuery = true, value = """
			SELECT g.gender_type FROM TBL_GENDER g 
			INNER JOIN TBL_NOVEL_GENERO ng ON ng.genero_id = g.id
			INNER JOIN TBL_NOVEL n ON ng.novel_id = n.id
			WHERE  n.id = :novelId
			""")
	List<GenderProjection> findGendersByNovelId(Long novelId);
	
	@Query(nativeQuery = true, value = """
			SELECT id, gender_type FROM TBL_GENDER ORDER BY GENDER_TYPE
			""")
	List<GenderProjection> findAllGenders();
}
