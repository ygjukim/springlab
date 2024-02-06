package com.springlab.biz.controller2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetBoardListController implements Controller {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 목록 처리");
		
		List<BoardDO> boardList = boardDAO.getBoardList(null);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("board_list", boardList);
		mv.setViewName("getBoardList");
		
		return mv;
	}

}
