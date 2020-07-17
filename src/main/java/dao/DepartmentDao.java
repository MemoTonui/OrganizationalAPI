package dao;

import models.Department;
import models.User;

import java.util.List;

public interface DepartmentDao {
    //Create
    void add(Department department);

    //Read
    List<Department> getAllDepartments();

    //Update
    void update();

    //Delete
    void deletebyId();
}
