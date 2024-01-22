package com.springlab.biz.controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InsertBoardController implements Controller {

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 등록 처리");
		
		ModelAndView mv = new ModelAndView();
		
		if (request.getMethod().equalsIgnoreCase("GET")) {
			mv.setViewName("insertBoard");
		}
		else {
			// step #1. get request parameters
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");	

			// step #2. data processing - DB 연동 처리
			BoardDO board = new BoardDO();
			board.setTitle(title);
			board.setWriter(writer);
			board.setContent(content);
			
			boardDAO.insertBoard(board);
			
			// step #3. output processing result
			mv.setViewName("redirect:getBoardList");				
		}
		
		return mv;
	}

}
