package com.example.admin.emergencyapp.Model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Client extends User {
    public String phonenumber;



    public Client(String id, String name, String email, String password, int age, String typeuser, String phone) {

        super(id, name, email, password, age, typeuser);
        this.phonenumber = phone;
    }




}
