package com.springlab.biz.controller3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.domain.UserDO;

import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BoardDAO boardDAO;

	@GetMapping("/login")
//	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginView() {
		System.out.println(">>> 로그인 처리");
		
		return "login";
	}
	
	@PostMapping("/login")
//	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserDO user, HttpSession session) {
		System.out.println(">>> 로그인 처리");
			
		UserDO registeredUser = userDAO.getUser(user);
		
		if (registeredUser != null && registeredUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("user", registeredUser);
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
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println(">>> 로그아웃 처리");
		
		session.invalidate();
		
		return "redirect:login";	
	}
	
	@GetMapping("getBoardList")
	public String getBoardList(Model model) {
		System.out.println(">>> 게시글 목록 처리");
		
		List<BoardDO> boardList = boardDAO.getBoardList(null);
		model.addAttribute("board_list", boardList);
		
		return "getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(BoardDO board, Model model) {
		System.out.println(">>> 게시글 조회 처리");

		board = boardDAO.getBoard(board);
		
		if (board != null) {
			board.setCnt(board.getCnt()+1);
			boardDAO.updateBoard(board);

			model.addAttribute("board", board);

			return "getBoard";
		}
		else {
			return "redirect:getBoardList";
		}
	}

	@GetMapping("/insertBoard")
	public String insertBoardView() {
		System.out.println(">>> 게시글 입력 처리");

		return "insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoard(BoardDO board) {
		System.out.println(">>> 게시글 등록 처리");

		boardDAO.insertBoard(board);
		
		return "redirect:getBoardList";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(BoardDO board) {
		System.out.println(">>> 게시글 수정 처리");
		
		BoardDO regBoard = boardDAO.getBoard(board);
		
		if (!regBoard.getTitle().equals(board.getTitle()) ||
			!regBoard.getContent().equals(board.getContent()) || 
			(regBoard.getCnt() != board.getCnt())) {
			boardDAO.updateBoard(board);
		}
		
		return "redirect:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(BoardDO board) {
		System.out.println(">>> 게시글 삭제 처리");

		boardDAO.deleteBoard(board);
		
		return "redirect:getBoardList";
	}

}
