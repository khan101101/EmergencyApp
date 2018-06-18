package com.example.admin.emergencyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.emergencyapp.Model.Respone;
import com.example.admin.emergencyapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class RequestHospitalDetailedActivity extends AppCompatActivity {
    private TextView description3;
    private String reqid;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_row);
        description3 = findViewById(R.id.description3);
        Intent intent = getIntent();
        String a = intent.getStringExtra("detail");

        description3.setText("User " + a + " has send an emergency request.");
        String[] b = a.split(" ");
        reqid = b[3];


    }

    public void respone(View view) {
        DatabaseReference responeRef = FirebaseDatabase.getInstance().getReference("Respone");
        String usermail = description3.getText().toString();
        DatabaseReference requestRef = FirebaseDatabase.getInstance().getReference();
        requestRef.child("Request").child(reqid).child("status").setValue(true);


        Toast.makeText(RequestHospitalDetailedActivity.this, usermail, Toast.LENGTH_LONG).show();

        String resId = responeRef.push().getKey();
        String type = "hospital";
        Date c = Calendar.getInstance().getTime();

        Respone respone = new Respone(resId, type, usermail, c);
        responeRef.child(resId).setValue(respone);
    }
}
