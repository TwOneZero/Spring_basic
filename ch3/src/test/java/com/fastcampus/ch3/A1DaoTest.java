package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class A1DaoTest {

    @Autowired
    DataSource ds;

    @Autowired
    DataSourceTransactionManager tm;


    @Autowired
    A1Dao a1Dao;

    @Test
    public void insertTest(){
        //Tx manager 생성 -> xml 태그로 추가하면 아래 코드 필요없음
//        PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
        TransactionStatus status =  tm.getTransaction(new DefaultTransactionDefinition());
        try {
            a1Dao.deleteAll();
            a1Dao.insert(1, 100);
            a1Dao.insert(2, 200);
            tm.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            tm.rollback(status);
        }
    }
}