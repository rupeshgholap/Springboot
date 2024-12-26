package com.example.employee_crud.repository;

import com.example.employee_crud.entities.Dependent;
import com.example.employee_crud.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> findByDepartment(@Param("department") String department);

    @Query("SELECT e.dependent FROM Employee e WHERE e.id = :employeeID")
    List<Dependent> dependentById(@Param("employeeID")int id);




}
