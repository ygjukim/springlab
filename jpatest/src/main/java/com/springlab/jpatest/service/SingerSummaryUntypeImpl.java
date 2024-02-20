package com.springlab.jpatest.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service("singerSummaryUntype")
@Repository
@Transactional
public class SingerSummaryUntypeImpl {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly = true)
	public void displayAllSingerSummary() {
		List result = em.createQuery(
				"select s.firstName, s.lastName, a.title from Singer s "
				+ "left join s.albums a "
				+ "where a.releaseDate = (select max(a2.releaseDate) "
				+ "from Album a2 where a2.singer.id = s.id)"
			).getResultList();
		int count = 0;
		for (Iterator i = result.iterator(); i.hasNext(); ) {
			Object[] values = (Object[])i.next();
			System.out.println(++count + ": " + values[0] + ", " + values[1] + ", " + values[2]);
		}
	}
}
