package com.springlab.jdbctest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springlab.jdbctest.entity.Singer;

public class PlainSingerDao implements SingerDao {
	
	private static Logger log = LoggerFactory.getLogger(PlainSingerDao.class);
	
	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException ex) {
			log.error("데이터베이스 드라이브를 로딩할 수 없습니다.", ex);
		}
	}
	
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:h2:tcp://localhost/~/h2-db/test", 
				"sa", "1234");
	}
	
	private void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				log.error("데이터베이스 컨넥션를 닫을 때에 문제가 발생하였습니다.", ex);
			}
		}
	}

	@Override
	public List<Singer> findAll() {
		List<Singer> result = new ArrayList<Singer>();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM SINGER");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Singer singer = new Singer();
				singer.setId(resultSet.getLong("id"));
				singer.setFirstName(resultSet.getString("first_name"));
				singer.setLastName(resultSet.getString("last_name"));
				singer.setBirthDate(resultSet.getDate("birth_date"));
				result.add(singer);
			}
			resultSet.close();
			statement.close();
		} catch (SQLException ex) {
			log.error("SELCT 문장 실행 중에 에러 발생 : ", ex);
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	@Override
	public List<Singer> findByFirstName(String firstName) {
		throw new NotImplementedException("findByFirstName");
	}

	@Override
	public String findNameById(Long id) {
		throw new NotImplementedException("findNameById");
	}

	@Override
	public String findLastNameById(Long id) {
		throw new NotImplementedException("findLastNameById");
	}

	@Override
	public String findFirstNameById(Long id) {
		throw new NotImplementedException("findFirstNameById");
	}

	@Override
	public void insert(Singer singer) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO SINGER(FIRST_NAME, LAST_NAME, BIRTH_DATE)"
					+ " VALUES(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, singer.getFirstName());
			statement.setString(2, singer.getLastName());
			statement.setDate(3, singer.getBirthDate());
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				singer.setId(generatedKeys.getLong(1));
				log.info("Generated key: " + generatedKeys.getLong(1));
			}
			generatedKeys.close();
			statement.close();
		} catch (SQLException ex) {
			log.error("INSERT 문장 실행 중에 에러 발생 : ", ex);
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public void update(Singer singer) {
		throw new NotImplementedException("update");
	}

	@Override
	public void delete(Long singerId) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM SINGER WHERE ID = ?");
			statement.setLong(1, singerId);
			statement.execute();
			statement.close();
		} catch (SQLException ex) {
			log.error("DELETE 문장 실행 중에 에러 발생 : ", ex);
		} finally {
			closeConnection(connection);
		}
	}

	@Override
	public List<Singer> findAllWithAlbums() {
		throw new NotImplementedException("findAllWithAlbums");
	}

	@Override
	public void insertWithAlbum(Singer singer) {
		throw new NotImplementedException("insertWithAlbum");
	}

}
