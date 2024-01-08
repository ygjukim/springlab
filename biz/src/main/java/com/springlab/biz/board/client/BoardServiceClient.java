package com.springlab.biz.board.client;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;

public class BoardServiceClient {

	public static void main(String[] args) {

		ApplicationContext context =
			new ClassPathXmlApplicationContext("classpath:appConfig.xml");
		
		BoardService boardService = (BoardService)context.getBean("boardService");
		
		BoardDO board = new BoardDO();
		board.setTitle("임시 게시물");
		board.setWriter("홍길동");
		board.setContent("임시 게시물입니다...");
		boardService.insertBoard(board);
		
		board.setSeq(1);
		board = boardService.getBoard(board);
		board.setCnt(board.getCnt()+1);
		boardService.updateBoard(board);
		
		board.setSeq(board.getCnt());
		boardService.deleteBoard(board);
		
		List<BoardDO> boardList = boardService.getBoardList(null);
		System.out.println("--- 등록된 게시글 ---");
		for (BoardDO bd : boardList) {
			System.out.println(bd.toString());
		}
		
	}

}
