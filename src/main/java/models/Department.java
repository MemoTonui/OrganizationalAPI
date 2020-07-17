package models;

import java.util.Objects;

public class Department {

    private String departmentName;
    private String description;
    private int noOfEmployees;
    private int id;

    public Department(String departmentName, String description, int noOfEmployees) {
        this.departmentName = departmentName;
        this.description = description;
        this.noOfEmployees = noOfEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return noOfEmployees == that.noOfEmployees &&
                id == that.id &&
                departmentName.equals(that.departmentName) &&
                description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentName, description, noOfEmployees, id);
    }
}
