package com.springlab.biz.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {

	String handleRequest(HttpServletRequest request, HttpServletResponse response);
	
}
