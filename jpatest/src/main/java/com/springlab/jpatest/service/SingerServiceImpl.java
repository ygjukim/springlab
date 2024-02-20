package com.springlab.jpatest.service;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springlab.jpatest.entities.Singer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Service("jpaSingerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {
	
	public static final String ALL_SINGER_NATIVE_QUERY = 
			"select id, first_name, last_name, birth_date, version from singer";
	
//	private Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAll() {
		return em.createNamedQuery("Singer.findAll", Singer.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Singer> findAllWithAblum() {
		return em.createNamedQuery("Singer.findAllWithAlbum", Singer.class).getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public Singer findById(Long id) {
		TypedQuery<Singer> query = em.createNamedQuery("Singer.findById", Singer.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public Singer save(Singer singer) {
		throw new NotImplementedException("save");
	}

	@Override
	public void delete(Singer singer) {
		throw new NotImplementedException("delete");
	}

}
