package com.wpc.model;

public class Option {

	private int sortNum;
	private String content;
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Option [sortNum=" + sortNum + ", content=" + content + "]";
	}
	
}
