package com.example.admin.emergencyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.emergencyapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class OfficerLoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private static final String TAG = "EmailPassword";
    private EditText email, password;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_login);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(OfficerLoginActivity.this, PoliceAreaActivity.class));
            finish();
        }

        email = findViewById(R.id.usermail);
        password = findViewById(R.id.etPass);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String offmail = email.getText().toString();
                String offpass = password.getText().toString();

                auth.signInWithEmailAndPassword(offmail, offpass).addOnCompleteListener(OfficerLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user.getEmail().equals("police@police.com")){
                            Intent intent = new Intent(OfficerLoginActivity.this, PoliceAreaActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(user.getEmail().equals("firedept@firedept.com")){
                            Intent intent = new Intent(OfficerLoginActivity.this, FireAreaActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(user.getEmail().equals("hospital@hospital.com")){
                            Intent intent = new Intent(OfficerLoginActivity.this, HospitalAreaActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            Toast.makeText(OfficerLoginActivity.this, "You are not the officer", Toast.LENGTH_LONG);
                            FirebaseAuth.getInstance().signOut();


                        }


                    }
                });

            }
        });


    }

}
