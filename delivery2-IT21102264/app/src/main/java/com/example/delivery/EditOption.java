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

    EditText editTextAddress, editTextPhoneNumber, editTextName, editTextEmail;
    ListView listViewUsers;
    List<Delivery> deliveries;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_option);

        findViews();

        initListener();

    }


    private void findViews() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Delivery");

        editTextAddress = (EditText) findViewById(R.id.edit_address);
        editTextPhoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);
        editTextName = (EditText) findViewById(R.id.edit_name);
        editTextEmail = (EditText) findViewById(R.id.edit_email);
        listViewUsers = (ListView) findViewById(R.id.listViewDeliverys);
        deliveries = new ArrayList<>();
    }

    private void initListener() {



        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Delivery Delivery = deliveries.get(i);
                CallUpdateAndDeleteDialog(Delivery.getUserid(), Delivery.getName(), Delivery.getAddress(), Delivery.getConNo(), Delivery.getEmail());


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                deliveries.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Delivery Delivery = postSnapshot.getValue(Delivery.class);
                    deliveries.add(Delivery);
                }
                DeliveryList UserAdapter = new DeliveryList(EditOption.this, deliveries);
                listViewUsers.setAdapter(UserAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CallUpdateAndDeleteDialog(final String userid, String username, final String email, String monumber, String location) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateTextname);
        final EditText updateTextaddress = (EditText) dialogView.findViewById(R.id.updateTextaddress);
        final EditText updateTextmobileno = (EditText) dialogView.findViewById(R.id.updateTextmobileno);
        final EditText updateTextemail = (EditText) dialogView.findViewById(R.id.updateTextemail);
        updateTextname.setText(username);
        updateTextaddress.setText(email);
        updateTextmobileno.setText(monumber);
        updateTextemail.setText(location);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateDelivery);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteDelivery);
        dialogBuilder.setTitle(username);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updateTextname.getText().toString().trim();
                String address = updateTextaddress.getText().toString().trim();
                String conNo = updateTextmobileno.getText().toString().trim();
                String email = updateTextemail.getText().toString().trim();

                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(email)) {
                        if (!TextUtils.isEmpty(conNo)) {
                            updateUser(userid, name, address, conNo, email);
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

    private boolean updateUser(String id, String name, String address, String conNo, String email) {
        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("Delivery").child(id);
        Delivery Delivery = new Delivery(id, name, address, conNo, email);
        UpdateReference.setValue(Delivery);
        Toast.makeText(getApplicationContext(), "Delivery Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteUser(String id) {
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("Delivery").child(id);
        DeleteReference.removeValue();
        Toast.makeText(getApplicationContext(), "Delivery Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}