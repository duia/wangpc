package com.wpc.admin.service;

import java.util.List;

import com.wpc.admin.entity.AuthElement;
import com.wpc.admin.entity.AuthMenu;
import com.wpc.common.BaseService;

/**
 * 操作相关
 * author wpc
 */
public interface AuthElementService extends BaseService<AuthElement, Integer> {
	
	public final static String BEAN_ID="authElementService";
	
	/**
	 * 为菜单添加默认的四个按钮（查看，保存，修改，删除）
	 * @param menuId
	 * @param menuName
	 */
	public void addDefaultElements(AuthMenu menu);
	
	/**
	 * 通过菜单id获取按钮
	 * @param menuId
	 * @return
	 */
	public List<AuthElement> queryElementByMenuId(int menuId);
	
	/**
	 * 新增或修改
	 * @param element
	 */
	public void saveOrUpdate(AuthElement element);
	
}
