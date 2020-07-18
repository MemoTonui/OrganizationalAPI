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
    private Connection conn;


    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://localhost:5432/organization_test";
        Sql2o sql2o = new Sql2o(connectionString, "tonui", "chepkemoi1999.");
        sql2oUsersDao = new Sql2oUsersDao(sql2o);
        sql2oDepartmentDao =new  Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

   /*

    @Test
    public void getAllUsers_getsAllUsers() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities","Finance");
        UsersDao.save(user);
        assertEquals(user,UsersDao.getAllUsers().contains(user));
    }

    @Test
    public void getAllUsers_getsAllUsersWhenMoreThanOne() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities","Finance");
        UsersDao.save(user);
        User user2 = new User("Brian","brian@bee.com","CEO","Head","Management");
        UsersDao.save(user2);
        assertTrue(UsersDao.getAllUsers().contains(user));
        assertTrue(UsersDao.getAllUsers().contains(user2));
    }

    @Test
    public void findById_findsUSerCorectlyById() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities","Finance");
        UsersDao.save(user);
        User user2 = new User("Brian","brian@bee.com","CEO","Head","Management");
        UsersDao.save(user2);
        assertEquals(user,UsersDao.findById(user.getId()));
    }*/
   @Test
   public void save_SavesUserCorrectly() {
       User user = setUpNewUser();
       sql2oUsersDao.save(user);
       assertEquals(user,sql2oUsersDao.getAllUsers().get(0));
   }

    @Test
    public void addingUserToDbSetsUserId() {
        User user = setUpNewUser();
        int originalId= user.getId();
        sql2oUsersDao.save(user);
        assertNotEquals(originalId,user.getId());
    }

    @Test
    public void addedUserIsReturnedCorrectly() {
        User user = setUpNewUser();
        sql2oUsersDao.save(user);
        assertEquals(user.getName(),sql2oUsersDao.findById(user.getId()).getName());
    }

    @Test
    public void allInstancesOfUsersAreReturned() {

        User user=setUpNewUser();
        User otherUser= new User("Brian","brian@bee.com","CEO","Head","Management");
        sql2oUsersDao.save(user);
        sql2oUsersDao.save(otherUser);
        assertEquals(user.getName(),sql2oUsersDao.getAllUsers().get(0).getName());
        assertEquals(otherUser.getName(),sql2oUsersDao.getAllUsers().get(1).getName());
    }
    @Test
    public void findById_findsUSerCorectlyById() {
        User user = setUpNewUser();
        sql2oUsersDao.save(user);
        User user2 = new User("Brian", "brian@bee.com", "CEO", "Head", "Management");
        sql2oUsersDao.save(user2);
        assertEquals(user.getId(), sql2oUsersDao.findById(user.getId()));
    }

    //Helpers
    private User setUpNewUser(){
        return  new User("Linda","linda@linda.com","Manager","Manage Office activities","Finance");
    }


}