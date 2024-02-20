package com.springlab.jdbctest.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.springlab.jdbctest.entity.Album;
import com.springlab.jdbctest.entity.Singer;

@Repository("singerDao")
public class JdbcSingerDao2 implements SingerDao {
	
	private static Logger log = LoggerFactory.getLogger(JdbcSingerDao2.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private SelectAllSingers selectAllSingers;

	@Autowired
	private SelectSingerByFirstName selectSingerByFirstName;

	@Autowired
	private UpdateSinger updateSinger;

	@Autowired
	private InsertSinger insertSinger;

	@Override
	public List<Singer> findAll() {
		return selectAllSingers.execute();
	}
	
	@Override
	public List<Singer> findByFirstName(String firstName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", firstName);
		return selectSingerByFirstName.executeByNamedParam(paramMap);
	}

	@Override
	public String findNameById(Long id) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("singerId", id);		
		return jdbcTemplate.queryForObject(
				"SELECT FIRST_NAME || ' ' || LAST_NAME FROM SINGER WHERE ID = :singerId", 
				namedParameters, String.class);
	}

	@Override
	public String findLastNameById(Long id) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("singerId", id);		
		return jdbcTemplate.queryForObject(
				"SELECT LAST_NAME FROM SINGER WHERE ID = :singerId", 
				namedParameters, String.class);
	}

	@Override
	public String findFirstNameById(Long id) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("singerId", id);		
		return jdbcTemplate.queryForObject(
				"SELECT FIRST_NAME FROM SINGER WHERE ID = :singerId", 
				namedParameters, String.class);
	}

	@Override
	public void insert(Singer singer) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(paramMap, keyHolder);
		singer.setId(keyHolder.getKey().longValue());
		log.info("새 가수 등록: id = " + singer.getId());
	}

	@Override
	public void update(Singer singer) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		paramMap.put("id", singer.getId());
		updateSinger.updateByNamedParam(paramMap);
		log.info("기존 가수 정보 수정: id = " + singer.getId());
	}

	@Override
	public void delete(Long singerId) {
		throw new NotImplementedException("delete");
	}

	@Override
	public List<Singer> findAllWithAlbums() {
		return jdbcTemplate.query(
			"SELECT S.*, A.ID as ALBUM_ID, A.TITLE, A.RELEASE_DATE "
			+ "FROM SINGER S LEFT JOIN ALBUM A ON S.ID = A.SINGER_ID",
			(rs) -> {
				Map<Long, Singer> map = new HashMap<>();
				Singer singer;
				while (rs.next()) {
					Long id = rs.getLong("id");
					singer = map.get(id);
					if (singer == null) {
						singer = new Singer();
						singer.setId(rs.getLong("id"));
						singer.setFirstName(rs.getString("first_name"));
						singer.setLastName(rs.getString("last_name"));
						singer.setBirthDate(rs.getDate("birth_date"));
						singer.setAlbums(new ArrayList<Album>());
						map.put(id, singer);
					}
					Long albumId = rs.getLong("album_id");
					if (albumId > 0) {
						Album album = new Album();
						album.setId(albumId);
						album.setSingerId(id);
						album.setTitle(rs.getString("title"));
						album.setReleaseDate(rs.getDate("release_date"));
						singer.addAlbum(album);
					}
				}
				return new ArrayList<>(map.values());				
			});
	}

	@Override
	public void insertWithAlbum(Singer singer) {
		insert(singer);
		
		List<Album> albums = singer.getAlbums();
		if (albums != null && !albums.isEmpty()) {
			InsertSingerAlbum insertSingerAlbum = new InsertSingerAlbum(dataSource);
			Map<String, Object> paramMap = new HashMap<>();
			for (Album album : albums) {
				paramMap.put("singer_id", singer.getId());
				paramMap.put("title", album.getTitle());
				paramMap.put("release_date", album.getReleaseDate());
				insertSingerAlbum.updateByNamedParam(paramMap);
			}
			insertSingerAlbum.flush();
		}
	}
	
}
