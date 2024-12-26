package com.example.employee_crud.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "projects",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Employee> employees = new ArrayList<>();

    public Project()
    {

    }
    public Project(int id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees(){return employees;}
    public void setEmployees(List<Employee> employees){this.employees=employees;}

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employees=" + employees +
                '}';
    }
}
