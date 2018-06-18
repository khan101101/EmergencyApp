package com.example.admin.emergencyapp.Model;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties

public class Respone {
    public String resId;
    public String officer_type;
    public String clientname;
    public Date date;

    public Respone() {}

    public Respone(String resId, String officer_type, String clientname, Date date) {
        this.resId = resId;
        this.officer_type = officer_type;
        this.clientname = clientname;
        this.date = date;
    }
}
