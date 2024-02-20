package com.springlab.hibernatetest.dao;

import java.util.List;

import com.springlab.hibernatetest.entities.Singer;

public interface SingerDao {

	List<Singer> findAll();
	Singer findById(Long id);
	String findLastNameById(Long id);
	String findFirstNameById(Long id);
	void insert(Singer singer);
	void update(Singer singer);
	void delete(Singer singer);
	List<Singer> findAllWithAlbums();
	void insertWithAlbum(Singer singer);

}
