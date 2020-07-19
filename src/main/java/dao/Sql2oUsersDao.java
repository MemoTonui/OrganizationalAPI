package dao;

import models.Department;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;


import java.util.ArrayList;
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

        try(Connection con =sql2o.open()) {
            String sql = "INSERT INTO users (name, email, pos, role) VALUES (:name, :email, :pos, :role)";
        int id = (int) con.createQuery(sql,true)
                .bind(user)
                .executeUpdate()
                .getKey();
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
    public List<Department> getAllDepartmentsForUsers(int user_id) {
        List<Department> departments=new ArrayList<>();
        try (Connection con=sql2o.open()) {
            String sql = "SELECT department_id FROM users_departments WHERE user_id=:user_id";
            List<Integer> departmentIds = con.createQuery(sql)
                    .addParameter("user_id", user_id)
                    .executeAndFetch(Integer.class);

            for (Integer id : departmentIds) {
                String userResults = "SELECT * FROM departments WHERE id=:id";
                departments.add(con.createQuery(userResults)
                        .addParameter("id", id)
                        .executeAndFetchFirst(Department.class));

            }

            return departments;
        }
    }


    //Used for tests

    @Override
    public void clearAll() {

        try (Connection con=sql2o.open()){
            String sql ="DELETE FROM users ";
            con.createQuery(sql).executeUpdate();
            String sqlUsersDepartments="DELETE FROM users_departments";
            con.createQuery(sqlUsersDepartments).executeUpdate();

        }catch (Sql2oException e){
            System.out.println(e);
        }


    }
}
