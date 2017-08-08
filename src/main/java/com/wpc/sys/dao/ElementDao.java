/*
 * Copyright(c) 2016 cncounter.com All rights reserved.
 * distributed with this file and available online at
 * http://www.cncounter.com/
 */
package com.wpc.sys.dao;

import com.wpc.sys.model.Element;
import com.wpc.common.base.dao.BaseDao;

public interface ElementDao extends BaseDao<Element, Long> {

    void deleteByMenuId(long menuId);

}