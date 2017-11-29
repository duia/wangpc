/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.service;

import com.wpc.sys.model.Menu;
import com.wpc.common.base.service.BaseService;

import java.util.List;

/**
 * 功能描述:
 * @Author: 王鹏程
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
public interface MenuService extends BaseService<Menu> {

    List<Menu> getLeftMenu();

    Long saveOrUpdateMenu(Menu menu);

}
