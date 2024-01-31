package com.springlab.biz.controller3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;
import com.springlab.biz.user.domain.UserDO;
import com.springlab.biz.user.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@Controller
public class BoardController {

	@Autowired
	private UserService userService;

	@Autowired
	private BoardService boardService;
	
	@Value("${board.uploadDir}")
	private String uploadDir;

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
		
		if (user.getId() == null || user.getId().isBlank()
			|| user.getPassword() == null || user.getPassword().isBlank()) {
			throw new IllegalArgumentException("아이디 또는 비밀번호는 반드시 입력하여야 합니다.");
		}
			
		UserDO registeredUser = userService.getUser(user);
		
		if (registeredUser != null && registeredUser.getPassword().equals(user.getPassword())) {
			session.setAttribute("user", registeredUser);
			session.setAttribute("uploadDir", uploadDir);
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
	
	@RequestMapping("getBoardList")
	public String getBoardList(BoardDO board, Model model) {
		System.out.println(">>> 게시글 목록 처리");
		
		List<BoardDO> boardList = boardService.getBoardList(board);
		model.addAttribute("board_list", boardList);
		
		Map<String, String> conditionMap = new LinkedHashMap<String, String>();
		conditionMap.put("TITLE", "제목");
		conditionMap.put("CONTENT", "내용");
		conditionMap.put("WRITER", "작성자");
		model.addAttribute("conditionMap", conditionMap);
		
		return "getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(BoardDO board, Model model) {
		System.out.println(">>> 게시글 조회 처리");

		board = boardService.getBoard(board);
		
		if (board != null) {
			board.setCnt(board.getCnt()+1);
			boardService.updateBoard(board);

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
	public String insertBoard(
			HttpServletRequest request,
			BoardDO board, 
			@RequestParam("uploadFile") MultipartFile[] parts) throws IOException, ServletException {
		System.out.println(">>> 게시글 등록 처리");

	 	// upload appended files
	 	String uploadPath = request.getServletContext().getRealPath(uploadDir);
	 	System.out.println(">>> " + uploadPath);
	 	File uploadPathFile = new File(uploadPath);
	 	if (!uploadPathFile.exists()) {
	 		uploadPathFile.mkdirs();
	 	}
	 	
	 	StringBuilder sb = new StringBuilder();
	 	for (MultipartFile p : parts) {
	 		if (!p.isEmpty()) {
		 		String fileName = p.getOriginalFilename();
			 	sb.append(fileName);
			 	sb.append(",");
			 	
			 	p.transferTo(new File(uploadPath + File.separator + fileName));
	 		}
	 	}
	 	
	 	int length = sb.length();
	 	if (length > 0) {
	 		sb.delete(length-1, length);
		 	board.setUploadFiles(sb.toString());
	 	}
	 	else {
		 	board.setUploadFiles(null);
	 	}
	 		 		 	
	 	// insert board entry to DB
		boardService.insertBoard(board);
		
		return "redirect:getBoardList";
	}
/*
	public String insertBoard(HttpServletRequest request) throws IOException, ServletException {
		System.out.println(">>> 게시글 등록 처리");

		// get request parameters
		BoardDO board = new BoardDO();
		board.setTitle(request.getParameter("title"));
	 	board.setContent(request.getParameter("content"));
	 	board.setWriter(request.getParameter("writer"));
	 	
	 	// upload appended files
	 	String uploadPath = request.getServletContext().getRealPath(uploadDir);
	 	System.out.println(">>> " + uploadPath);
	 	File uploadPathFile = new File(uploadPath);
	 	if (!uploadPathFile.exists()) {
	 		uploadPathFile.mkdirs();
	 	}
	 	
	 	Collection<Part> parts = request.getParts();
	 	StringBuilder sb = new StringBuilder();
	 	for (Part p : parts) {
	 		if (!p.getName().equals("uploadFile")) continue;
	 		if (p.getSize() <= 0) continue;
	 		
	 		String fileName = p.getSubmittedFileName();
		 	sb.append(fileName);
		 	sb.append(",");

		 	InputStream fis = p.getInputStream();	 	
		 	FileOutputStream fos = new FileOutputStream(
		 			uploadPath + File.separator + fileName);

		 	byte[] buf = new byte[1024];
		 	int size = 0;
		 	while((size = fis.read(buf)) != -1) {
		 		fos.write(buf, 0, size);
		 	}
		 	
		 	fis.close();
		 	fos.close();
	 	}
	 	
	 	int length = sb.length();
	 	if (length > 0) {
	 		sb.delete(length-1, length);
		 	board.setUploadFiles(sb.toString());
	 	}
	 	else {
		 	board.setUploadFiles(null);
	 	}
	 		 		 	
	 	// insert board entry to DB
		boardService.insertBoard(board);
		
		return "redirect:getBoardList";
	}
*/
	
	@PostMapping("/updateBoard")
	public String updateBoard(BoardDO board) {
		System.out.println(">>> 게시글 수정 처리");
		
		BoardDO regBoard = boardService.getBoard(board);
		
		if (!regBoard.getTitle().equals(board.getTitle()) ||
			!regBoard.getContent().equals(board.getContent()) || 
			(regBoard.getCnt() != board.getCnt())) {
			boardService.updateBoard(board);
		}
		
		return "redirect:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(BoardDO board) {
		System.out.println(">>> 게시글 삭제 처리");

		boardService.deleteBoard(board);
		
		return "redirect:getBoardList";
	}

}
