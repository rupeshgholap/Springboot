package com.example.employee_crud;

import com.example.employee_crud.entities.Address;
import com.example.employee_crud.entities.Employee;
import com.example.employee_crud.repository.EmployeeRepository;
import com.example.employee_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeCrudApplication {


	public static void main(String[] args) {
		SpringApplication.run(EmployeeCrudApplication.class, args);

		System.out.println("---------------------------------------------------");
		System.out.println("Project employee Started");

	}

}
