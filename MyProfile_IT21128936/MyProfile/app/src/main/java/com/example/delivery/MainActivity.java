package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextAddress, editTextNumber, editTextEmail, editTextCity, editTextZipCode;
    Button buttonAddUser, buttonEditOption;
    ListView listViewProfiles;
    List<Profile> profiles;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

        initListener();
    }


    private void findViews() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Profile");

        editTextName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextAddress = (EditText) findViewById(R.id.editTextTextPersonName2);
        editTextCity = (EditText) findViewById(R.id.editTextTextPersonName3);
        editTextNumber = (EditText) findViewById(R.id.editTextTextPersonName4);
        editTextEmail = (EditText) findViewById(R.id.editTextTextPersonName5);
        editTextZipCode = (EditText) findViewById(R.id.editTextTextPersonName6);
        listViewProfiles = (ListView) findViewById(R.id.listViewProfiles);
        buttonAddUser = (Button) findViewById(R.id.button3);
        buttonEditOption = (Button) findViewById(R.id.button2);
        profiles = new ArrayList<>();
    }

    private void initListener() {
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
        buttonEditOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditOption.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profiles.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Profile Profile = postSnapshot.getValue(Profile.class);
                    profiles.add(Profile);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    


    private void addUser() {

        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String conNo = editTextNumber.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String zipCode = editTextZipCode.getText().toString().trim();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(address)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(city)){
            Toast.makeText(getApplicationContext(), "Please Enter Your City", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(conNo)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Contact Number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(zipCode)){
            Toast.makeText(getApplicationContext(), "Please Enter Your City ZipCode", Toast.LENGTH_SHORT).show();
        } else {
            String id = databaseReference.push().getKey();
            Profile Profile = new Profile(id, name, address, city, conNo, email, zipCode);
            databaseReference.child(id).setValue(Profile);

            editTextName.setText("");
            editTextNumber.setText("");
            editTextAddress.setText("");
            editTextEmail.setText("");
            editTextCity.setText("");
            editTextZipCode.setText("");
            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
        }

    }
}