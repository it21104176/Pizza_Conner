package com.example.delivery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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

public class EditOption extends AppCompatActivity {

    EditText editTextName, editTextAddress, editTextNumber, editTextEmail, editTextCity, editTextZipCode;
    ListView listViewUsers;
    List<Profile> profiles;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_option);

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
        listViewUsers = (ListView) findViewById(R.id.listViewProfiles);
        profiles = new ArrayList<>();
    }

    private void initListener() {



        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Profile Profile = profiles.get(i);
                CallUpdateAndDeleteDialog(Profile.getUserid(), Profile.getName(), Profile.getAddress(), Profile.getCity(), Profile.getConNo(), Profile.getEmail(), Profile.getZipCode());


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
                ProfileList UserAdapter = new ProfileList(EditOption.this, profiles);
                listViewUsers.setAdapter(UserAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CallUpdateAndDeleteDialog(String userid, String name, String address, String city, String conNo, String email, String zipCode) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateTextname);
        final EditText updateTextaddress = (EditText) dialogView.findViewById(R.id.updateTextaddress);
        final EditText updateTextcity = (EditText) dialogView.findViewById(R.id.updateTextcity);
        final EditText updateTextmobileno = (EditText) dialogView.findViewById(R.id.updateTextmobileno);
        final EditText updateTextemail = (EditText) dialogView.findViewById(R.id.updateTextemail);
        final EditText updateTextzipcode = (EditText) dialogView.findViewById(R.id.updateTextzipcode);
        updateTextname.setText(name);
        updateTextaddress.setText(address);
        updateTextcity.setText(city);
        updateTextmobileno.setText(conNo);
        updateTextemail.setText(email);
        updateTextzipcode.setText(zipCode);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProfile);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProfile);
        dialogBuilder.setTitle(name);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updateTextname.getText().toString().trim();
                String address = updateTextaddress.getText().toString().trim();
                String city = updateTextcity.getText().toString().trim();
                String conNo = updateTextmobileno.getText().toString().trim();
                String email = updateTextemail.getText().toString().trim();
                String zipCode = updateTextzipcode.getText().toString().trim();

                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(email)) {
                        if (!TextUtils.isEmpty(conNo)) {
                            updateUser(userid, name, address, city, conNo, email, zipCode);
                            b.dismiss();
                        }
                    }
                }

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(userid);
                b.dismiss();
            }
        });
    }

    private boolean updateUser(String userid, String name, String address, String city, String conNo, String email, String zipCode) {
        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("Profile").child(userid);
        Profile Profile = new Profile(userid, name, address, city, conNo, email, zipCode);
        UpdateReference.setValue(Profile);
        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteUser(String id) {
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("Profile").child(id);
        DeleteReference.removeValue();
        Toast.makeText(getApplicationContext(), "Profile Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}