package com.example.admin.emergencyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.admin.emergencyapp.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton officer = (ImageButton) findViewById(R.id.officer);
        ImageButton user = (ImageButton) findViewById(R.id.user);

        officer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent officerRegister = new Intent(MainActivity.this , OfficerLoginActivity.class);
                MainActivity.this.startActivity(officerRegister);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userRegister =  new Intent(MainActivity.this , UserLoginActivity.class);
                MainActivity.this.startActivity(userRegister);
            }
        });
    }
}
