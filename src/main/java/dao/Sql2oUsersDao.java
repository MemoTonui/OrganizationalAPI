package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.List;
import org.sql2o.Sql2oException;
import org.sql2o.logging.SysOutLogger;

public class Sql2oUsersDao implements UsersDao {
    private Sql2o sql2o ;


    public Sql2oUsersDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public  void save(User user) {
        String sql = "INSERT INTO TABLE users (name,email,pos,role,department) VALUES (:name,:email,:pos,:role,:department)";
        try(Connection con =sql2o.open()) {
        int id = (int) con.createQuery(sql,true).bind(user).executeUpdate().getKey();
         user.setId(id);
        }
      catch (Sql2oException ex){
          System.out.println(ex);

      }

    }

    @Override
    public  List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(User.class);
        }
        //return null;
    }
    @Override
    public  User findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM users WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void deletebyId() {

    }
}
