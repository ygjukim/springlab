package com.springlab.jpatest.service;

import java.util.List;

import com.springlab.jpatest.entities.Singer;

public interface SingerService {

	List<Singer> findAll();
	List<Singer> findAllWithAblum();
	Singer findById(Long id);
	Singer save(Singer singer);
	void delete(Singer singer);
	
}
