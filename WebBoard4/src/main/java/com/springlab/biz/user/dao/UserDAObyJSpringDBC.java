package com.springlab.biz.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springlab.biz.board.dao.JDBCUtil;
import com.springlab.biz.user.domain.UserDO;

@Component("userDAO")
public class UserDAObyJSpringDBC implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserRowMapper userRowMapper;
	
	private final String USER_INSERT = "insert into USERS(ID, PASSWORD, NAME, ROLE) values(?, ?, ?, ?)";
	private final String USER_UPDATE = "update USERS set PASSWORD=?, NAME=?, ROLE=? where ID=?";
	private final String USER_DELETE = "delete USERS where ID=?";
	private final String USER_GET = "select * from USERS where ID=?";
	private final String USER_LIST = "select * from USERS order by ID";
	
	@Override
	public void insertUser(UserDO user) {
		System.out.println(">>> UserDAObySpringJDBC : process insertUser()");
		
		jdbcTemplate.update(USER_INSERT,
				user.getId(), user.getPassword(), user.getName(), user.getRole());
	}

	@Override
	public void updateUser(UserDO user) {
		System.out.println(">>> UserDAObySpringJDBC : process updateUser()");
		
		jdbcTemplate.update(USER_UPDATE, 
				user.getPassword(), user.getName(), user.getRole(), user.getId());
	}

	@Override
	public void deleteUser(UserDO user) {
		System.out.println(">>> UserDAObySpringJDBC : process deleteUser()");
		
		jdbcTemplate.update(USER_DELETE, user.getId());
	}

	@Override
	public UserDO getUser(UserDO user) {
		System.out.println(">>> UserDAObySpringJDBC : process getUser()");
				
		return jdbcTemplate.queryForObject(USER_GET, userRowMapper, user.getId());
	}

	@Override
	public List<UserDO> getUserList(UserDO user) {
		System.out.println(">>> UserDAObySpringJDBC : process getUserList()");
		
		return jdbcTemplate.query(USER_LIST, userRowMapper);
	}

}
