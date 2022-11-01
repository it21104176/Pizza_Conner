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
    List<Payment> payments;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_option);

        findViews();

        initListener();

    }


    private void findViews() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Payment");
        listViewUsers = (ListView) findViewById(R.id.listViewProfiles);
        payments = new ArrayList<>();
    }

    private void initListener() {



        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Payment Payment = payments.get(i);
                CallUpdateAndDeleteDialog(Payment.getUserid(), Payment.getCardName(), Payment.getCardNo(), Payment.getExpDate(), Payment.getCvvCode(), Payment.getAmount(), Payment.getPizzaName());


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
                PaymentList UserAdapter = new PaymentList(EditOption.this, payments);
                listViewUsers.setAdapter(UserAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CallUpdateAndDeleteDialog(String userid, String cardName, String cardNo, String expDate, String cvvCode, String amount, String pizzaName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateTextcardname);
        final EditText updateTextcardno = (EditText) dialogView.findViewById(R.id.updateTextcardno);
        final EditText updateTextexpNo = (EditText) dialogView.findViewById(R.id.updateTextexpno);
        final EditText updateTextcvvcode = (EditText) dialogView.findViewById(R.id.updateTextcvvcode);
        final EditText updateTextamout = (EditText) dialogView.findViewById(R.id.updateTexttotalamout);
        final EditText updateTextpizzaname = (EditText) dialogView.findViewById(R.id.updateTextpizzaname);
        updateTextname.setText(cardName);
        updateTextcardno.setText(cardNo);
        updateTextexpNo.setText(expDate);
        updateTextcvvcode.setText(cvvCode);
        updateTextamout.setText(amount);
        updateTextpizzaname.setText(pizzaName);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdatePayment);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeletePayment);
        dialogBuilder.setTitle(pizzaName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = updateTextname.getText().toString().trim();
                String cardNo = updateTextcardno.getText().toString().trim();
                String expNo = updateTextexpNo.getText().toString().trim();
                String cvvCode = updateTextcvvcode.getText().toString().trim();

                if (!TextUtils.isEmpty(name)) {
                    if (!TextUtils.isEmpty(cvvCode)) {
                        if (!TextUtils.isEmpty(expNo)) {
                            updateUser(userid, name, cardNo, expNo, cvvCode, amount, pizzaName);
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

    private boolean updateUser(String userid, String name, String cardNo, String expNo, String cvvCode, String amount, String pizzaName) {
        DatabaseReference UpdateReference = FirebaseDatabase.getInstance().getReference("Payment").child(userid);
        Payment Payment = new Payment(userid, name, cardNo, expNo, cvvCode, amount, pizzaName);
        UpdateReference.setValue(Payment);
        Toast.makeText(getApplicationContext(), "Payment Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteUser(String id) {
        DatabaseReference DeleteReference = FirebaseDatabase.getInstance().getReference("Payment").child(id);
        DeleteReference.removeValue();
        Toast.makeText(getApplicationContext(), "Payment Deleted", Toast.LENGTH_LONG).show();
        return true;
    }

}