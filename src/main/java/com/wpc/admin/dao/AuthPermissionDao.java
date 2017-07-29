package com.wpc.admin.dao;

import java.util.List;

import com.wpc.common.base.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import com.wpc.admin.dto.PermissionDto;
import com.wpc.admin.entity.AuthPermission;
/**
 * 操作相关
 * author wpc
 */
public interface AuthPermissionDao extends BaseDao<AuthPermission, Long> {
	
	public List<AuthPermission> queryPermissionByRoleId(long rid);
	
	/**
	 * 通过资源id和资源类型获取权限数据
	 * @param resourceId
	 * @return
	 */
	public AuthPermission findByResourceId(@Param("resourceId")long resourceId, @Param("permissionType")String permissionType);
	
	/**
	 * 通过资源id和资源类型删除权限数据
	 * @param resourceId
	 * @return
	 */
	public void deleteByResourceId(@Param("resourceId")long resourceId, @Param("permissionType")String permissionType);
	
	/**
	 * 通过资源id和资源类型删除权限数据
	 * @param resourceId
	 * @return
	 */
	public void deleteByParentId(@Param("parentId")long parentId, @Param("permissionType")String permissionType);
	
	/**
	 * 获取所有权限，区分角色是否具有该角色
	 * @param roleId
	 */
	List<PermissionDto> getAllPermissionsByRole(long roleId);
	
}
