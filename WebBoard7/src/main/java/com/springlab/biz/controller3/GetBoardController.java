package com.springlab.biz.controller3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class GetBoardController {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@RequestMapping("/getBoard")
	public ModelAndView getBoard(HttpServletRequest request, HttpServletResponse bresponse) {
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
