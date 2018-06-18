package com.example.admin.emergencyapp.Model;

public abstract class User {
    public String id;
    public String name;
    public String email;
    public String password;
    public int age;
    public String typeuser;


    public User(String id, String name, String email, String password, int age, String typeuser) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.typeuser = typeuser;
    }



}
