package dao;

import models.Department;
import models.User;

import java.util.List;

public interface UsersDao {
    //Create
    void save(User user);

    //find
    User findById(int id);
    //Read
    List <User> getAllUsers();
    List<Department> getAllDepartmentsForUsers(int user_id);


    //Delete
    void clearAll();
}
