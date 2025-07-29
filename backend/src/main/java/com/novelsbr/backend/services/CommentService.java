package com.novelsbr.backend.services;

import java.util.List;

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.domain.entities.Comment;
import com.novelsbr.backend.domain.projections.CommentProjection;

public interface CommentService {

	Comment save(CommentDTO commentDTO);
	
	List<Comment> findAll();
	
	List<CommentProjection> findAllNovelsByEntityId(Long entityId);
	
	List<CommentProjection> findAllChaptersByEntityId(Long entityId);
	
	Comment update(CommentDTO commentDTO, Long id);
	
	void delete(Long id);
}
