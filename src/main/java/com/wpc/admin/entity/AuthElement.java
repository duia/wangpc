package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

import java.io.Serializable;

/**
*  实体类
* author wpc
*/
public class AuthElement extends BaseEntity {

	/**
	 * 所属菜单ID
	 */
	private Long menuId;
	/**
	 * 
	 */
	private String elementName;
	/**
	 * 
	 */
	private String elementCode;
	/**
	 * 
	 */
	private String elementDesc;
	
	public void setMenuId(Long menuId){
		this.menuId=menuId;
	}
	public Long getMenuId(){
		return this.menuId;
	}
	
	public void setElementName(String elementName){
		this.elementName=elementName;
	}
	public String getElementName(){
		return this.elementName;
	}
	
	public void setElementCode(String elementCode){
		this.elementCode=elementCode;
	}
	public String getElementCode(){
		return this.elementCode;
	}
	
	public void setElementDesc(String elementDesc){
		this.elementDesc=elementDesc;
	}
	public String getElementDesc(){
		return this.elementDesc;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("AuthElement[");
		sb.append(",menuId=");
		sb.append(menuId);
		sb.append(",elementName=");
		sb.append(elementName);
		sb.append(",elementCode=");
		sb.append(elementCode);
		sb.append(",elementDesc=");
		sb.append(elementDesc);
		sb.append("]");
		return sb.toString();
	}
}
