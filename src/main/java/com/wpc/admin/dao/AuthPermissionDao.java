package com.wpc.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wpc.admin.dto.PermissionDto;
import com.wpc.admin.entity.AuthPermission;
import com.wpc.common.BaseDao;
/**
 * 操作相关
 * author wpc
 */
public interface AuthPermissionDao extends BaseDao<AuthPermission, Integer> {
	
	public final static String BEAN_ID="authPermissionDao";
	
	public List<AuthPermission> queryPermissionByRoleId(int rid);
	
	/**
	 * 通过资源id和资源类型获取权限数据
	 * @param resourceId
	 * @return
	 */
	public AuthPermission findByResourceId(@Param("resourceId")int resourceId, @Param("permissionType")String permissionType);
	
	/**
	 * 通过资源id和资源类型删除权限数据
	 * @param resourceId
	 * @return
	 */
	public void deleteByResourceId(@Param("resourceId")int resourceId, @Param("permissionType")String permissionType);
	
	/**
	 * 通过资源id和资源类型删除权限数据
	 * @param resourceId
	 * @return
	 */
	public void deleteByParentId(@Param("parentId")int parentId, @Param("permissionType")String permissionType);
	
	/**
	 * 获取所有权限，区分角色是否具有该角色
	 * @param roleId
	 */
	List<PermissionDto> getAllPermissionsByRole(int roleId);
	
}
