/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.dao;

import com.wpc.sys.model.User;
import com.wpc.common.base.dao.BaseDao;

import java.util.List;

public interface UserDao extends BaseDao<User, Long> {

    User getUserByAccount(String account);

    List<User> queryUserByRole(long roleId);

}