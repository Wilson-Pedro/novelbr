package com.novelsbr.backend.utils.mapper;

import com.novelsbr.backend.domain.dto.CommentDTO;
import com.novelsbr.backend.domain.dto.CommentMinDTO;
import com.novelsbr.backend.domain.entities.Comment;

public class CommentMapper {

	public static <T extends Comment> CommentDTO toDTO(T comment) {
		CommentDTO dto = new CommentDTO(comment);
		dto.setEntityId(comment.getEntityId());
		if(comment.getComments().size() > 0) {
			dto.setComments(comment.getComments()
					.stream().map(x -> new CommentMinDTO(x)).toList());
		}
		return dto;
	}
}
