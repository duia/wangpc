package com.wpc.sys.service.impl;

import com.wpc.annotation.SysLogAnn;
import com.wpc.common.base.service.impl.BaseServiceImpl;
import com.wpc.enums.OperLevel;
import com.wpc.enums.OperType;
import com.wpc.sys.dao.ElementDao;
import com.wpc.sys.dao.PermissionDao;
import com.wpc.sys.model.Element;
import com.wpc.sys.service.ElementService;
import com.wpc.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.sys.dao.MenuDao;
import com.wpc.sys.model.Menu;
import com.wpc.sys.service.MenuService;

import java.util.List;

/**
 * 功能描述: 
 * @Author: 王鹏程 
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, Long> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MenuDao authMenuDao;
    @Autowired
    private ElementDao authElementDao;
    @Autowired
    private PermissionDao authPermissionDao;

    @Autowired
    private ElementService authElementService;
    @Autowired
    private PermissionService authPermissionService;

    @SysLogAnn(operType = OperType.SYSTEM, operLevel = OperLevel.NORM, describe = "获取菜单")
    @Override
    public List<Menu> getLeftMenu() {
        return authMenuDao.queryAll();//getLeftMenu();
    }

    @Override
    public void save(Menu menu) {
        // TODO Auto-generated method stub
        authMenuDao.save(menu);
        authPermissionService.addMenuPermission(menu);
        //首次保存要关联保存一下四个默认的按钮
        authElementService.addDefaultElements(menu);
    }

    @Override
    public void update(Menu menu) {
        super.update(menu);
        authPermissionService.addMenuPermission(menu);
        //修改时需要关联所有的按钮权限
        for(Element element : authElementService.queryElementByMenuId(menu.getId())){
            authPermissionService.addElementPermission(element);
        }
    }

    @Override
    public void delete(Long id) {
        authElementDao.deleteByMenuId(id);
        authPermissionDao.deleteByParentId(id, PermissionService.PER_TYPE_ELEMENT);
        authPermissionDao.deleteByResourceId(id, PermissionService.PER_TYPE_MENU);
        super.delete(id);
    }

}
