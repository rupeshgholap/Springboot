package com.example.employee_crud.entities;

import jakarta.persistence.*;

@Entity
public class Dependent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private String reletion;

    @ManyToOne(cascade = CascadeType.ALL)
    private Employee employee;

    public Dependent() {

    }

    public Dependent(String name, String gender, String reletion) {
        this.name = name;
        this.gender = gender;
        this.reletion = reletion;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReletion() {
        return reletion;
    }

    public void setReletion(String reletion) {
        this.reletion = reletion;
    }

    @Override
    public String toString() {
        return "Dependent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", reletion='" + reletion + '\'' +
                ", employee=" + employee +
                '}';
    }
}
