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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ResponeUserActivity extends AppCompatActivity {

    private DatabaseReference responeRef;
    private ListView mListRespone;
    private ArrayList<String> mRespone = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_respone);

        final ArrayList<Request> listRespone = new ArrayList<>();

        mListRespone = findViewById(R.id.respone_user);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mRespone);




        mListRespone.setAdapter(arrayAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        responeRef = database.getReference().child("Respone");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Respone respone = dataSnapshot.getValue(Respone.class);

                String mail =respone.clientname;
                String officertype = respone.officer_type;
                Date date = Calendar.getInstance().getTime();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user.getEmail().equals(mail) && officertype.equals("police")){
                    mRespone.add("You have receive a respone from Police Department. Time: " + date);

                }
                else if(user.getEmail().equals(mail) && officertype.equals("fire")){
                    mRespone.add("You have receive a respone from Fire Department. Time: " + date);

                }
                else if(user.getEmail().equals(mail) && officertype.equals("hospital")){
                    mRespone.add("You have receive a respone from Hospital Center. Time: " + date);

                }
                arrayAdapter.notifyDataSetChanged();



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

        responeRef.addChildEventListener(childEventListener);




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
            startActivity(new Intent(ResponeUserActivity.this, UserAreaActivity.class));
            finish();
        }
        else if(id == R.id.action_logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ResponeUserActivity.this, UserLoginActivity.class));
            finish();


        }



        return super.onOptionsItemSelected(item);
    }

}
