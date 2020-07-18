package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Test
    public void News_InstantiatesNewsCorrectly() {
        News news = setUpNews();
        assertTrue(news instanceof News);
    }

    @Test
    public void News_InstantiatesAlternateNewsCorrectly() {
        News alternateNews = setUpAlternateNews();
        assertTrue(alternateNews instanceof News);
    }

    @Test
    public void getUser_id() {
        News news = setUpNews();
        assertEquals(5,news.getUser_id());
    }


    @Test
    public void getDepartment_id() {
        News news = setUpAlternateNews();
        assertEquals(2,news.getDepartment_id());
    }

    @Test
    public void getTitle() {
        News news = setUpNews();
        assertEquals("New Manager",news.getTitle());

    }

    @Test
    public void getDescription() {
        News news = setUpNews();
        assertEquals("You have a new MAnager",news.getDescription());
    }

    @Test
    public void setId() {
        News news = setUpNews();
        news.setId(1);
        assertEquals(1,news.getId());
    }

    @Test
    public void getNews_Type() {
        News news = setUpNews();
        assertEquals("general",news.getNews_type());
    }

    private News setUpNews(){
        return new News("New Manager","You have a new MAnager",5);
    }
    private News setUpAlternateNews(){
        return new News("New Manager","You have a new MAnager",2,5);
    }
}