package com.springlab.biz.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

@Component("boardServiceImpl")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	@Qualifier("boardDAO")
	private BoardDAO dao;

	@Override
	public void insertBoard(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process insertBoard()");
		
//		long start = System.currentTimeMillis();
		
		dao.insertBoard(board);
		
//		long end = System.currentTimeMillis();
//		String message = (end - start) + " msec 경과";
//		System.out.println("insertBoard() method: " + message);
	}

	@Override
	public void updateBoard(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process updateBoard()");
		dao.updateBoard(board);
	}

	@Override
	public void deleteBoard(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process deleteBoard()");
		if (board == null) throw new IllegalArgumentException("잘못된 매개변수입니다.");
		dao.deleteBoard(board);
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process getBoard()");
		return dao.getBoard(board);
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		System.out.println(">>> BoardServiceImpl : process getBoardList()");
		return dao.getBoardList(board);
	}

}
