package dao;

import models.User;

import java.util.List;

public interface UsersDao {
    //Create
    void save(User user);

    //find
    User findById(int id);
    //Read
    List <User> getAllUsers();

    //Update
    void update();

    //Delete
    void deletebyId();
}
