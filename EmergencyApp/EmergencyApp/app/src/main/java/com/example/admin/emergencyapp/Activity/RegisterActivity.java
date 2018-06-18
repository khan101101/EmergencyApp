package com.example.admin.emergencyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.emergencyapp.Model.Client;
import com.example.admin.emergencyapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText etAge, etName, etEmail, etPassword, etPhone;
    private Button bRegister;

    DatabaseReference databaseCustomer;
    private FirebaseAuth clientAuth;
    private String id;
    private Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.usermail);
        etPassword = (EditText) findViewById(R.id.etPass);
        bRegister = (Button) findViewById(R.id.bRegister);
        etPhone = (EditText) findViewById(R.id.etPhone);


        databaseCustomer = FirebaseDatabase.getInstance().getReference("Client");
        clientAuth = FirebaseAuth.getInstance();

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString().trim();
                int age = Integer.parseInt(etAge.getText().toString());
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String type = "user";
                String phone = etPhone.getText().toString();
                id = databaseCustomer.push().getKey();

                client = new Client(id, name, email, password, age, type, phone);
                databaseCustomer.child(id).setValue(client);



                clientAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intentUser = new Intent(RegisterActivity.this, UserAreaActivity.class);
                            intentUser.putExtra("name", name);
                            startActivity(intentUser);

                            finish();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

                });
//                if(clientAuth.getCurrentUser() != null){
//
//                }
            }
        });
    }



}
