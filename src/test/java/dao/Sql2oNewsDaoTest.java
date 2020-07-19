package dao;

import models.Department;
import models.Department_News;
import models.News;
import models.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oNewsDaoTest {

    private static Sql2oDepartmentDao sql2oDepartmentDao;
    private static Sql2oUsersDao sql2oUsersDao;
    private static Sql2oNewsDao sql2oNewsDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://localhost:5432/organization_test";
        Sql2o sql2o = new Sql2o(connectionString, "tonui", "chepkemoi1999.");
        sql2oUsersDao = new Sql2oUsersDao(sql2o);
        sql2oDepartmentDao =new  Sql2oDepartmentDao(sql2o);
        sql2oNewsDao =new Sql2oNewsDao(sql2o);
        conn = sql2o.open();
    }


    @After
    public void tearDown() throws Exception {
        sql2oDepartmentDao.clearAll();
        sql2oUsersDao.clearAll();
        sql2oNewsDao.clearAll();
        conn.close();
    }


    @Test
    public void addNews() {
        User user=setUpNewUser();
        sql2oUsersDao.save(user);
        Department departments=setUpNewDepartment();
        sql2oDepartmentDao.add(departments);
        News news=new News("Activity","Go for team building",user.getId());
        sql2oNewsDao.addNews(news);

        assertEquals(user.getId(),sql2oNewsDao.findById(news.getId()).getUser_id());
        assertEquals(news.getDepartment_id(),sql2oNewsDao.findById(news.getId()).getDepartment_id());
    }




    @Test
    public void addDepartmentNews() {
        User user=setUpNewUser();
        sql2oUsersDao.save(user);
        Department departments=setUpNewDepartment();
        sql2oDepartmentDao.add(departments);
        Department_News department_news =new Department_News("Announcement","To elect new Representative",departments.getId(),user.getId());
        sql2oNewsDao.addDepartmentNews(department_news);
        assertEquals(user.getId(),sql2oNewsDao.findById(department_news.getId()).getUser_id());
        assertEquals(department_news.getDepartment_id(),sql2oNewsDao.findById(department_news.getId()).getDepartment_id());

    }




    @Test
    public void getAll() {
        User user=setUpNewUser();
        sql2oUsersDao.save(user);
        Department departments=setUpNewDepartment();
        sql2oDepartmentDao.add(departments);
        Department_News department_news =new Department_News("Announcement","To elect new Representative",departments.getId(),user.getId());
        sql2oNewsDao.addDepartmentNews(department_news);
        News news=new News("Activity","Go for team building",user.getId());
        sql2oNewsDao.addNews(news);
        assertTrue(sql2oNewsDao.getAll().contains(news));
    }



    @Test
    public void findById() {
        User user=setUpNewUser();
        sql2oUsersDao.save(user);
        Department departments=setUpNewDepartment();
        sql2oDepartmentDao.add(departments);
        News news=new News("Activity","Go for team building",user.getId());
        sql2oNewsDao.addNews(news);
        assertEquals(news,sql2oNewsDao.findById(news.getId()));
    }

    //helper
//    private News setUpNewNews() {
//        return new News("Meeting","Meeting to set activities for team building");
//    }
    private Department setUpNewDepartment(){
        return new Department("Finance","Handles Finances");
    }
    private User setUpNewUser(){
        return  new User("Linda","linda@linda.com","Manager","Manage Office activities");
    }

}