package com.springlab.biz.controller;

public class ViewResolver {

	private String prefix;
	private String suffix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) {
		return new StringBuilder().append(prefix)
					.append(viewName)
					.append(suffix)
					.toString();
	}
}
