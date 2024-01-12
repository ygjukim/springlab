package com.springlab.biz.board.client;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springlab.biz.board.domain.BoardDO;
import com.springlab.biz.board.service.BoardService;
import com.springlab.biz.config.AppConfig;

public class BoardServiceClient {

	public static void main(String[] args) {

		ApplicationContext context =
//			new ClassPathXmlApplicationContext("classpath:appConfig.xml");
			new AnnotationConfigApplicationContext(AppConfig.class);
		
		BoardService boardService = (BoardService)context.getBean("boardServiceImpl");
		
//		BoardService boardServiceImpl = (BoardService)context.getBean("boardServiceImpl");
//		BoardService boardService = (BoardService)Proxy.newProxyInstance(
//				BoardServiceImpl.class.getClassLoader(), 
//				new Class[] {BoardService.class}, 
//				new InvocationHandler() {
//
//					@Override
//					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//
//						long start = System.currentTimeMillis();
//						
//						Object result = method.invoke(boardServiceImpl, args);
//						
//						long end = System.currentTimeMillis();
//						String message = (end - start) + " msec 경과";
//						System.out.println(method.getName() + "() method: " + message);
//
//						return result;
//					}
//				});
		
		BoardDO board = new BoardDO();
		board.setSeq(1001);
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
//		boardService.deleteBoard(null);
		
		List<BoardDO> boardList = boardService.getBoardList(null);
		System.out.println("--- 등록된 게시글 ---");
		for (BoardDO bd : boardList) {
			System.out.println(bd.toString());
		}
		
	}

}
