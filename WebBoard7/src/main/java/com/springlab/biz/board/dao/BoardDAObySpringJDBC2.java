package com.springlab.biz.board.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springlab.biz.board.domain.BoardDO;

import lombok.extern.slf4j.Slf4j;

@Repository("boardDAO")
@Slf4j
public class BoardDAObySpringJDBC2 implements BoardDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String BOARD_INSERT = "insert into BOARD(SEQ, TITLE, WRITER, CONTENT, UPLOADFILES) "
			+ "values((select nvl(max(SEQ), 0)+1 from BOARD), ?, ?, ?, ?)";
	private final String BOARD_UPDATE = "update BOARD set TITLE=?, CONTENT=?, CNT=? where SEQ=?";
	private final String BOARD_DELETE = "delete BOARD where SEQ=?";
	private final String BOARD_GET = "select * from BOARD where SEQ=?";
	private final String BOARD_LIST_F = "select * from BOARD where %s like '%%'||?||'%%' order by SEQ desc";

//	private Logger log;
//	
//	public BoardDAObySpringJDBC2() {
//		logger = LoggerFactory.getLogger(this.getClass());
//	}
	
	@Override
	public void insertBoard(BoardDO board) {
		log.info("process insertBoard()");
		
		jdbcTemplate.update(BOARD_INSERT, 
				board.getTitle(), board.getWriter(), board.getContent(), board.getUploadFiles());
	}

	@Override
	public void updateBoard(BoardDO board) {
		log.info("process updateBoard()");
		
		jdbcTemplate.update(BOARD_UPDATE, 
				board.getTitle(), board.getContent(), board.getCnt(), board.getSeq());
	}

	@Override
	public void deleteBoard(BoardDO board) {
		log.info("process deleteBoard()");
		
		jdbcTemplate.update(BOARD_DELETE, board.getSeq());
	}

	@Override
	public BoardDO getBoard(BoardDO board) {
		log.info("process getBoard()");
		
		return jdbcTemplate.queryForObject(
				BOARD_GET, new BoardRowMapper(), board.getSeq() );
	}

	@Override
	public List<BoardDO> getBoardList(BoardDO board) {
		log.info("process getBoardList()");
		
		String condition = board.getSearchCondition();
		if (condition == null)  condition = "TITLE";
		String sql = String.format(BOARD_LIST_F, condition);
		
		String keyword = board.getSearchKeyword();
		if (keyword == null)  keyword = "";
			
		return jdbcTemplate.query(sql, new BoardRowMapper(), keyword);
	}

}
