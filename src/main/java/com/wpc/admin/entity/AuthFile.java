package com.wpc.admin.entity;

import com.wpc.common.base.entity.BaseEntity;

/**
*  实体类
* author wpc
*/
public class AuthFile extends BaseEntity {

	/**
	 * 
	 */
	private String fileName;
	/**
	 * 
	 */
	private String filePath;
	
	public void setFileName(String fileName){
		this.fileName=fileName;
	}
	public String getFileName(){
		return this.fileName;
	}
	
	public void setFilePath(String filePath){
		this.filePath=filePath;
	}
	public String getFilePath(){
		return this.filePath;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("AuthFile[");
		sb.append(",fileName=");
		sb.append(fileName);
		sb.append(",filePath=");
		sb.append(filePath);
		sb.append("]");
		return sb.toString();
	}
}
