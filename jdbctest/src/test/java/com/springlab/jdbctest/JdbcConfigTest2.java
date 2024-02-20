package com.springlab.jdbctest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springlab.jdbctest.dao.SingerDao;
import com.springlab.jdbctest.entity.Album;
import com.springlab.jdbctest.entity.Singer;

public class JdbcConfigTest2 {
	
	private GenericXmlApplicationContext ctx;
	private SingerDao singerDao;

	@Before
	public void setup() {
		ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:config/embedded-h2-cfg.xml");
		ctx.refresh();
		
		singerDao = ctx.getBean(SingerDao.class);
		assertNotNull(singerDao);
	}
	
	@After
	public void tearDown() {
		ctx.close();
	}
	
	@Test
	public void testFindAll() {
		List<Singer> singers = singerDao.findAll();
		assertTrue(singers.size() == 3);
		listSingers(singers);
	}
	
	@Test
	public void testFindByFirstName() {
		List<Singer> singers = singerDao.findByFirstName("John");
		assertTrue(singers.size() == 2);
		listSingers(singers);
	}
	
	@Test
	public void testUpdateSinger() {
		Singer singer = new Singer();
		singer.setId(1L);
		singer.setFirstName("John Clayton");
		singer.setLastName("Mayer");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
		singerDao.update(singer);
		
		listSingers(singerDao.findAll());
	}
	
	@Test
	public void testInsertSinger() {
		Singer singer = new Singer();
		singer.setFirstName("Ed");
		singer.setLastName("Sheeran");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1971, 1, 17)).getTime().getTime()));
		singerDao.insert(singer);
		
		listSingers(singerDao.findAll());
	}
	
	@Test
	public void testInsertSingerWithAlbums() {
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
		
		
		singerDao.insertWithAlbum(singer);
		
		listSingers(singerDao.findAllWithAlbums());
	}
	
	private void listSingers(List<Singer> singers) {
		singers.forEach((singer) -> {
			System.out.println(singer);
			if (singer.getAlbums() != null ) {
				for (Album album : singer.getAlbums()) {
					System.out.println("---" + album);
				}
			}
		});
	}
	
}
