package com.springlab.biz.controller3;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.springlab.biz.controller3")
public class CommonExceptionHandler {

	@ExceptionHandler(ArithmeticException.class)
	public String handleArithmeticExcpetion(Exception ex, Model model) {
		
		model.addAttribute("exception", ex);
		
		return "error/arithmeticError";
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(Exception ex, Model model) {
		
		model.addAttribute("exception", ex);
		
		return "error/nullPointerError";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgumentException(Exception ex, Model model) {
		
		model.addAttribute("exception", ex);
		
		return "error/illegalArgumentError";
		
	}

	@ExceptionHandler(Exception.class)
	public String handleExcpetion(Exception ex, Model model) {
		
		model.addAttribute("exception", ex);
		
		return "error/error";
	}

}
