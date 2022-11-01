package com.example.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.selectBtn1);
        btn2 = findViewById(R.id.selectBtn2);
        btn3 = findViewById(R.id.selectBtn3);
        btn4 = findViewById(R.id.selectBtn4);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentForum.class);
                intent.putExtra("Price", "1050.00");
                intent.putExtra("Name", "Devilled Chicken");
                startActivity(intent);
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentForum.class);
                intent.putExtra("Price", "1100.00");
                intent.putExtra("Name", "Thandoori Chicken");
                startActivity(intent);
            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentForum.class);
                intent.putExtra("Price", "1300.00");
                intent.putExtra("Name", "Seafood");
                startActivity(intent);
            }

        });

        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PaymentForum.class);
                intent.putExtra("Price", "1050.00");
                intent.putExtra("Name", "Sausage");
                startActivity(intent);
            }

        });
    }

    public void previousOders(View view) {
        startActivity(new Intent(getApplicationContext(), EditOption.class));
    }
}