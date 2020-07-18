package dao;

import models.Department;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentDaoTest {
    private Sql2oDepartmentDao DepartmentDao;
    private Sql2oUsersDao UsersDao;
    private Connection conn;


    @Before
    public void setUp() throws Exception {

        String connectionString = "jdbc:postgresql://localhost:5432/organization_test";
        Sql2o sql2o = new Sql2o(connectionString, "tonui", "chepkemoi1999.");
        //Sql2o sql2o = new Sql2o(connectionString, "", "");
        UsersDao = new Sql2oUsersDao(sql2o);
        DepartmentDao =new  Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addDepartment_AddsDepartmentCorrectly_true() {
        Department department = new Department("Finance","Handles Finances",45);
        int deptId = department.getId();
        DepartmentDao.add(department);
        assertEquals(deptId,department.getId());
    }
}