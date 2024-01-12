package com.springlab.biz.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.domain.UserDO;

@Component("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDAO")
	private UserDAO dao;
	
	@Override
	public void insertUser(UserDO user) {
		System.out.println(">>> UserServiceImpl : process insertUser()");
		dao.insertUser(user);
	}

	@Override
	public void updateUser(UserDO user) {
		System.out.println(">>> UserServiceImpl : process updateUser()");
		dao.updateUser(user);
	}

	@Override
	public void deleteUser(UserDO user) {
		System.out.println(">>> UserServiceImpl : process deleteUser()");
		dao.deleteUser(user);
	}

	@Override
	public UserDO getUser(UserDO user) {
		System.out.println(">>> UserServiceImpl : process getUser()");
		return dao.getUser(user);
	}

	@Override
	public List<UserDO> getUserList(UserDO user) {
		System.out.println(">>> UserServiceImpl : process getUserList()");
		return dao.getUserList(user);
	}

}
