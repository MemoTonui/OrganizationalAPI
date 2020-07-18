package dao;

import models.Department;
import models.News;
import models.User;

import java.util.List;

public interface DepartmentDao {
    //Create
    void add(Department department);
    void addUserToDepartment(User user,Department department);

    //Read
    List<Department> getAllDepartments();
    List<User> getAllUsersInDepartment(int department_id);
    List<News> getDepartmentNews(int id);
    Department findById(int id);



    //Delete
    void clearAll();
}
