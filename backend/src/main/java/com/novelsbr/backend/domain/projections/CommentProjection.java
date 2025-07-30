package com.novelsbr.backend.domain.projections;

import java.time.LocalDateTime;

public interface CommentProjection {

	Long getCommentId();
	
	Long getAuthorId();
	
	String getUsername();
	
	Long getCommentFatherId();
	
	LocalDateTime getDateRegistration();
	
	Long getEntityId();
	
	String getBodyText();
	
	String getCommentBy();
}
