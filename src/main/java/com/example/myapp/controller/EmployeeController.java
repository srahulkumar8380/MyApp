package com.example.myapp.controller;

import com.example.myapp.entity.Employee;
import com.example.myapp.exception.BusinessException;
import com.example.myapp.exception.ControllerException;
import com.example.myapp.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code")
public class EmployeeController {
    //this is EmployeeController
    final
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> addEmployee(@RequestBody Employee emp)
    {
        //In this method GlobalException Handling is implemented
        Employee e= employeeService.addMyEmployee(emp);
        return new ResponseEntity<>(e,HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public  ResponseEntity<List<Employee>> getALlEmployee()
    {
        List<Employee> list= employeeService.getAllEmployees();
        return new ResponseEntity< >(list,HttpStatus.OK);
    }

    @GetMapping("/emp/{empId}")
    public  ResponseEntity<?> getEmpById(@PathVariable("empId") Long empId)
    {
        //In this method CustomException Handling is implemented
        try{
            Employee emp= employeeService.getEmpById(empId);
            return new ResponseEntity< >(emp,HttpStatus.OK);
        }
        catch (BusinessException e)
        {
            ControllerException con=new ControllerException(e.getErrorCode(),e.getErrorMessage());
            return new ResponseEntity<>(con,HttpStatus.BAD_REQUEST);
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
        return  new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp)
    {
        Employee e= employeeService.addMyEmployee(emp);
        return new ResponseEntity<>(e,HttpStatus.ACCEPTED);
    }

}
