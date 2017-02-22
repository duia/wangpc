package com.wpc.admin.entity;

import java.io.Serializable;

/**
*  实体类
* author wpc
*/
public class AuthFile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private String fileName;
	/**
	 * 
	 */
	private String filePath;
	
	public void setId(Integer id){
		this.id=id;
	}
	public Integer getId(){
		return this.id;
	}
	
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
		sb.append("id=");
		sb.append(id);
		sb.append(",fileName=");
		sb.append(fileName);
		sb.append(",filePath=");
		sb.append(filePath);
		sb.append("]");
		return sb.toString();
	}
}
