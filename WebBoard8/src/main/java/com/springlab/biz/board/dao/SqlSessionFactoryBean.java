package com.springlab.biz.board.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {

	private static SqlSessionFactory sqlSessionFactory = null;
	static {
		if (sqlSessionFactory == null) {
			try {
				Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static SqlSession getSqlSessionInstance() {
		return sqlSessionFactory.openSession();
	}
	
}
