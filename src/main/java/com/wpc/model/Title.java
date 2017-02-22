package com.wpc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Title {

	private String title;
	private String answer;
	private int type;
	private int[] points;
	private List<Option> options = new ArrayList<Option>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int[] getPoints() {
		return points;
	}
	public void setPoints(int[] points) {
		this.points = points;
	}
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	@Override
	public String toString() {
		return "Title [title=" + title + ", answer=" + answer + ", type=" + type + ", points=" + Arrays.toString(points)
						+ ", options=" + options + "]";
	}
	
}
