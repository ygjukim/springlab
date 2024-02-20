package com.springlab.jdbctest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import com.springlab.jdbctest.entity.Singer;

@Component("selectSingerByFirstName")
public class SelectSingerByFirstName extends MappingSqlQuery<Singer> {

	private static final String SQL_FIND_BY_FIRST_NAME = 
		"SELECT * FROM SINGER WHERE FIRST_NAME = :first_name";
	
	@Autowired
	public SelectSingerByFirstName(DataSource dataSource) {
		super(dataSource, SQL_FIND_BY_FIRST_NAME);
		super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
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
