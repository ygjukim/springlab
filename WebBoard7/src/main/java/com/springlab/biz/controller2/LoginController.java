package com.springlab.biz.controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.domain.UserDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Controller {

	@Autowired
	private UserDAO userDAO;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 로그인 처리");
				
		ModelAndView mv = new ModelAndView();

		if (request.getMethod().equalsIgnoreCase("GET")) {
			mv.setViewName("login");
		}
		else {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDO user = new UserDO();
			user.setId(id);
			
			user = userDAO.getUser(user);
			
			HttpSession session = request.getSession();
			
			if (user != null && password.equals(user.getPassword())) {
				session.setAttribute("user", user);
				session.removeAttribute("message");
				mv.setViewName("redirect:getBoardList");
			}
			else {
				String message = null;
				if (user == null)  message = "잘못된 사용자 아이디입니다!";
				else if (!password.equals(user.getPassword()))
					message = "비밀번호가 잘못 입력되었습니다!";
				session.setAttribute("message", message);
				mv.setViewName("redirect:login");
			}
		}
		
		return mv;
	}

}
