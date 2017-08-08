package com.wpc.sys.service.impl;

import com.wpc.annotation.CacheAnn;
import com.wpc.common.base.service.impl.BaseServiceImpl;
import com.wpc.enums.ECacheDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.sys.dao.UserDao;
import com.wpc.sys.model.User;
import com.wpc.sys.service.UserService;

import java.util.List;

/**
 * 功能描述: 
 * @Author: 王鹏程 
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByAccount(String loginNme) {
        // TODO Auto-generated method stub
        User query = new User();
        query.setLoginName(loginNme);
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