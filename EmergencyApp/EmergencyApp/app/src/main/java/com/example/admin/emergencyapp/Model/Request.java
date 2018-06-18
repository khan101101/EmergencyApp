package com.example.admin.emergencyapp.Model;

import com.google.firebase.database.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties

public class Request {
    public String reqId;
    public String clientId;
    public String username;
    public Date date;
    public Boolean status = false;
    public String type;

    public Request(){

    }

    public Request(String reqId, String clientId, String username, Date date, Boolean status, String type) {
        this.reqId = reqId;
        this.clientId = clientId;
        this.username = username;
        this.date = date;
        this.status = status;
        this.type = type;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("reqid", reqId);
        result.put("clientid", clientId);
        result.put("clientname", username);
        result.put("date", date);
        result.put("status", status);
        result.put("type", type);
        return  result;

    }
}
