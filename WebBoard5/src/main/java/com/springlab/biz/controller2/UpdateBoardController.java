package com.springlab.biz.controller2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateBoardController implements Controller {

	@Autowired
	private BoardDAO baordDAO;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(">>> 게시글 수정 처리");
		
		// step #1. get request parameters
		int seq = Integer.parseInt(request.getParameter("seq"));
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// step #2. data processing - DB 연동 처리
		BoardDO board = new BoardDO();
		board.setSeq(seq);
		
		board = baordDAO.getBoard(board);
		
		if (!board.getTitle().equals(title) ||
			!board.getContent().equals(content) || 
			(board.getCnt() != cnt)) {
			board.setTitle(title);
			board.setContent(content);
			board.setCnt(cnt);
			
			baordDAO.updateBoard(board);
		}
		
		// step #3. output processing result
		return new ModelAndView("redirect:getBoardList");
	}

}
