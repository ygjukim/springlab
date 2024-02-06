package com.springlab.biz.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;

//@Repository("boardDAO")
@Slf4j
public class BoardDAObyMybatis extends SqlSessionDaoSupport implements BoardDAO {

//	private SqlSession mybatis;
//	
//	public BoardDAObyMybatis() {
//		mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
//	}
	
	@Autowired
	public void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void insertBoard(BoardDO board) {
		log.info("process insertBoard()");

//		mybatis.insert("BoardMapper.insertBoard", board);
//		mybatis.commit();
		getSqlSession().insert("BoardMapper.insertBoard", board);
	}

	@Override
	public void updateBoard(BoardDO board) {
		log.info("process updateBoard()");

//		mybatis.update("BoardMapper.updateBoard", board);
//		mybatis.commit();
		getSqlSession().update("BoardMapper.updateBoard", board);
	}

	@Override
	public void deleteBoard(BoardDO board) {
		log.info("process deleteBoard()");

//		mybatis.delete("BoardMapper.deleteBoard", board);
//		mybatis.commit();
		getSqlSession().delete("BoardMapper.deleteBoard", board);
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		log.info("process getBoard()");

//		return (BoardDO)mybatis.selectOne("BoardMapper.getBoard", board);
		return (BoardDO)getSqlSession().selectOne("BoardMapper.getBoard", board);
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		log.info("process getBoardList()");

//		return mybatis.selectList("BoardMapper.getBoardList", board);
		return getSqlSession().selectList("BoardMapper.getBoardList", board);
	}

}
