package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void Department_InstantiatesDepartmentCorrectly() {
        Department department = new Department("Finance","Handles Finances");
        assertTrue(department instanceof Department);
    }

    @Test
    public void setDepartmentName_SetsNameCorrectly_true() {
        Department department = new Department("Finance","Handles Finances");
        department.setDepartmentName("IT");
        assertNotEquals("Finance",department.getDepartmentName());
    }

    @Test
    public void getDepartmentName_getsDepartmentCorrectly() {
        Department department = new Department("Finance","Handles Finances");
        assertEquals("Finance",department.getDepartmentName());
    }
    @Test
    public void setDescription_SetsDescriptionCorrectly_true() {
        Department department = new Department("Finance","Handles Finances");
        department.setDescription("Create Programs");
        assertNotEquals("Handles Finances",department.getDescription());
    }

    @Test
    public void getDescription_getsDescriptionCorrectly() {
        Department department = new Department("Finance","Handles Finances");
        assertEquals("Handles Finances",department.getDescription());
    }


}