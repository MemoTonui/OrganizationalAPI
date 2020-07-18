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

public class Sql2oDepartmentDaoTest {
    private static Sql2oDepartmentDao sql2oDepartmentDao;
    private static Sql2oUsersDao sql2oUsersDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://localhost:5432/organization_test";
        Sql2o sql2o = new Sql2o(connectionString, "tonui", "chepkemoi1999.");

        //Sql2o sql2o = new Sql2o(connectionString, "", "");
        sql2oUsersDao = new Sql2oUsersDao(sql2o);
        sql2oDepartmentDao=new  Sql2oDepartmentDao(sql2o);
        try {
            conn = sql2o.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addDepartment_AddsDepartmentCorrectly_true() throws Exception {
        Department department = setUpNewDepartment();
        int deptId = department.getId();
        sql2oDepartmentDao.add(department);
        assertEquals(deptId,department.getId());
    }
    @Test
    public void idForAddedDepartmentIsSet() {
        Department department=setUpNewDepartment();
        int originalId=department.getId();
        sql2oDepartmentDao.add(department);
        assertEquals(originalId,department.getId());
    }



    @Test
    public void addUserToDepartment() {
        Department department=setUpNewDepartment();
        sql2oDepartmentDao.add(department);
        User user=setUpNewUser();
        User user2 = new User("Brian", "brian@bee.com", "CEO", "Head", "Management");
        sql2oUsersDao.save(user);
        sql2oUsersDao.save(user2);
        sql2oDepartmentDao.addUserToDepartment(user,department);
        sql2oDepartmentDao.addUserToDepartment(user2,department);
        assertEquals(2,sql2oDepartmentDao.getAllUsersInDepartment(department.getId()).size());
        assertEquals(2,sql2oDepartmentDao.findById(department.getId()).getSize());
    }

    @Test
    public void getAllDepartments() {
        Department department=setUpNewDepartment();
        sql2oDepartmentDao.add(department);
        Department otherDepartment =new Department("IT","Handles IT",24);
        sql2oDepartmentDao.add(otherDepartment);
        assertEquals(department,sql2oDepartmentDao.getAllDepartments().get(0));
        assertEquals(otherDepartment,sql2oDepartmentDao.getAllDepartments().get(1));
    }

    @Test
    public void correctDepartmentIsReturnedFindById() {
        Department department=setUpNewDepartment();
        sql2oDepartmentDao.add(department);
        Department otherDepartment =new Department("IT","Handles IT",24);
        sql2oDepartmentDao.add(otherDepartment);
        assertEquals(department,sql2oDepartmentDao.findById(department.getId()));
        assertEquals(otherDepartment,sql2oDepartmentDao.findById(otherDepartment.getId()));

    }

    @Test
    public void getAllUsersInDepartment() {
        Department department=setUpNewDepartment();
        sql2oDepartmentDao.add(department);
        User user=setUpNewUser();
        User user2 = new User("Brian", "brian@bee.com", "CEO", "Head", "Management");
        sql2oUsersDao.save(user);
        sql2oUsersDao.save(user2);
        sql2oDepartmentDao.addUserToDepartment(user,department);
        sql2oDepartmentDao.addUserToDepartment(user2,department);
        assertEquals(2,sql2oDepartmentDao.getAllUsersInDepartment(department.getId()).size());
        assertEquals(2,sql2oDepartmentDao.findById(department.getId()).getSize());
    }
    @Test
    public void findById_returnsCorrectDepartmentById() throws Exception {
        Department department = setUpNewDepartment();
        sql2oDepartmentDao.add(department);
        Department otherDepartment = new Department("IT","Handles IT",45);
        sql2oDepartmentDao.add(otherDepartment);
        assertEquals(department,sql2oDepartmentDao.findById(department.getId()));
    }
   /* @Test
    public void getDepartmentNews() {
        User user=setUpNewUser();
        sql2oUsersDao.save(user);
        Department departments=setUpNewDepartment();
        sql2oDepartmentDao.add(departments);
        Department_News department_news =new Department_News("Meeting","To nominate new chairman",departments.getId(),user.getId());
        sql2oNewsDao.addDepartmentNews(department_news);
        News news=new News("Meeting","Meeting to set activities for team building",user.getId());
        sql2oNewsDao.addNews(news);

        assertEquals(department_news.getTitle(),sql2oDepartmentDao.getDepartmentNews(department_news.getId()).get(0).getTitle());
    }*/

    //Helper
    private Department setUpNewDepartment(){
        return new Department("Finance","Handles Finances",45);
    }
    private User setUpNewUser(){
        return  new User("Linda","linda@linda.com","Manager","Manage Office activities","Finance");
    }
}