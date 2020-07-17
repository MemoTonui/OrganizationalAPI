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
        Department department = new Department("Finance","Handles Finances",45);
        assertTrue(department instanceof Department);
    }

    @Test
    public void setDepartmentName_SetsNameCorrectly_true() {
        Department department = new Department("Finance","Handles Finances",45);
        department.setDepartmentName("IT");
        assertNotEquals("Finance",department.getDepartmentName());
    }

    @Test
    public void getDepartmentName_getsDepartmentCorrectly() {
        Department department = new Department("Finance","Handles Finances",45);
        assertEquals("Finance",department.getDepartmentName());
    }
    @Test
    public void setDescription_SetsDescriptionCorrectly_true() {
        Department department = new Department("Finance","Handles Finances",45);
        department.setDescription("Create Programs");
        assertNotEquals("Handles Finances",department.getDescription());
    }

    @Test
    public void getDescription_getsDescriptionCorrectly() {
        Department department = new Department("Finance","Handles Finances",45);
        assertEquals("Handles Finances",department.getDescription());
    }
    @Test
    public void setEmployeePopulation_SetsNumberCorrectly_true() {
        Department department = new Department("Finance","Handles Finances",45);
        department.setNoOfEmployees(25);
        assertNotEquals(45,department.getNoOfEmployees());
    }

    @Test
    public void getEmployeePopulation_getsEmployeeNumberCorrectly() {
        Department department = new Department("Finance","Handles Finances",45);
        assertEquals(45,department.getNoOfEmployees());
    }
}