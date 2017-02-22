package com.wpc.admin.service;

import java.util.List;

import com.wpc.admin.entity.User;
import com.wpc.common.BaseService;

/**
 * 操作相关
 * author wpc
 */
public interface UserService extends BaseService<User, Integer> {
	
	public final static String BEAN_ID="userService";
	
	public User getUserByAccount(String username);
	
	public List<User> queryUserByRole(int roleId);
	
}
