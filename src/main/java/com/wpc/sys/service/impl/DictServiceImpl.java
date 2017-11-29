package com.wpc.sys.service.impl;

import com.wpc.common.base.service.impl.TreeBaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.sys.dao.DictDao;
import com.wpc.sys.model.Dict;
import com.wpc.sys.service.DictService;

/**
 * 功能描述: 
 * @Author: 王鹏程 
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
@Service
public class DictServiceImpl extends TreeBaseServiceImpl<Dict> implements DictService {

    @Autowired
    private DictDao dictDao;
    
}
