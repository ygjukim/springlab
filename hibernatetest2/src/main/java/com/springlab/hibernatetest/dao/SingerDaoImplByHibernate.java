package com.springlab.hibernatetest.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springlab.hibernatetest.entities.Singer;

@SuppressWarnings({ "unchecked", "deprecation" })
@Transactional
@Repository("singerDao")
public class SingerDaoImplByHibernate implements SingerDao {
	
	private static Logger logger = LoggerFactory.getLogger(SingerDaoImplByHibernate.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Singer s").list();
	}

	@Transactional(readOnly = true)
	@Override
	public Singer findById(Long id) {
		return sessionFactory.getCurrentSession()
				.createNamedQuery("Singer.findById", Singer.class)
				.setParameter("id", id).uniqueResult();
	}

	@Override
	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findFirstNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Singer singer) {
		sessionFactory.getCurrentSession().persist(singer);
		logger.info("Singer 레코드 등록, id: " + singer.getId());
	}

	@Override
	public void update(Singer singer) {
		sessionFactory.getCurrentSession().merge(singer);
	}

	@Override
	public void delete(Singer singer) {
		sessionFactory.getCurrentSession().remove(singer);
		logger.info("Singer 레코드 삭제, id: " + singer.getId());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAllWithAlbums() {
		return sessionFactory.getCurrentSession()
//				.getNamedQuery("Singer.finaAllWithAlbums").list();
				.createNamedQuery("Singer.findAllWithAlbums", Singer.class).list();
	}

	@Override
	public void insertWithAlbum(Singer singer) {
		// TODO Auto-generated method stub

	}

}
