package com.springlab.jdbctest.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

@Component("insertSinger")
public class InsertSinger extends SqlUpdate {
	
	private static final String SQL_INSERT_SINGER =
		"INSERT INTO SINGER(FIRST_NAME, LAST_NAME, BIRTH_DATE) "
			+ "VALUES (:first_name, :last_name, :birth_date)";
	
	@Autowired
	public InsertSinger(DataSource dataSource) {
		super(dataSource, SQL_INSERT_SINGER);
		super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("birth_date", Types.DATE));
		super.setGeneratedKeysColumnNames(new String[] {"id"});
		super.setReturnGeneratedKeys(true);
	}
	
}
