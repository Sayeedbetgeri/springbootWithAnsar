
package com.myapp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

public class Data {

    @JsonAlias("employee_age")
    private Long employeeAge;
    @JsonAlias("employee_name")
    private String employeeName;
    @JsonAlias("employee_salary")
    private Long employeeSalary;
    private Long id;
    @JsonAlias("profile_image")
    private String profileImage;

    public Long getEmployeeAge() {
        return employeeAge;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Long getEmployeeSalary() {
        return employeeSalary;
    }

    public Long getId() {
        return id;
    }

    public String getProfileImage() {
        return profileImage;
    }


}
