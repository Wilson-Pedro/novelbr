package com.novelsbr.backend.services;

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.domain.entities.Comment;

public interface CommentStrategy {

	Comment getNewComment(CommentDTO commentDTO);
}
