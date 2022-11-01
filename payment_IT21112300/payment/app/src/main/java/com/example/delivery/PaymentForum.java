package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PaymentForum extends AppCompatActivity {
    TextView totalPayment;
    String price, pizzaName;
    EditText editTextCardName, editTextCardNo, editTextCVVNo, editTextExpDate;
    Button buttonAddUser;
    ListView listViewProfiles;
    List<Payment> payments;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_forum);

        price = getIntent().getStringExtra("Price");
        pizzaName = getIntent().getStringExtra("Name");
        totalPayment = (TextView) findViewById(R.id.textVewTotalPayment);
        totalPayment.setText("Total Payment : Rs. "+price);

        findViews();

        initListener();

    }

    private void findViews() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Payment");

        editTextCardName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextCardNo = (EditText) findViewById(R.id.editTextTextPersonName2);
        editTextExpDate = (EditText) findViewById(R.id.editTextTextPersonName3);
        editTextCVVNo = (EditText) findViewById(R.id.editTextTextPersonName5);
        listViewProfiles = (ListView) findViewById(R.id.listViewProfiles);
        buttonAddUser = (Button) findViewById(R.id.confirm_button);
        payments = new ArrayList<>();
    }

    private void initListener() {
        buttonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                payments.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Payment Payment = postSnapshot.getValue(Payment.class);
                    payments.add(Payment);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    private void addUser() {

        String CardName = editTextCardName.getText().toString().trim();
        String CardNo = editTextCardNo.getText().toString().trim();
        String ExpDate = editTextExpDate.getText().toString().trim();
        String CVVNo = editTextCVVNo.getText().toString().trim();

        if (TextUtils.isEmpty(CardName)){
            Toast.makeText(getApplicationContext(), "Please Enter Card Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(CardNo)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Card Number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ExpDate)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Card Exp. Date", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(CVVNo)){
            Toast.makeText(getApplicationContext(), "Please Enter Your Contact Number", Toast.LENGTH_SHORT).show();
        } else {
            String id = databaseReference.push().getKey();
            Payment Payment = new Payment(id, CardName, CardNo, ExpDate, CVVNo, price, pizzaName);
            databaseReference.child(id).setValue(Payment);

            editTextCardName.setText("");
            editTextCVVNo.setText("");
            editTextCardNo.setText("");
            editTextExpDate.setText("");
            totalPayment.setText("Total Payment : ");
            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_LONG).show();
        }

    }

}