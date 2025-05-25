package com.example.ftcstartplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ftcstartplaner.R;

public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreate = findViewById(R.id.btnCreateStrategy);
        btnView = findViewById(R.id.btnViewStrategies);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 startActivity(new Intent(MainActivity.this, CreateStrategyActivity.class));
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Buraya yönlendirme eklenecek
                startActivity(new Intent(MainActivity.this, StrategyListActivity.class));
            }
        });
    }
}
