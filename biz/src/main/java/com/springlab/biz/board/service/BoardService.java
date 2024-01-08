package com.springlab.biz.board.service;

import java.util.List;

import com.springlab.biz.board.domain.BoardDO;

public interface BoardService {

	public void insertBoard(BoardDO board);
	public void updateBoard(BoardDO board);
	public void deleteBoard(BoardDO board);
	public BoardDO getBoard(BoardDO board);
	public List<BoardDO> getBoardList(BoardDO board);
	
}
