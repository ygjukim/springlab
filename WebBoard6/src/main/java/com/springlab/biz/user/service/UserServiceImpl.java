package com.springlab.biz.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.domain.UserDO;

import lombok.extern.slf4j.Slf4j;

@Service("userService")
@Transactional(readOnly=true, rollbackFor=Throwable.class)
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
//	@Qualifier("userDAO")
	private UserDAO dao;
	
	@Transactional(readOnly=false)
	@Override
	public void insertUser(UserDO user) {
		log.info("process insertUser()");
		dao.insertUser(user);
	}

	@Transactional(readOnly=false)
	@Override
	public void updateUser(UserDO user) {
		log.info("process updateUser()");
		dao.updateUser(user);
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteUser(UserDO user) {
		log.info("process deleteUser()");
		dao.deleteUser(user);
	}

	@Override
	public UserDO getUser(UserDO user) {
		log.info(" process getUser()");
		return dao.getUser(user);
	}

	@Override
	public List<UserDO> getUserList(UserDO user) {
		log.info("process getUserList()");
		return dao.getUserList(user);
	}

}
