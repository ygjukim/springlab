package com.springlab.jdbctest.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Component;

@Component("updateSinger")
public class UpdateSinger extends SqlUpdate {
	
	private static final String SQL_UPDATE_SINGER =
		"UPDATE SINGER SET FIRST_NAME=:first_name, LAST_NAME=:last_name, "
		+ "BIRTH_DATE=:birth_date WHERE ID = :id";
	
	@Autowired
	public UpdateSinger(DataSource dataSource) {
		super(dataSource, SQL_UPDATE_SINGER);
		super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("birth_date", Types.DATE));
		super.declareParameter(new SqlParameter("id", Types.INTEGER));
	}
	
}
