package com.example.employee_crud.controllers;

import com.example.employee_crud.entities.Dependent;
import com.example.employee_crud.entities.Employee;
import com.example.employee_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee saveUpdate(@RequestBody Employee employee)
    {
        System.out.println("checking if receving or not"+employee);
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees/all")
    public List<Employee> getAllEmployee()
    {
        return  employeeService.getEmployee();
    }

    @GetMapping("/employees/{id}")
    public Optional<Employee> EmployeeID(@PathVariable int id)
    {
        return employeeService.getById(id);
    }

    @DeleteMapping("/employees/{id}")
    public void Delete(@PathVariable int id)
    {
        employeeService.DeleteByID(id);
    }

    @GetMapping("/employees/department/{department}")
    public List<Employee> getBYDepartment(@PathVariable String department)
    {
        return employeeService.findByDepartment(department);
    }

    @GetMapping("/employees/{id}/dependents")
    public List<Dependent> getDependent(@PathVariable int id)
    {
        return employeeService.findDependent(id);
    }

    @GetMapping("/employees/hello")
    public String hello()
    {
        return "hello consumer from employee project ";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('user')")
    public String userHello()
    {
        return "Access granted to user";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String adminHello()
    {
        return "Access granted to admin";
    }
}
