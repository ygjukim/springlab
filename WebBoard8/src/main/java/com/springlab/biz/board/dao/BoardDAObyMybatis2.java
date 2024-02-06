package com.springlab.biz.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;

@Repository("boardDAO")
@Slf4j
public class BoardDAObyMybatis2 implements BoardDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	@Override
	public void insertBoard(BoardDO board) {
		log.info("process insertBoard()");

		mybatis.insert("BoardMapper.insertBoard", board);
	}

	@Override
	public void updateBoard(BoardDO board) {
		log.info("process updateBoard()");

		mybatis.update("BoardMapper.updateBoard", board);
	}

	@Override
	public void deleteBoard(BoardDO board) {
		log.info("process deleteBoard()");

		mybatis.delete("BoardMapper.deleteBoard", board);
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		log.info("process getBoard()");

		return (BoardDO)mybatis.selectOne("BoardMapper.getBoard", board);
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		log.info("process getBoardList()");

		return mybatis.selectList("BoardMapper.getBoardList", board);
	}

}
