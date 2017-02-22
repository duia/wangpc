package com.wpc.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.UserDao;
import com.wpc.admin.entity.User;
import com.wpc.admin.service.UserService;
import com.wpc.common.BaseServiceImpl;

/**
 * 操作相关
 * author wpc
 */
@Service(UserService.BEAN_ID)
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource(name=UserDao.BEAN_ID)
	private UserDao userDao;

	@Override
	public User getUserByAccount(String username) {
		// TODO Auto-generated method stub
		User query = new User();
		query.setUsername(username);
		List<User> list = userDao.search(query);
		if(list.size()>0) return list.get(0);
		return null;
	}

	@Override
	public List<User> queryUserByRole(int roleId) {
		// TODO Auto-generated method stub
		return userDao.queryUserByRole(roleId);
	}
	
}
