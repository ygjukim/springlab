package com.springlab.biz.controller;

import java.io.IOException;
import java.util.List;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.dao.BoardDAObyJDBC;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.user.dao.UserDAO;
import com.springlab.biz.user.dao.UserDAObyJDBC;
import com.springlab.biz.user.domain.UserDO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DispatcherServlet
 */
//@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HandlerMapping handlerMapping = null;
	private ViewResolver viewResolver = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("jsp/");
		viewResolver.setSuffix(".jsp");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("Request Path: " + path);
		
		Controller controller = handlerMapping.getController(path);
		
		if (controller != null) {
			String viewName = controller.handleRequest(request, response);
			
			if (viewName.contains("redirect:")) {
				String redirectPath = viewName.substring(viewName.lastIndexOf(":")+1).trim();
				response.sendRedirect(redirectPath);
			}
			else {
				viewName = viewResolver.getView(viewName.trim());
				
				RequestDispatcher view = request.getRequestDispatcher(viewName);
				view.forward(request, response);
			}
		}

		/*
		
		if (path.equals("/login.do")) {
			System.out.println(">>> 로그인 처리");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDO user = new UserDO();
			user.setId(id);
			
			UserDAO dao = new UserDAObyJDBC();
			user = dao.getUser(user);
			
			if (user != null && password.equals(user.getPassword())) {
				session.setAttribute("user", user);
				response.sendRedirect("getBoardList.do");
			}
			else {
				response.sendRedirect("jsp/login.jsp");
			}			
		}
		else if (path.equals("/logout.do")) {
			System.out.println(">>> 로그아웃 처리");
			
			// step #1. get request parameters

			// step #2. data processing - DB 연동 처리
			// close session
			session.invalidate();
			
			// step #3. output processing result
			response.sendRedirect("jsp/login.jsp");
		}
		else if (path.equals("/insertBoard.do")) {
			System.out.println(">>> 게시글 등록 처리");
			
			if (request.getMethod().equalsIgnoreCase("GET")) {
				RequestDispatcher view = request.getRequestDispatcher("jsp/insertBoard.jsp");
				view.forward(request, response);
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
				
				BoardDAO dao = new BoardDAObyJDBC();
				dao.insertBoard(board);
				
				// step #3. output processing result
				response.sendRedirect("getBoardList.do");				
			}
		}
		else if (path.equals("/updateBoard.do")) {
			System.out.println(">>> 게시글 수정 처리");
			
			// step #1. get request parameters
			int seq = Integer.parseInt(request.getParameter("seq"));
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			// step #2. data processing - DB 연동 처리
			BoardDO board = new BoardDO();
			board.setSeq(seq);
			
			BoardDAO dao = new BoardDAObyJDBC();
			board = dao.getBoard(board);
			
			if (!board.getTitle().equals(title) ||
				!board.getContent().equals(content) || 
				(board.getCnt() != cnt)) {
				board.setTitle(title);
				board.setContent(content);
				board.setCnt(cnt);
				
				dao.updateBoard(board);
			}
			
			// step #3. output processing result
			response.sendRedirect("getBoardList.do");		}
		else if (path.equals("/deleteBoard.do")) {
			System.out.println(">>> 게시글 삭제 처리");

			// step #1. get request parameters
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			// step #2. data processing - DB 연동 처리
			BoardDO board = new BoardDO();
			board.setSeq(seq);
			
			BoardDAO dao = new BoardDAObyJDBC();
			dao.deleteBoard(board);
			
			// step #3. output processing result
			response.sendRedirect("getBoardList.do");
		}
		else if (path.equals("/getBoard.do")) {
			System.out.println(">>> 게시글 조회 처리");

			int seq = Integer.parseInt(request.getParameter("seq"));
		
			BoardDO board = new BoardDO();
			board.setSeq(seq);
			
			BoardDAO dao = new BoardDAObyJDBC();
			board = dao.getBoard(board);
			
			if (board != null) {
				board.setCnt(board.getCnt()+1);
				dao.updateBoard(board);
	
				request.setAttribute("board", board);
				
				RequestDispatcher view = request.getRequestDispatcher("jsp/getBoard.jsp");
				view.forward(request, response);
			}
			else {
				response.sendRedirect("getBoardList.do");
			}
		}
		else if (path.equals("/getBoardList.do")) {
			System.out.println(">>> 게시글 목록 처리");
			
			BoardDAO dao = new BoardDAObyJDBC();
			List<BoardDO> boardList = dao.getBoardList(null);
			
			request.setAttribute("board_list", boardList);
			
			RequestDispatcher view = request.getRequestDispatcher("jsp/getBoardList.jsp");
			view.forward(request, response);
		}
		*/
	}

}
