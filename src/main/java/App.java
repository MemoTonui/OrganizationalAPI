import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oNewsDao;
import dao.Sql2oUsersDao;
import exception.ApiException;
import models.Department;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.after;

public class App {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/organization";
        Sql2o sql2o = new Sql2o(connectionString, "tonui", "chepkemoi1999.");

         Sql2oDepartmentDao sql2oDepartmentDao= new Sql2oDepartmentDao(sql2o);
         Sql2oUsersDao sql2oUsersDao = new Sql2oUsersDao(sql2o);
         Sql2oNewsDao sql2oNewsDao =new Sql2oNewsDao(sql2o);
         Connection conn;
         Gson gson= new Gson();
         

        

        //Posts new users
        //works
        post("/user/new","application/json",(request, response) -> {
            User user = gson.fromJson(request.body(),User.class);
            sql2oUsersDao.save(user);
            response.status(201);
            return gson.toJson(user);
        });

        //get all users
        //works
        get("/user", "application/json", (request, response) -> {
               if(sql2oUsersDao.getAllUsers().size()>0){
                return gson.toJson(sql2oUsersDao.getAllUsers());
            }
            else {
                return "{\"message\":\"Sorry No Users Here!!\"}";
            }
        });

        //get individual user
        //works
        get("/user/:id", "application/json", (request, response) -> {
            int id=Integer.parseInt(request.params("id"));
            if(sql2oUsersDao.findById(id)==null){
                throw new ApiException(404, String.format("User with the id: \"%s\" doesn't exist",request.params("id")));
            }
            else {
                return gson.toJson(sql2oUsersDao.findById(id));
            }
        });




        //New Departments
        //works
        post("/department/new","application/json",(request, response) -> {
            Department department =gson.fromJson(request.body(),Department.class);
            sql2oDepartmentDao.add(department);
            response.status(201);
            return gson.toJson(department);
        });

        //get all departments
        //works
        get("/departments","application/json",(request, response) -> {
            if(sql2oDepartmentDao.getAllDepartments().size()>0){
                return gson.toJson(sql2oDepartmentDao.getAllDepartments());
            }
            else {
                return "{\"message\":\"No Departments Listed here\"}";
            }
        });
        //new  user in a department
        post("/departments/:department_id/users/:user_id","application/json",(request, response) -> {
            int user_id=Integer.parseInt(request.params("user_id"));
            int department_id=Integer.parseInt(request.params("department_id"));
            Department departments=sql2oDepartmentDao.findById(department_id);
            User user=sql2oUsersDao.findById(user_id);
            if(departments==null){
                throw new ApiException(404, String.format("Department with the id: \"%s\" doesn't exists",
                        request.params("department_id")));
            }
            if(user==null){
                throw new ApiException(404, String.format("User with the id: \"%s doesn't exist",
                        request.params("user_id")));
            }
            sql2oDepartmentDao.addUserToDepartment(user,departments);

            List<User> usersInDepartment = sql2oDepartmentDao.getAllUsersInDepartment(department_id);

            response.status(201);
            return gson.toJson(usersInDepartment);
        });

        //gets users in a specific department
        //works
        get("/departments/:id/users","application/json",(request, response) -> {
            int id=Integer.parseInt(request.params("id"));
            if(sql2oDepartmentDao.getAllUsersInDepartment(id).size()>0){
                return gson.toJson(sql2oDepartmentDao.getAllUsersInDepartment(id));
            }
            else {
                return "{\"message\":\"Sorry!!This Department is Empty!!.\"}";
            }
        });

        //get specific user in a department
        //works
        get("/departments/:id/users/:id","application/json",(request, response) -> {
            int departmentId=Integer.parseInt(request.params("id"));
            int userId=Integer.parseInt(request.params("id"));
            if(sql2oDepartmentDao.getAllUsersInDepartment(departmentId).size()>0){
                return gson.toJson(sql2oDepartmentDao.findById(userId));
            }
            else {
                return "{\"message\":\" User doesn't Exist!!\"}";
            }
        });
        //Get individual departments
        //works
        get("/department/:id","application/json",(request, response) -> {
            int id=Integer.parseInt(request.params("id"));
            if(sql2oDepartmentDao.findById(id)==null){
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")));
            }
            else {
                return gson.toJson(sql2oDepartmentDao.findById(id));
            }
        });

        //New General News
        //works
        post("/news/new/general","application/json",(request, response) -> {

            News news =gson.fromJson(request.body(),News.class);
            sql2oNewsDao.addNews(news);
            response.status(201);
            return gson.toJson(news);
        });

        //get general news
        //works
        get("/news/general","application/json",(request, response) -> {
            if(sql2oNewsDao.getAll().size()>0){
                return gson.toJson(sql2oNewsDao.getAll());
            }
            else {
                return "{\"message\":\"We have no news for you!!!\"}";
            }
        });




        //New Departmental News
        //works
        post("/news/new/department","application/json",(request, response) -> {
            News department_news =gson.fromJson(request.body(),News.class);
            Department department=sql2oDepartmentDao.findById(department_news.getDepartment_id());
            User user =sql2oUsersDao.findById(department_news.getUser_id());
            if(department==null){
                throw new ApiException(404, String.format("Department with the id: \"%s doesn't exist",
                        request.params("department_id")));
            }
            else if(user==null){
                throw new ApiException(404, String.format("User with the id: \"%s\" doesn't exists",
                        request.params("user_id")));
            }
            else {
                sql2oNewsDao.addNews(department_news);
                response.status(201);
                return gson.toJson(department_news);
            }
        });

        // get news for a particular department
        // works
        get("/news/departments/:id","application/json",(request, response) -> {

            int id=Integer.parseInt(request.params("id"));
            Department department =sql2oDepartmentDao.findById(id);
            if(department==null){
                throw new ApiException(404, String.format(" Department with the id: \"%s\" does not exists",
                        request.params("id")));
            }
             else if(sql2oDepartmentDao.getDepartmentNews(id).size()>0){
                return gson.toJson(sql2oDepartmentDao.getDepartmentNews(id));
            }
            else {
                return "{\"message\":\"We have no news for you!\"}";
            }
        });




        //FILTERS
        exception(ApiException.class, (exception, request, response) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });


        after((request, response) ->{
            response.type("application/json");
        });


    }
}



