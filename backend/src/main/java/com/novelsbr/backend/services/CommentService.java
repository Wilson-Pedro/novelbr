package com.novelsbr.backend.services;

import java.util.List;

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.domain.entities.Comment;

public interface CommentService {

	void save(CommentDTO commentDTO);
	
	List<Comment> findAll();
}
