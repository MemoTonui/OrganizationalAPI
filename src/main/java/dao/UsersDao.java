package dao;

import models.User;

import java.util.List;

public interface UsersDao {
    //Create
    void add(User user);

    //Read
    List <User> getAllUsers();

    //Update
    void update();

    //Delete
    void deletebyId();
}
