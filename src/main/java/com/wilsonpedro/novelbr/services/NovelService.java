package com.wilsonpedro.novelbr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilsonpedro.novelbr.repositories.NovelRepository;

@Service
public class NovelService {

	@Autowired
	private NovelRepository novelRepository;
}
