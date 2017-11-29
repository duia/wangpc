package com.wpc.common.base.dao;

import com.wpc.common.base.entity.TreeEntity;

import java.util.List;

/**
 * Created by 
 */
public interface TreeBaseDao<T extends TreeEntity<T>> extends BaseDao<T> {

    /**
     * 找到所有子节点
     * @param entity
     * @return
     */
    List<T> findByParentIdsLike(T entity);

    /**
     * 更新所有父节点字段
     * @param entity
     * @return
     */
    int updateParentIds(T entity);

}
