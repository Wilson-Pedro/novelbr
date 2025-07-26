package com.novelsbr.backend.services.strategy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.novelsbr.backend.enums.CommentBy;
import com.novelsbr.backend.exceptions.TypeNotFoundException;
import com.novelsbr.backend.services.CommentStrategy;

@Component
public class CommentStrategyProvider {
	
	private final Map<CommentBy, CommentStrategy> mapStrategy;
	
	public CommentStrategyProvider(
			CommentNovelStrategy commentNovelStrategy,
			CommentChapterStrategy commentChapterStrategy) {
		this.mapStrategy = Map.of(
				CommentBy.NOVEL, commentNovelStrategy,
				CommentBy.CHAPTER, commentChapterStrategy
		);
	}
	
	public CommentStrategy getStrategy(CommentBy commentBy) {
		CommentStrategy commentStrategy = mapStrategy.get(commentBy);
		if(commentStrategy == null) throw new TypeNotFoundException("Type " +commentBy + " not found");
		return commentStrategy;
	}
}
