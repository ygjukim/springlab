package com.springlab.jdbctest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springlab.jdbctest.entity.Album;
import com.springlab.jdbctest.entity.Singer;

//@Repository("singerDao")
public class JdbcSingerDao implements SingerDao {
	
	@Autowired
//	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
//	public List<Singer> findAll() {
//		return jdbcTemplate.query(
//				"SELECT * FROM SINGER", 
//				new SingerMapper());
//	}
	public List<Singer> findAll() {
		return jdbcTemplate.query("SELECT * FROM SINGER", 
				(rs, rownum) -> {
					Singer singer = new Singer();
					singer.setId(rs.getLong("id"));
					singer.setFirstName(rs.getString("first_name"));
					singer.setLastName(rs.getString("last_name"));
					singer.setBirthDate(rs.getDate("birth_date"));
					return singer;					
				});
	}

	@Override
	public List<Singer> findByFirstName(String firstName) {
		throw new NotImplementedException("findByFirstName");
	}

	@Override
	public String findNameById(Long id) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("singerId", id);		
		return jdbcTemplate.queryForObject(
				"SELECT FIRST_NAME || ' ' || LAST_NAME FROM SINGER WHERE ID = :singerId", 
				namedParameters, String.class);
		
//		return jdbcTemplate.queryForObject(
//				"SELECT FIRST_NAME || ' ' || LAST_NAME FROM SINGER WHERE ID = ?", 
//				new Object[] {id}, 
//				String.class);
	}

	@Override
	public String findLastNameById(Long id) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("singerId", id);		
		return jdbcTemplate.queryForObject(
				"SELECT LAST_NAME FROM SINGER WHERE ID = :singerId", 
				namedParameters, String.class);

//		return jdbcTemplate.queryForObject(
//				"SELECT LAST_NAME FROM SINGER WHERE ID = ?", 
//				new Object[] {id}, 
//				String.class);
	}

	@Override
	public String findFirstNameById(Long id) {
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("singerId", id);		
		return jdbcTemplate.queryForObject(
				"SELECT FIRST_NAME FROM SINGER WHERE ID = :singerId", 
				namedParameters, String.class);

//		return jdbcTemplate.queryForObject(
//				"SELECT FIRST_NAME FROM SINGER WHERE ID = ?", 
//				new Object[] {id}, 
//				String.class);
	}

	@Override
	public void insert(Singer singer) {
		throw new NotImplementedException("insert");
	}

	@Override
	public void update(Singer singer) {
		throw new NotImplementedException("update");
	}

	@Override
	public void delete(Long singerId) {
		throw new NotImplementedException("delete");
	}

	@Override
//	public List<Singer> findAllWithAlbums() {
//		return jdbcTemplate.query(
//			"SELECT S.*, A.ID as ALBUM_ID, A.TITLE, A.RELEASE_DATE "
//			+ "FROM SINGER S LEFT JOIN ALBUM A ON S.ID = A.SINGER_ID",
//			new SingerWithAlbumsExtractor());
//	}
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
		throw new NotImplementedException("insertWithAlbum");
	}
	
	private static final class SingerMapper implements RowMapper<Singer> {

		@Override
		public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Singer singer = new Singer();
			singer.setId(rs.getLong("id"));
			singer.setFirstName(rs.getString("first_name"));
			singer.setLastName(rs.getString("last_name"));
			singer.setBirthDate(rs.getDate("birth_date"));
			return singer;
		}
		
	}
	
	private static final class SingerWithAlbumsExtractor 
		implements ResultSetExtractor<List<Singer>> {

		@Override
		public List<Singer> extractData(ResultSet rs) throws SQLException, DataAccessException {
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
		}
		
	}

}
