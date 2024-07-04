package com.wilsonpedro.novelbr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilsonpedro.novelbr.entities.Novel;

public interface NovelRepository extends JpaRepository<Novel, Long>{

}
