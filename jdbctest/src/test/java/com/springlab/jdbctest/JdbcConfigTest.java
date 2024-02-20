package com.springlab.jdbctest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springlab.jdbctest.dao.SingerDao;
import com.springlab.jdbctest.entity.Album;
import com.springlab.jdbctest.entity.Singer;

public class JdbcConfigTest {

	@Test
	public void testH2DB() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:config/embedded-h2-cfg.xml");
		ctx.refresh();
		
		testDao(ctx.getBean(SingerDao.class));
		
		ctx.close();
	}
	
	@Test
	public void testRowMapper() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:config/embedded-h2-cfg.xml");
		ctx.refresh();
		
		testDaoWithRowMapper(ctx.getBean(SingerDao.class));
		
		ctx.close();
		
	}
	
	@Test
	public void testResultSetExtractor() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:config/embedded-h2-cfg.xml");
		ctx.refresh();
		
		testDaoWithResultSetExtract(ctx.getBean(SingerDao.class));
		
		ctx.close();
		
	}
	
	private void testDao(SingerDao singerDao) {
		assertNotNull(singerDao);
		
		String singerName = singerDao.findNameById(1L);
		assertTrue("John Mayer".equals(singerName));		
	}
	
	private void testDaoWithRowMapper(SingerDao singerDao) {
		assertNotNull(singerDao);
		
		List<Singer> singers = singerDao.findAllWithAlbums();
		assertTrue(singers.size() == 3);
		
		singers.forEach((singer) -> {
			System.out.println(singer);
			if (singer.getAlbums() != null ) {
				for (Album album : singer.getAlbums()) {
					System.out.println("---" + album);
				}
			}
		});
	}
	
	private void testDaoWithResultSetExtract(SingerDao singerDao) {
		assertNotNull(singerDao);
		
		List<Singer> singers = singerDao.findAll();
		assertTrue(singers.size() == 3);
		
		singers.forEach((singer) -> {
			System.out.println(singer);
			if (singer.getAlbums() != null ) {
				for (Album album : singer.getAlbums()) {
					System.out.println("---" + album);
				}
				if (singer.getId() == 1L) {
					assertTrue(singer.getAlbums().size() == 2);
				}
				else if (singer.getId() == 2L) {
					assertTrue(singer.getAlbums().size() == 1);
				}
				else if (singer.getId() == 3L) {
					assertTrue(singer.getAlbums().size() == 0);
				}
			}
		});
	}
	
}
