package com.springlab.biz.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springlab.biz.user.domain.UserDO;

import lombok.extern.slf4j.Slf4j;

@Repository("userDAO")
@Slf4j
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
		log.info("process insertUser()");
		
		jdbcTemplate.update(USER_INSERT,
				user.getId(), user.getPassword(), user.getName(), user.getRole());
	}

	@Override
	public void updateUser(UserDO user) {
		log.info("process updateUser()");
		
		jdbcTemplate.update(USER_UPDATE, 
				user.getPassword(), user.getName(), user.getRole(), user.getId());
	}

	@Override
	public void deleteUser(UserDO user) {
		log.info("process deleteUser()");
		
		jdbcTemplate.update(USER_DELETE, user.getId());
	}

	@Override
	public UserDO getUser(UserDO user) {
		log.info("process getUser()");
		
		try {
			user = jdbcTemplate.queryForObject(USER_GET, userRowMapper, user.getId());
		} catch(DataAccessException ex) {
			user = null;
		} 
		
		return user;
	}

	@Override
	public List<UserDO> getUserList(UserDO user) {
		log.info("process getUserList()");
		
		return jdbcTemplate.query(USER_LIST, userRowMapper);
	}

}
