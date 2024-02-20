package com.springlab.jdbctest.main;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

@SuppressWarnings("deprecation")
public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator {

	@Override
	protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
		if (sqlEx.getErrorCode() == -12345) {
			return new DeadlockLoserDataAccessException(task, sqlEx);
		}
		return null;
	}

}
