/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wpc.common.base.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wpc.util.base.ObjectUtils;
import com.wpc.util.base.Reflections;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据Entity类
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class TreeEntity<T> extends DataEntity<T> {

	private static final long serialVersionUID = 1L;

	protected Long parentId;	// 父级编号
	protected String parentIds; // 所有父级编号

	protected T parent;	// 父级

	public TreeEntity() {
		super();
		this.sort = 30;
	}
	
	public TreeEntity(Long id) {
		super(id);
	}
	
	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * @return
	 */
	@JsonBackReference
	public T getParent() {
		return parent;
	}

	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * @return
	 */
	public void setParent(T parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Long getParentId() {
		Long id = null;
		if (parent != null){
			id = (Long) Reflections.getFieldValue(parent, "id");
		}
		return ObjectUtils.isNotEmpty(id) ? id : 0L;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
