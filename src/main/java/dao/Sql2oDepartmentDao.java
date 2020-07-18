package dao;

import models.Department;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao {
    private Sql2o sql2o;
    public Sql2oDepartmentDao(Sql2o sql2o){
        this.sql2o =sql2o;
    }


    @Override
    public void add(Department department) {
        String add = "INSERT INTO TABLE departments(department,description,noOfEmployees) VALUES (:department, :description, :noOfEmployees)";
        try(Connection con =sql2o.open()) {
            int id=(int)con.createQuery(add,true).bind(department).executeUpdate().getKey();
            department.setId(id);
        }

    }

    @Override
    public List<Department> getAllDepartments() {
        String sql ="SELECT * FROM departments";
        try(Connection con=sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Department.class);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void deletebyId() {

    }
}
