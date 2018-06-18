package com.example.admin.emergencyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.admin.emergencyapp.Model.Request;
import com.example.admin.emergencyapp.Model.Respone;
import com.example.admin.emergencyapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class UserAreaActivity extends AppCompatActivity {

    private ImageButton btnpolice, btnfire, btnambulance;
    private DatabaseReference databaseRequest, databaseRespone;

    private Request request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        btnpolice = (ImageButton) findViewById(R.id.police);
        btnfire = (ImageButton) findViewById(R.id.firedept);
        btnambulance = (ImageButton) findViewById(R.id.ambulance);

        databaseRequest = FirebaseDatabase.getInstance().getReference("Request");

        databaseRespone = FirebaseDatabase.getInstance().getReference().child("Respone");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Respone respone = dataSnapshot.getValue(Respone.class);
                String mail =respone.clientname;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user.getEmail().equals(mail)){
                    Log.d("receive", "You have many respones");

                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        databaseRespone.addChildEventListener(childEventListener);





        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(UserAreaActivity.this, "Welcome, you login as " + name, Toast.LENGTH_SHORT).show();


        btnpolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date c = Calendar.getInstance().getTime();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){
                    String clientID = user.getUid();
                    String name = user.getEmail();
                    String id = databaseRequest.push().getKey();
                    request = new Request(id, clientID, name,c, false, "police");
                    databaseRequest.child(id).setValue(request);

                }

            }
        });
        btnfire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date c = Calendar.getInstance().getTime();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){
                    String clientID = user.getUid();
                    String name = user.getEmail();
                    String id = databaseRequest.push().getKey();
                    request = new Request(id, clientID, name,c, false, "fire");
                    databaseRequest.child(id).setValue(request);

                }

            }
        });
        btnambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date c = Calendar.getInstance().getTime();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null){
                    String clientID = user.getUid();
                    String name = user.getEmail();
                    String id = databaseRequest.push().getKey();
                    request = new Request(id, clientID, name,c, false, "hospital");
                    databaseRequest.child(id).setValue(request);

                }

            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_home){
            startActivity(new Intent(UserAreaActivity.this, UserAreaActivity.class));
            finish();
        }
        else if(id == R.id.action_logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(UserAreaActivity.this, UserLoginActivity.class));
            finish();


        }
        else if(id == R.id.action_respone){
            startActivity(new Intent(UserAreaActivity.this, ResponeUserActivity.class));
            finish();

        }

        return super.onOptionsItemSelected(item);
    }
}
