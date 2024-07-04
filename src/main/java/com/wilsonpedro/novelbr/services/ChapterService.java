package com.wilsonpedro.novelbr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilsonpedro.novelbr.repositories.ChapterRepository;

@Service
public class ChapterService {

	@Autowired
	private ChapterRepository chapterRepository;
}
