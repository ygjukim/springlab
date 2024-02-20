package com.springlab.jdbctest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import com.springlab.jdbctest.entity.Singer;

@Component("selectAllSingers")
public class SelectAllSingers extends MappingSqlQuery<Singer> {
	
	private static final String SQL_SELECT_ALL_SINGERS = "SELECT * FROM SINGER";
	
	@Autowired
	public SelectAllSingers(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_SINGERS);
	}

	@Override
	protected Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Singer singer = new Singer();
		singer.setId(rs.getLong("id"));
		singer.setFirstName(rs.getString("first_name"));
		singer.setLastName(rs.getString("last_name"));
		singer.setBirthDate(rs.getDate("birth_date"));
		return singer;
	}

}
