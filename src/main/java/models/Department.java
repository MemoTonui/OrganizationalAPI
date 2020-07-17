package models;

public class Department {

    private String departmentName;
    private String description;
    private int noOfEmployees;

    public Department(String departmentName, String description, int noOfEmployees) {
        this.departmentName = departmentName;
        this.description = description;
        this.noOfEmployees = noOfEmployees;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }
}
