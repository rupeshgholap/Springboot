package com.example.employee_crud.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String department;
    @Column
    private double salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "employee_id")
    private List<Dependent> dependent = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_projects")
    @JsonManagedReference
    private List<Project> projects = new ArrayList<>();

    public Employee()
    {

    }
    public Employee(String name, String department, double salary, Address address, List<Dependent> dependent, List<Project> projects)
    {
        this.name=name;
        this.department=department;
        this.salary=salary;
        this.address=address;
        this.dependent=dependent;
        this.projects=projects;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Address getAddress(){return address;}
    public void setAddress(Address address){this.address=address;}

    public List<Dependent> getDependent() {
        return dependent;
    }
    public void setDependent(List<Dependent> dependent) {
        this.dependent = dependent;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", address=" + address +
                ", dependent=" + dependent +
                ", projects=" + projects +
                '}';
    }
}
