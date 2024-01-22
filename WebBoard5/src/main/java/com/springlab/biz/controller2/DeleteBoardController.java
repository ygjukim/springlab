package com.springlab.biz.controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteBoardController implements Controller {

	@Autowired
	private BoardDAO baordDAO;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 삭제 처리");

		// step #1. get request parameters
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		// step #2. data processing - DB 연동 처리
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		baordDAO.deleteBoard(board);
		
		// step #3. output processing result
		return new ModelAndView("redirect:getBoardList");
	}

}
