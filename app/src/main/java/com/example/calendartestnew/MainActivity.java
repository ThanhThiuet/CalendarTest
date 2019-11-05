package com.example.calendartestnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            try {
                startActivity(new Intent(MainActivity.this, Class.forName("com.example.simplecalendar.activities.SplashActivity")));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
