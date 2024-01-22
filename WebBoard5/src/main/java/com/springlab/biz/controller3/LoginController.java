package com.springlab.biz.controller3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.domain.UserDO;

import jakarta.servlet.http.HttpSession;

//@Controller
//@SessionAttributes("user")
public class LoginController {
	
	@Autowired
	private UserDAO userDAO;

	@GetMapping("/login")
//	@RequestMapping(value="/login", method=RequestMethod.GET)
//	public ModelAndView loginView() {
//		return new ModelAndView("login");
//	}
	public String loginView() {
		return "login";
	}
	
	@PostMapping("/login")
//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
//		
//		UserDO user = new UserDO();
//		user.setId(id);
//		
//		user = userDAO.getUser(user);
//		
//		HttpSession session = request.getSession();
//		
//		if (user != null && password.equals(user.getPassword())) {
//			session.setAttribute("user", user);
//			session.removeAttribute("message");
//			return "redirect:getBoardList";
//		}
//		else {
//			String message = null;
//			if (user == null)  message = "잘못된 사용자 아이디입니다!";
//			else if (!password.equals(user.getPassword()))
//				message = "비밀번호가 잘못 입력되었습니다!";
//			session.setAttribute("message", message);
//			return "redirect:login";
//		}		
//	}	
//	public String login(@RequestParam("id")String id, @RequestParam("password")String password, HttpSession session) {
//		UserDO user = new UserDO();
//		user.setId(id);
//		
//		user = userDAO.getUser(user);
//		
//		if (user != null && password.equals(user.getPassword())) {
//			session.setAttribute("user", user);
//			session.removeAttribute("message");
//			return "redirect:getBoardList";
//		}
//		else {
//			String message = null;
//			if (user == null)  message = "잘못된 사용자 아이디입니다!";
//			else if (!password.equals(user.getPassword()))
//				message = "비밀번호가 잘못 입력되었습니다!";
//			session.setAttribute("message", message);
//			return "redirect:login";
//		}		
//	}	
	public String login(UserDO user, HttpSession session) {
	
		UserDO registeredUser = userDAO.getUser(user);
		
		if (registeredUser != null && registeredUser.getPassword().equals(user.getPassword())) {
//			session.setAttribute("user", registeredUser);
			session.removeAttribute("message");
			return "redirect:getBoardList";
		}
		else {
			String message = null;
			if (registeredUser == null)  message = "잘못된 사용자 아이디입니다!";
			else if (!registeredUser.getPassword().equals(user.getPassword()))
				message = "비밀번호가 잘못 입력되었습니다!";
			session.setAttribute("message", message);
			return "redirect:login";
		}		
	}	
	
}
