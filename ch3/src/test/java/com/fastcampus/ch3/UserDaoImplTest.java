package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Test
    public void deleteUser() {
    }

    @Test
    public void selectUser() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void updateUser() {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2023, 11, 14);

        userDao.deleteAll();
        User user = new User("test3", "1234", "test3", "test3@test.com", new Date(cal.getTimeInMillis()), "fb", new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt == 1);

        user.setPwd("4321");
        user.setSns("ig");
        rowCnt = userDao.updateUser(user);
        assertTrue(rowCnt == 1);
        User user2 = userDao.selectUser(user.getId());
        assertTrue(user.equals(user2));

        System.out.println("user = " + user);
        System.out.println("user2 = " + user2);

    }

    @Test
    public void deleteAll() {
    }
}