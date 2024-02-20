package com.springlab.jdbctest.main;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springlab.jdbctest.dao.PlainSingerDao;
import com.springlab.jdbctest.dao.SingerDao;
import com.springlab.jdbctest.entity.Singer;

public class PlainJdbcDemo {
    private static SingerDao singerDao = new PlainSingerDao();
    private static Logger logger = LoggerFactory.getLogger(PlainJdbcDemo.class);

    public static void main(String... args) {
        logger.info("초기 가수 데이터 목록:");

        listAllSingers();

        logger.info("-------------");
        logger.info("가수 등록");

        Singer singer = new Singer();
        singer.setFirstName("Ed");
        singer.setLastName("Sheeran");
        singer.setBirthDate(new Date((new GregorianCalendar(1991, 2, 1991)).getTime().getTime()));
        singerDao.insert(singer);

        logger.info("신규 가수를 등록한 뒤 가수 데이터 목록:");

        listAllSingers();

        logger.info("-------------");
        logger.info("앞에서 생성한 가수 삭제");

        singerDao.delete(singer.getId());

        logger.info("신규 가수 삭제 뒤 가수 데이터 목록:");

        listAllSingers();
    }

    private static void listAllSingers() {
        List<Singer> singers = singerDao.findAll();

        for (Singer singer: singers) {
            logger.info(singer.toString());
        }
    }
}
