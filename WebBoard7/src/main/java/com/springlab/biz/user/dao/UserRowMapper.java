package com.springlab.biz.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.springlab.biz.user.domain.UserDO;

@Component("userRowMapper")
public class UserRowMapper implements RowMapper<UserDO> {

	@Override
	public UserDO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDO user = new UserDO();
		user.setId(rs.getString("ID"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setName(rs.getString("NAME"));
		user.setRole(rs.getString("ROLE"));		
		return user;
	}

}
