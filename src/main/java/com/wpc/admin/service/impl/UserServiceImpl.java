/*
 * 文 件 名:  UserServiceImpl.java
 * 创 建 人:
 * 创建时间:
 */
package com.wpc.admin.service.impl;

import com.wpc.annotation.CacheAnn;
import com.wpc.common.base.service.impl.BaseServiceImpl;
import com.wpc.enums.ECacheDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.admin.dao.UserDao;
import com.wpc.admin.entity.User;
import com.wpc.admin.service.UserService;

import java.util.List;

/**
 * <一句话功能简述>
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
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
	public List<User> queryUserByRole(long roleId) {
		// TODO Auto-generated method stub
		return userDao.queryUserByRole(roleId);
	}

	@CacheAnn(groupKey = "user", eCacheDataSource = ECacheDataSource.WPC)
	@Override
	public User findById(Long id) {
		return super.findById(id);
	}

}
