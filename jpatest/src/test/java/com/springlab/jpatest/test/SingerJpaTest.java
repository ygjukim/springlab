package com.springlab.jpatest.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springlab.jpatest.entities.Singer;
import com.springlab.jpatest.service.SingerService;
import com.springlab.jpatest.service.SingerSummaryUntypeImpl;

public class SingerJpaTest {

	private Logger logger = LoggerFactory.getLogger(SingerJpaTest.class);
	private GenericApplicationContext ctx = null;
	private SingerService singerService = null;
	private SingerSummaryUntypeImpl singerSummaryUntype = null;
	
	@Before
	public void setup() {
		ctx = new GenericXmlApplicationContext("classpath:config/jpatest-context-cfg.xml");
		singerService = ctx.getBean(SingerService.class);
		singerSummaryUntype = ctx.getBean(SingerSummaryUntypeImpl.class);
		assertNotNull(singerService);
		assertNotNull(singerSummaryUntype);
	}
	
	@After
	public void tearOff() {
		ctx.close();
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
	
//	@Test
	public void testFindAll() {
		listSingers(singerService.findAll());
	}
	
//	@Test
	public void testFindAllWithAlbum() {
		listSingersWithAlbums(singerService.findAllWithAblum());
	}
	
	@Test
	public void testFindById() {
		Singer singer = singerService.findById(1L);
		assertNotNull(singer);
		
		List<Singer> singers = new ArrayList<>();
		singers.add(singer);
		listSingersWithAlbums(singers);
	}
	
	@Test
	public void testFindAllUntype() {
		singerSummaryUntype.displayAllSingerSummary();
	}
	
}
