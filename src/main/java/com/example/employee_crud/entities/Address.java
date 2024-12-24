package com.example.employee_crud.entities;

import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column
    private String locality;
    @Column
    private String city;
    @Column
    private int pin;

    public Address()
    {

    }
    public Address(String locality, String city, int pin)
    {
        this.locality=locality;
        this.city=city;
        this.pin=pin;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLocality() {
        return locality;
    }
    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public int getPin() {
        return pin;
    }
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", locality='" + locality + '\'' +
                ", city='" + city + '\'' +
                ", pin=" + pin +
                '}';
    }
}
