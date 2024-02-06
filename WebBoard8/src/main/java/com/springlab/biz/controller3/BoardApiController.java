package com.springlab.biz.controller3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

//@Controller
@RestController
@RequestMapping("/api/")
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;

	@GetMapping("getBoard")
	//@ResponseBody
//	public String getBoard(BoardDO board) throws JsonProcessingException {
//		System.out.println(">>> 게시글 조회 API 처리");
//
//		board = boardService.getBoard(board);
//		
//		String jsonStr = null;		
//		if (board != null) {
//			jsonStr = (new ObjectMapper()).writeValueAsString(board);
//		}
//		
//		return jsonStr;
//	}
//	public BoardDO getBoard(BoardDO board) throws JsonProcessingException {
//		System.out.println(">>> 게시글 조회 API 처리");
//
//		board = boardService.getBoard(board);
//		
//		return board;
//	}
	public ResponseEntity<BoardDO> getBoard(BoardDO board) throws JsonProcessingException {
		System.out.println(">>> 게시글 조회 API 처리");

		board = boardService.getBoard(board);
		if (board == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(board);
	}
	
	@RequestMapping("getBoardList")
	//@ResponseBody
	public ResponseEntity<Map<String, Object>> getBoardList(BoardDO board) {
		System.out.println(">>> 게시글 목록 API 처리");
		
		List<BoardDO> boardList = boardService.getBoardList(board);
		if (boardList == null || boardList.size() == 0) {
			return ResponseEntity.notFound().build();
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", boardList);
		result.put("count", boardList.size());
		
		return ResponseEntity.ok(result);
	}
	
}
