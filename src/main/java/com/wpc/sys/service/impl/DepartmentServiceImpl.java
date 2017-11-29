package com.wpc.sys.service.impl;

import com.wpc.common.base.service.impl.TreeBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.sys.dao.DepartmentDao;
import com.wpc.sys.model.Department;
import com.wpc.sys.service.DepartmentService;

/**
 * 功能描述: 
 * @Author: 王鹏程 
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
@Service
public class DepartmentServiceImpl extends TreeBaseServiceImpl<Department> implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;
    
}
