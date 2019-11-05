package com.example.calendartestnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.simplecalendar.activities.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity
{
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, MainActivity.class)));
    }
}
