package com.springlab.biz.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springlab.biz.board.dao.BoardDAO;
import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;

@Service("boardServiceImpl")
@Transactional(readOnly=true)
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	@Qualifier("boardDAO")
	private BoardDAO dao;

	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void insertBoard(BoardDO board) {
		log.info("process insertBoard()");
		
//		long start = System.currentTimeMillis();
		
		dao.insertBoard(board);
		
//		long end = System.currentTimeMillis();
//		String message = (end - start) + " msec 경과";
//		System.out.println("insertBoard() method: " + message);
	}

	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void updateBoard(BoardDO board) {
		log.info("process updateBoard()");
		dao.updateBoard(board);
	}

	@Transactional(readOnly=false, rollbackFor=Throwable.class)
	@Override
	public void deleteBoard(BoardDO board) {
		log.info("process deleteBoard()");
		if (board == null) throw new IllegalArgumentException("잘못된 매개변수입니다.");
		dao.deleteBoard(board);
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		log.info("process getBoard()");
		return dao.getBoard(board);
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		log.info("process getBoardList()");
		return dao.getBoardList(board);
	}

}
