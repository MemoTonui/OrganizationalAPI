package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void User_InstantiatesOjectCorrectly_true() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        assertTrue(user instanceof User);
    }
    @Test
    public void setName_setsNameCorrectly_true() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        user.setName("Joy");
        assertNotEquals("Linda",user.getName());
    }
    @Test
    public void getName_getsNameCorrectly_true() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        assertEquals("Linda",user.getName());
    }
    @Test
    public void setEmail_setsEmailCorrectly_true() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        user.setEmail("tonui@tonui.com");
        assertNotEquals("linda@linda.com",user.getEmail());
    }
    @Test
    public void getEmail_getEmailCorrectly_true() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        assertEquals("linda@linda.com",user.getEmail());
    }

    @Test
    public void setPosition_setsPositionCorrectly_true() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        user.setPos("Sweeper");
        assertNotEquals("Manager",user.getPos());
    }
    @Test
    public void getPosition_getsPositionCorrectly() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        assertEquals("Manager",user.getPos());
    }
    @Test
    public void setsRoles_setsRolesCorrectly_true() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        user.setRole("Sweep floors");
        assertNotEquals("Manage Office activities",user.getRole());
    }
    @Test
    public void getRoles_getsRoleCorrectly() {
        User user = new User("Linda","linda@linda.com","Manager","Manage Office activities");
        assertEquals("Manage Office activities",user.getRole());
    }

}