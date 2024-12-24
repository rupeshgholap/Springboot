package com.example.employee_crud.controllers;

import com.example.employee_crud.entities.Employee;
import com.example.employee_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee saveUpdate(@RequestBody Employee employee)
    {
        System.out.println("checking if receving or not"+employee);
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployee()
    {
        return  employeeService.getEmployee();
    }

    @GetMapping("/{id}")
    public Optional<Employee> EmployeeID(@PathVariable int id)
    {
        return employeeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable int id)
    {
        employeeService.DeleteByID(id);
    }

    @GetMapping("/department/{department}")
    public List<Employee> getBYDepartment(@PathVariable String department)
    {
        return employeeService.findByDepartment(department);
    }

}
