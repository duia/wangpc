package com.wpc.sys.service.impl;

import com.wpc.sys.dto.PermissionDto;
import com.wpc.common.base.service.impl.BaseServiceImpl;
import com.wpc.sys.dao.MenuDao;
import com.wpc.sys.model.Element;
import com.wpc.sys.model.File;
import com.wpc.sys.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wpc.sys.dao.PermissionDao;
import com.wpc.sys.model.Permission;
import com.wpc.sys.service.PermissionService;

import java.util.Date;
import java.util.List;

/**
 * 功能描述: 
 * @Author: 王鹏程 
 * @E-mail: wpcfree@qq.com @QQ: 376205421
 * @Blog: http://www.wpcfree.com
 * @Date:
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private MenuDao authMenuDao;
    @Autowired
    private PermissionDao authPermissionDao;

    @Override
    public void addElementPermission(Element element) {
        boolean isExist = true;
        Menu menu = authMenuDao.findById(element.getMenuId());
        Permission per = authPermissionDao.findByResourceId(element.getId(), PermissionService.PER_TYPE_ELEMENT);
        if(per == null){
            per = new Permission();
            isExist = false;
        }
        per.setPermissionName(element.getElementName());
        per.setPermissionCode(menu.getMenuCode()+":"+element.getElementCode());
        per.setPermissionType(PermissionService.PER_TYPE_ELEMENT);
        per.setResourceId(element.getId());
        per.setParentId(element.getMenuId());
        per.setUpdateDate(new Date());
        if(isExist){
            authPermissionDao.update(per);
        }else{
            authPermissionDao.save(per);
        }
    }

    @Override
    public void addMenuPermission(Menu menu) {
        boolean isExist = true;
        Permission per = authPermissionDao.findByResourceId(menu.getId(), PermissionService.PER_TYPE_MENU);
        if(per == null){
            per = new Permission();
            isExist = false;
        }
        per.setPermissionName(menu.getMenuName());
        per.setPermissionCode(menu.getMenuCode());
        per.setPermissionType(PermissionService.PER_TYPE_MENU);
        per.setResourceId(menu.getId());
        per.setParentId(0L);
        per.setUpdateDate(new Date());
        if(isExist){
            authPermissionDao.update(per);
        }else{
            authPermissionDao.save(per);
        }
    }

    @Override
    public void addFilePermission(File file) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<PermissionDto> getAllPermissionsByRole(long roleId) {
        return authPermissionDao.getAllPermissionsByRole(roleId);
    }

}
