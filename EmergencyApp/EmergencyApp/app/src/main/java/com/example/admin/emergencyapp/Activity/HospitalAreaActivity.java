package com.example.admin.emergencyapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.admin.emergencyapp.Model.Request;
import com.example.admin.emergencyapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HospitalAreaActivity extends AppCompatActivity {

    private DatabaseReference requestRef;
    private ListView mListView;
    private ArrayList<String> mRequest = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_area);

        final ArrayList<Request> mListRequest = new ArrayList<>();

        mListView = findViewById(R.id.listRequest3);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mRequest);




        mListView.setAdapter(arrayAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String requestitem = mRequest.get(position);

                Intent intent = new Intent(HospitalAreaActivity.this, RequestHospitalDetailedActivity.class);
                intent.putExtra("detail", requestitem);

                startActivity(intent);




            }
        });




        FirebaseDatabase database = FirebaseDatabase.getInstance();
        requestRef = database.getReference().child("Request");

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Request request = dataSnapshot.getValue(Request.class);

                String email = request.username;
                String reqId = request.reqId;
                if(request.type.equals("hospital") && request.status == false){
                    mRequest.add(email +" with requestId " + reqId);


                }
                arrayAdapter.notifyDataSetChanged();



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Request request = dataSnapshot.getValue(Request.class);
                String email = request.username;
                String reqId = request.reqId;

                if(request.type.equals("hospital") && request.status == true) {
                    mRequest.remove(email + " with requestId " + reqId);
                }
                arrayAdapter.notifyDataSetChanged();

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

        requestRef.addChildEventListener(childEventListener);




    }

    @Override
    protected void onStart() {
        super.onStart();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_officer, menu);
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
        else if(id == R.id.action_logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HospitalAreaActivity.this, MainActivity.class));
            finish();
        }
        else if(id == R.id.action_history){
            startActivity(new Intent(HospitalAreaActivity.this, HistoryRequestHospitalActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
