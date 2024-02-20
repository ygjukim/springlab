package com.springlab.jdbctest.dao;

import java.util.List;

import com.springlab.jdbctest.entity.Singer;

public interface SingerDao {

	List<Singer> findAll();
	List<Singer> findByFirstName(String firstName);
	String findNameById(Long id);
	String findLastNameById(Long id);
	String findFirstNameById(Long id);
	void insert(Singer singer);
	void update(Singer singer);
	void delete(Long singerId);
	List<Singer> findAllWithAlbums();
	void insertWithAlbum(Singer singer);
	
}
