package com.example.delivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    TextView login;
    Button signup;
    EditText etname,etphone,etemail,etpswd;

    FirebaseAuth mAtuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        etname = findViewById(R.id.editTextTextPersonName);
        etphone = findViewById(R.id.editTextPhone);
        etemail = findViewById(R.id.editTextTextEmailAddress);
        etpswd = findViewById(R.id.editTextTextPassword2);
        signup = findViewById(R.id.button5);
        login = findViewById(R.id.textViewLogin);

        mAtuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,Login.class));
            }
        });
    }
    private void registerUser(){
        String name = etname.getText().toString().trim();
        String phone = etphone.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String pwd = etpswd.getText().toString().trim();

        if (email.isEmpty()) {
            etemail.setError("Email is required!");
            etemail.requestFocus();
            return;
        }
          /*  if (!Pattern.EMAIL_ADDRESS.matcher(email).matches()) {
                etemail.setError("Provide valid email!");
                etemail.requestFocus();
                return;
            }*/
        if (phone.isEmpty()) {
            etphone.setError("Phone No is required!");
            etphone.requestFocus();
            return;
        }
        if (name.isEmpty()) {
            etname.setError("Name is required!");
            etname.requestFocus();
            return;
        }

        if (pwd.isEmpty()) {
            etname.setError("Name is required!");
            etname.requestFocus();
            return;
        }
        if (pwd.length() < 6) {
            etpswd.setError("Password is required!");
            etpswd.requestFocus();
            return;
        }


        mAtuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Signin user = new Signin(name, phone, email, pwd);

                            FirebaseDatabase.getInstance().getReference("Pizza")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(Signup.this, HomePage.class));
                                        Toast.makeText(Signup.this, "Account create Successfully.", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(Signup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Signup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}