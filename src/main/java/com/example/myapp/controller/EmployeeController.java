package com.example.myapp.controller;

import com.example.myapp.entity.Employee;
import com.example.myapp.exception.BusinessException;
import com.example.myapp.exception.ControllerException;
import com.example.myapp.repos.EmployeeRepo;
import com.example.myapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code")
public class EmployeeController {
    //this is EmployeeController
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp)
    {
        try{
            Employee e= employeeService.addMyEmployee(emp);
            return new ResponseEntity<Employee>(e,HttpStatus.CREATED);
        }
        catch (BusinessException e)
        {
            ControllerException con=new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(con,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            ControllerException con=new ControllerException("611","Something went wrong in controller"+e.getMessage());
            return  new ResponseEntity<Exception>(con,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public  ResponseEntity<List<Employee>> getALlEmployee()
    {
        List<Employee> list= employeeService.getAllEmployees();
        return new ResponseEntity<List<Employee> >(list,HttpStatus.OK);
    }

    @GetMapping("/emp/{empId}")
    public  ResponseEntity<?> getEmpById(@PathVariable("empId") Long empId)
    {
        try{
            Employee emp= employeeService.getEmpById(empId);
            return new ResponseEntity<Employee >(emp,HttpStatus.OK);
        }
        catch (BusinessException e)
        {
            ControllerException con=new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<ControllerException>(con,HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            ControllerException con=new ControllerException("611","Something went wrong in controller"+e.getMessage());
            return  new ResponseEntity<Exception>(con,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<Void> deleteEmpByID(@PathVariable("empId") Long empId)
    {
        employeeService.deleteEmpByID(empId);
        return  new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp)
    {
        Employee e= employeeService.addMyEmployee(emp);
        return new ResponseEntity<Employee>(e,HttpStatus.ACCEPTED);
    }

}
