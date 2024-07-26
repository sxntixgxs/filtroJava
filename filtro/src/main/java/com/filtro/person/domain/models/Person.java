package com.filtro.person.domain.models;

public class Person {
    int id;
    String name;
    String lastname;
    int idCity;
    String address;
    int age;
    String email;
    int idgender;
    public Person(String name, String lastname, int idCity, String address, int age, String email, int idgender) {
        this.name = name;
        this.lastname = lastname;
        this.idCity = idCity;
        this.address = address;
        this.age = age;
        this.email = email;
        this.idgender = idgender;
    }
    public Person(int id, String name, String lastname, int idCity, String address, int age, String email,
            int idgender) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.idCity = idCity;
        this.address = address;
        this.age = age;
        this.email = email;
        this.idgender = idgender;
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
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public int getIdCity() {
        return idCity;
    }
    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getIdgender() {
        return idgender;
    }
    public void setIdgender(int idgender) {
        this.idgender = idgender;
    }
}
