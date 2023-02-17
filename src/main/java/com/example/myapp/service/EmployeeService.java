package com.example.myapp.service;

import com.example.myapp.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addMyEmployee(Employee emp);

    List<Employee> getAllEmployees();

    Employee getEmpById(Long empId);

    void deleteEmpByID(Long empId);
}
