package com.springlab.jdbctest.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.stereotype.Component;

//@Component("insertSingerAlbum")
//@Scope("prototype")
public class InsertSingerAlbum extends BatchSqlUpdate {
		
	private static final String SQL_INSERT_SINGER_ALBUM =
		"INSERT INTO ALBUM(SINGER_ID, TITLE, RELEASE_DATE) "
			+ "VALUES (:singer_id, :title, :release_date)";
	
	private static final int BATCH_SIZE = 10; 
	
	@Autowired
	public InsertSingerAlbum(DataSource dataSource) {
		super(dataSource, SQL_INSERT_SINGER_ALBUM);
		
		super.declareParameter(new SqlParameter("singer_id", Types.INTEGER));
		super.declareParameter(new SqlParameter("title", Types.VARCHAR));
		super.declareParameter(new SqlParameter("release_date", Types.DATE));
		
		this.setBatchSize(BATCH_SIZE);
	}
	
}
