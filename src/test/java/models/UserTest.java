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
        User user = new User("Linda","linda@linda.com","Manager","Finance");
        assertTrue(user instanceof User);
    }

}