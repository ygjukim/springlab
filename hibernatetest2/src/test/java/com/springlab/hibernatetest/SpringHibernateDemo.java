package com.springlab.hibernatetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springlab.hibernatetest.dao.SingerDao;
import com.springlab.hibernatetest.entities.Album;
import com.springlab.hibernatetest.entities.Singer;

public class SpringHibernateDemo {

	private Logger logger = LoggerFactory.getLogger(SpringHibernateDemo.class);
	private GenericApplicationContext ctx = null;
	private SingerDao singerDao = null;
	
	@Before
	public void setup() {
		ctx = new GenericXmlApplicationContext("classpath:config/hibernate-cfg.xml");
		singerDao = ctx.getBean(SingerDao.class);
		assertNotNull(singerDao);
	}
	
	@After
	public void tearOff() {
		ctx.close();
	}
	
	@Test
	public void testDelete() {
		Singer singer = singerDao.findById(2L);
		assertNotNull(singer);
		
		singerDao.delete(singer);
		
		List<Singer> singers = singerDao.findAllWithAlbums();
		listSingersWithAlbums(singers);	
	}

//	@Test
	public void testUpdate() {
		Singer singer = singerDao.findById(1L);
		assertNotNull(singer);
		assertEquals("Mayer", singer.getLastName());
		
		Album album = singer.getAlbums().stream().filter(
					a -> a.getTitle().equals("Battle Studies")
				).findFirst().get();
		
		singer.setFirstName("John Clayton");
		singer.removeAlbum(album);
		singerDao.update(singer);
		
		List<Singer> singers = singerDao.findAllWithAlbums();
		listSingersWithAlbums(singers);	
	}
	
//	@Test
	public void testInsert() {
		Singer singer = new Singer();
		singer.setFirstName("BB");
		singer.setLastName("King");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
		
		Album album = new Album();
		album.setTitle("My kind od Blues");
		album.setReleaseDate(new Date(
				(new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
		singer.addAlbum(album);
		
		album = new Album();
		album.setTitle("Aheart full of Blues");
		album.setReleaseDate(new Date(
				(new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
		singer.addAlbum(album);
		
		singerDao.insert(singer);
		assertNotNull(singer.getId());
		
		List<Singer> singers = singerDao.findAllWithAlbums();
		assertEquals(singers.size(), 4);
		listSingersWithAlbums(singers);	
	}
	
//	@Test
	public void testFindAll() {
		listSingersWithAlbums(singerDao.findAllWithAlbums());
	}
	
//	@Test
	public void testFindById() {
		Singer singer = singerDao.findById(2L);
		List<Singer> singers = new ArrayList<>() ;
		singers.add(singer);
		listSingersWithAlbums(singers);
	}
	
	public void listSingers(List<Singer> singers) {
		logger.info("--- 가수 목록");
		if (singers.isEmpty()) {
			logger.info("검색 결과가 없음...");
		}
		else {
			for (Singer singer : singers) {
				logger.info(singer.toString());
			}
		}
	}
	
	public void listSingersWithAlbums(List<Singer> singers) {
		logger.info("--- 가수 목록(앨범 및 악기 목록 포함)");
		singers.forEach((s) -> {
			logger.info(s.toString());
			if (s.getAlbums() != null) {
				s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
			}
			if (s.getInstruments() != null) {
				s.getInstruments().forEach(i -> logger.info("\t" + i.toString()));
			}
		});
	}
	
}
