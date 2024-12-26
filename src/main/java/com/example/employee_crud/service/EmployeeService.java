package com.example.employee_crud.service;

import com.example.employee_crud.entities.Dependent;
import com.example.employee_crud.entities.Employee;
import com.example.employee_crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployee()
    {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getById(int id)
    {
        return employeeRepository.findById(id);

    }

    public void DeleteByID(int id)
    {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findByDepartment(String department)
    {
        return employeeRepository.findByDepartment(department);
    }

    public List<Dependent> findDependent(int id)
    {
        return employeeRepository.dependentById(id);
    }

}
