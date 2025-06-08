package com.example.ftcstartplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ftcstartplaner.R;

public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnView;
    ImageButton btnSettings;  // Ayarlar butonu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreateStrategy);
        btnView = findViewById(R.id.btnViewStrategies);
        btnSettings = findViewById(R.id.btnSettings);  // Ayarlar butonu bağlantısı

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateStrategyActivity.class));
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, StrategyListActivity.class));
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ayarlar sayfasına yönlendirme
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });
    }
}
