package com.wpc.admin.service;

import java.util.List;

import com.wpc.admin.dto.PermissionDto;
import com.wpc.admin.entity.AuthElement;
import com.wpc.admin.entity.AuthFile;
import com.wpc.admin.entity.AuthMenu;
import com.wpc.admin.entity.AuthPermission;
import com.wpc.common.base.service.BaseService;

/**
 * 操作相关
 * author wpc
 */
public interface AuthPermissionService extends BaseService<AuthPermission, Long> {
	
	public final static String BEAN_ID="authPermissionService";
	
	public static String PER_TYPE_MENU = "menu";
	public static String PER_TYPE_ELEMENT = "element";
	public static String PER_TYPE_FILE = "file";
	
	public static int OPERATION_COUNT = 4;
	
	public static String[] OPERATION_NAMES = {"查看", "保存", "修改" ,"删除"};
	public static String[] OPERATION_CODES = {"view", "save", "update", "delete"};
	
	
	/**
	 * 根据传来的资源为该资源添加对应的权限
	 * @param element （菜单，按钮，文件）
	 */
	void addMenuPermission(AuthMenu menu);
	void addElementPermission(AuthElement element);
	void addFilePermission(AuthFile file);
	
	/**
	 * 获取所有权限，区分角色是否具有该角色
	 * @param roleId
	 */
	List<PermissionDto> getAllPermissionsByRole(long roleId);
	
}
