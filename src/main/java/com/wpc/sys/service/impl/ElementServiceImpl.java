package com.wpc.sys.service.impl;

import com.wpc.common.base.service.impl.BaseServiceImpl;
import com.wpc.sys.dao.MenuDao;
import com.wpc.sys.dao.PermissionDao;
import com.wpc.sys.model.Menu;
import com.wpc.sys.model.Permission;
import com.wpc.sys.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.sys.dao.ElementDao;
import com.wpc.sys.model.Element;
import com.wpc.sys.service.ElementService;

import java.util.List;

/**
 * 功能描述: 
 * @Author: 王鹏程 
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
@Service
public class ElementServiceImpl extends BaseServiceImpl<Element, Long> implements ElementService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private ElementDao elementDao;
    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private PermissionService permissionService;

    @Override
    public void addDefaultElements(Menu menu) {
        Element element = null;
        for (int i = 0; i < Permission.OPERATION_COUNT; i++) {
            element = new Element();
            element.setMenuId(menu.getId());
            element.setElementName(Permission.OPERATION_NAMES[i]);
            element.setElementCode(Permission.OPERATION_CODES[i]);
            element.setElementDesc(menu.getMenuName()+"_"+Permission.OPERATION_NAMES[i]);
            elementDao.save(element);
            //给element添加权限
            permissionService.addElementPermission(element);
        }
    }

    @Override
    public List<Element> queryElementByMenuId(long menuId) {
        // TODO Auto-generated method stub
        Element query = new Element();
        query.setMenuId(menuId);
        return elementDao.search(query);
    }

    @Override
    public void saveOrUpdate(Element t) {
        if(t.getId()!=null && t.getId()!=0){
            elementDao.update(t);
        }else{
            elementDao.save(t);
        }
        permissionService.addElementPermission(t);
    }

    @Override
    public void delete(Long id) {
        permissionDao.deleteByResourceId(id, Permission.PER_TYPE_ELEMENT);
        super.delete(id);
    }

}
