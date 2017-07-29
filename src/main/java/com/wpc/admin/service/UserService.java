/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.admin.service;

import java.util.List;

import com.wpc.admin.entity.User;
import com.wpc.common.base.service.BaseService;

/**
 * 功能描述:
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public interface UserService extends BaseService<User, Long> {
	
	User getUserByAccount(String username);
	
	List<User> queryUserByRole(long roleId);
	
}
