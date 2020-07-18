package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Department_NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNews_type() {
        News news = setUpAlternateNews();
        assertEquals("department",news.getNews_type());

    }

    @Test
    public void getDepartment_id() {
        News news = setUpAlternateNews();
        assertEquals(2,news.getDepartment_id());
    }


    private News setUpNews(){
        return new News("New Manager","You have a new MAnager",5);
    }
    private News setUpAlternateNews(){
        return new News("New Manager","You have a new MAnager",2,5);
    }
}