<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.springlab.biz.user.dao.*, com.springlab.biz.user.domain.*" %>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	UserDO user = new UserDO();
	user.setId(id);
	user.setPassword(password);
	
	UserDAO dao = new UserDAObyJDBC();
	user = dao.getUser(user);
	
	if (user != null && password.equals(user.getPassword())) {
		session.setAttribute("user", user);
		response.sendRedirect("getBoardList.jsp");
	}
	else {
		response.sendRedirect("login.jsp");
	}
%>