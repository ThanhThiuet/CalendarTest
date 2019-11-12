package com.example.calendartestnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity
{
    private Button button, btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        button = findViewById(R.id.button);
        btnTest = findViewById(R.id.buttonTest);

        button.setOnClickListener(v -> {
            try {
                startActivity(new Intent(HomeActivity.this, Class.forName("com.example.simplecalendar.activities.MainActivity")));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        btnTest.setOnClickListener(v -> {
            try {
                startActivity(new Intent(HomeActivity.this, Class.forName
                        ("com.example.simplecalendar.test.TestCalendarActivity")));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
