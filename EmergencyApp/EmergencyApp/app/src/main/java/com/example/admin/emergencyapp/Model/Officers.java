package com.example.admin.emergencyapp.Model;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Officers extends User{


    public Officers(String id, String name, String email, String password, int age, String typeuser) {
        super(id, name, email, password, age, typeuser);
    }
}
