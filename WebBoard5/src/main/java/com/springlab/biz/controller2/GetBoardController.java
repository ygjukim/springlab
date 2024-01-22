package com.springlab.biz.controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBoardController implements Controller {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 조회 처리");

		int seq = Integer.parseInt(request.getParameter("seq"));
	
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		board = boardDAO.getBoard(board);
		
		ModelAndView mv = new ModelAndView();
		
		if (board != null) {
			board.setCnt(board.getCnt()+1);
			boardDAO.updateBoard(board);

			mv.addObject("board", board);
			mv.setViewName("getBoard");
		}
		else {
			mv.setViewName("redirect:getBoardList");
		}
		
		return mv;
	}

}
