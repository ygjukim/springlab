package com.springlab.jdbctest.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.springlab.jdbctest.dao.SingerDao;

public class JdbcSingerDaoDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:config/embedded-h2-cfg.xml");
        ctx.refresh();

        SingerDao singerDao = ctx.getBean(SingerDao.class);

        System.out.println("Singer name for singer id 1 is: " +
                singerDao.findNameById(1l));

        ctx.close();
    }
}
