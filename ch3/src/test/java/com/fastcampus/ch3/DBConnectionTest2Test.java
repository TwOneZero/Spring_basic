package com.fastcampus.ch3;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {

    @Autowired
    DataSource ds; // JUnit 은 인스턴스 변수를 공유하지 않고 각 객체를 생성하여 실행한다.

    @Test
    public void insertUserTest() throws Exception {
        User user = new User("test2", "1234", "test2", "test2@test.com", new Date(), "fb", new Date());
        deleteAll();
        int rowCnt = insertUser(user);

        Assert.assertTrue(rowCnt == 1);

    }

    @Test
    public void selectUserTest() throws Exception {
        deleteAll();
        insertUser(new User("test1", "1234", "test1", "test1@test.com", new Date(), "fb", new Date()));

        User user = selectUser("test1");
        Assert.assertEquals("test1", user.getId());
    }

    @Test
    public void deleteUserTest() throws  Exception {
        deleteAll();
        int rowCnt = deleteUser("test1");
        Assert.assertTrue(rowCnt == 0);
        User user = new User("test1", "1234", "test1", "test1@test.com", new Date(), "fb", new Date());
        rowCnt = insertUser(user);
        Assert.assertTrue(rowCnt == 1);

        rowCnt = deleteUser(user.getId());
        Assert.assertTrue(rowCnt == 1);

        Assert.assertTrue(selectUser(user.getId()) == null);

    }

    public int deleteUser(String id) throws  Exception {
        Connection conn = ds.getConnection();
        String sql = "delete from user_info where id = ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);

        return pstmt.executeUpdate();
    }

    public User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();
        String sql = "select * from user_info where id= ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(new Date(rs.getTimestamp(7).getTime()));

            return user;
        }
        return null;
    }

    private void deleteAll() throws Exception {
        Connection conn = ds.getConnection();


        String sql = "delete from user_info ";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.executeUpdate();
    }

    @Test
    public void  transactionTest() throws Exception {
        Connection conn =null;
        try {
            deleteAll();
            conn = ds.getConnection();
            conn.setAutoCommit(false); // autoCommit 끄기

            String sql = "insert into user_info values (?,?,?,?,?,?, now())";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"asdf");
            pstmt.setString(2, "1234");
            pstmt.setString(3, "asdf");
            pstmt.setString(4, "asdf@test.com");
            pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
            pstmt.setString(6, "fb");

            pstmt.executeUpdate(); //insert 실행

            //id 변경
            pstmt.setString(1, "asdf");
            pstmt.executeUpdate(); //insert 실행

            conn.commit(); // 커밋하기

        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {

        }


    }


    //    사용자 정보를 user 테이블에 저장
    public int insertUser(User user) throws Exception {
        Connection conn = ds.getConnection();

//
//        insert into user_info (id, pwd, name, email, birth, sns, reg_date)
//        values ('test1', '1234', 'test1', 'test1@test.com', '2021-01-01', 'fb', now());

        String sql = "insert into user_info values (?,?,?,?,?,?, now())";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        return pstmt.executeUpdate();
    }

    @Test
    public void main() throws Exception {
//        위 autowired 와 class 에 ContextConfiguration 어노테이션을 통해 bean 자동으로 주입
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.


        System.out.println("conn = " + conn);
        //assertTrue(conn != null); <- 아래와 같음
        Assert.assertNotNull(conn);

    }
}