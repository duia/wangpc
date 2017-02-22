package com.wpc.admin.entity;

import java.io.Serializable;

/**
*  实体类
* author wpc
*/
public class AuthElement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 所属菜单ID
	 */
	private Integer menuId;
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
	
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	
	public void setMenuId(Integer menuId){
		this.menuId=menuId;
	}
	public Integer getMenuId(){
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
		sb.append("id=");
		sb.append(id);
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
