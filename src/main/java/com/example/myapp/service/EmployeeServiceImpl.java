package com.example.myapp.service;

import com.example.myapp.entity.Employee;
import com.example.myapp.exception.BusinessException;
import com.example.myapp.exception.EmptyInputException;
import com.example.myapp.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepo employeeRepo;


    @Override
    public Employee addMyEmployee(Employee emp) {
        if(emp.getEmp_name().isEmpty())
            throw new EmptyInputException("601A","Inputs fields are empty");
        Employee savedEmployee= employeeRepo.save(emp);
        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> emp=null;
        try{
             emp=employeeRepo.findAll();
        }
        catch (Exception e)
        {
              throw  new BusinessException("605A","Something went wrong in service  while fetching all employee"+e.getMessage());
        }
        if(emp.isEmpty())
            throw new BusinessException("604A","Hey employee list is empty we have nothing to show");
        return emp;
    }

    @Override
    public Employee getEmpById(Long empId) {

        try
        {
            return employeeRepo.findById(empId).get();
        }
        catch (IllegalArgumentException e)
        {
            throw  new BusinessException("606A","given employee is null "+ e.getMessage());
        }
        catch (NoSuchElementException e)
        {
            throw  new BusinessException("607A","No Element with id ="+empId+" found" + e.getMessage());
        }

    }

    @Override
    public void deleteEmpByID(Long empId) {

        try
        {
            employeeRepo.deleteById(empId);
        }
        catch (IllegalArgumentException e)
        {
            throw  new BusinessException("606A","given employee is null "+ e.getMessage());
        }
        catch (NoSuchElementException e)
        {
            throw  new BusinessException("607A","No Element with id ="+empId+" found" + e.getMessage());
        }

    }
}
