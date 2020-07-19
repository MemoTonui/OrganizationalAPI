package dao;

import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUsersDaoTest {

    private static Sql2oDepartmentDao sql2oDepartmentDao;
    private static Sql2oUsersDao sql2oUsersDao;
    private static  Sql2oNewsDao sql2oNewsDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://localhost:5432/organization_test";
        Sql2o sql2o = new Sql2o(connectionString, "tonui", "chepkemoi1999.");
        sql2oUsersDao = new Sql2oUsersDao(sql2o);
        sql2oDepartmentDao =new  Sql2oDepartmentDao(sql2o);
        sql2oNewsDao= new Sql2oNewsDao(sql2o);
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
   public void save_SavesUserCorrectly() {
       User user = setUpNewUser();
       sql2oUsersDao.save(user);
       assertTrue(sql2oUsersDao.getAllUsers().contains(user));
       assertEquals(1,sql2oUsersDao.getAllUsers().size());
   }

    @Test
    public void addingUserToDbSetsUserId() {
        User user = setUpNewUser();
        int originalId= user.getId();
        sql2oUsersDao.save(user);
        assertNotEquals(originalId,user.getId());
    }

    @Test
    public void UserAddedIsReturnedCorrectly() {
        User user = setUpNewUser();
        sql2oUsersDao.save(user);
        assertEquals(user.getName(),sql2oUsersDao.findById(user.getId()).getName());
    }

    @Test
    public void getAllUsers_allInstancesOfUsersAreReturned() {

        User user=setUpNewUser();
        User otherUser= new User("Brian","brian@bee.com","CEO","Head");
        sql2oUsersDao.save(user);
        sql2oUsersDao.save(otherUser);
        assertEquals(user.getName(),sql2oUsersDao.getAllUsers().get(0).getName());
        assertEquals(otherUser.getName(),sql2oUsersDao.getAllUsers().get(1).getName());
    }
    @Test
    public void findById_findsUSerCorrectlyById() {
        User user = setUpNewUser();
        sql2oUsersDao.save(user);
        User user2 = new User("Brian", "brian@bee.com", "CEO", "Head");
        sql2oUsersDao.save(user2);
        assertEquals(user, sql2oUsersDao.findById(user.getId()));
    }

    //Helpers
    private User setUpNewUser(){
        return  new User("Linda","linda@linda.com","Manager","Manage Office activities");
    }


}