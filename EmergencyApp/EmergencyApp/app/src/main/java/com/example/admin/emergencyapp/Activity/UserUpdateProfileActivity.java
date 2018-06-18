package com.example.admin.emergencyapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.emergencyapp.Model.Client;
import com.example.admin.emergencyapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserUpdateProfileActivity extends AppCompatActivity {

    private EditText newAge, newName, password, newPhone;
    private Button btnAcc;

    DatabaseReference clientRef;
    private FirebaseAuth clientAuth;
    private String id;
    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        newAge = (EditText) findViewById(R.id.newAge);
        newName = (EditText) findViewById(R.id.newName);
        newPhone = (EditText) findViewById(R.id.newPhone);
        btnAcc = (Button) findViewById(R.id.bAccept);


        clientRef = FirebaseDatabase.getInstance().getReference().child("Client");
        clientAuth = FirebaseAuth.getInstance();

        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = newName.getText().toString();
                String phone = newPhone.getText().toString();
                int age = Integer.parseInt(newAge.getText().toString());
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


            }
        });


    }

}
