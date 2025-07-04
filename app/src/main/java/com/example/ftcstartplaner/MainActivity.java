package com.example.ftcstartplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.ftcstartplaner.R;

public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnView; // Create and View Buttons
    ImageButton btnSettings;  // Settings Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connecting buttons on java
        btnCreate = findViewById(R.id.btnCreateStrategy);
        btnView = findViewById(R.id.btnViewStrategies);
        btnSettings = findViewById(R.id.btnSettings);

        AppCompatDelegate.setDefaultNightMode(
                // Tercihe göre ayarlanmalı, örnek:
                AppCompatDelegate.MODE_NIGHT_YES // veya MODE_NIGHT_NO
        );


        // When you click the crate button what is gonna happen
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateStrategyActivity.class));
            }
        });

        // When you click the View button what is gonna happen
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StrategyListActivity.class));
            }
        });

        // When you click the Settings button what is gonna happen
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ayarlar sayfasına yönlendirme
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }
}
