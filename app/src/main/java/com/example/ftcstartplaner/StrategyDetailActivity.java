package com.example.ftcstartplaner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StrategyDetailActivity extends AppCompatActivity {

    private TextView tvName, tvDescription, tvAlliance, tvStartPos, tvEndGoal;
    private ImageView ivDrawing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_detail);

        tvName = findViewById(R.id.tvDetailName);
        tvDescription = findViewById(R.id.tvDetailDescription);
        tvAlliance = findViewById(R.id.tvDetailAlliance);
        tvStartPos = findViewById(R.id.tvDetailStartPos);
        tvEndGoal = findViewById(R.id.tvDetailEndGoal);
        ivDrawing = findViewById(R.id.ivDetailDrawing);

        // Intent'ten verileri alıyoruz
        Strategy strategy = (Strategy) getIntent().getSerializableExtra("strategy");

        if (strategy != null) {
            tvName.setText(strategy.name);
            tvDescription.setText(strategy.description);
            tvAlliance.setText(strategy.alliance);
            tvStartPos.setText("Start Position: " + strategy.startPosition);
            tvEndGoal.setText("End Goal: " + strategy.endGoal);

            // Alliance rengine göre arka plan rengi verelim
            switch (strategy.alliance.toLowerCase()) {
                case "red":
                    tvAlliance.setBackgroundColor(Color.RED);
                    tvAlliance.setTextColor(Color.WHITE);
                    break;
                case "blue":
                    tvAlliance.setBackgroundColor(Color.BLUE);
                    tvAlliance.setTextColor(Color.WHITE);
                    break;
                default:
                    tvAlliance.setBackgroundColor(Color.GRAY);
                    tvAlliance.setTextColor(Color.WHITE);
            }

            Bitmap bitmap = BitmapFactory.decodeFile(strategy.drawingPath);
            if (bitmap != null) {
                ivDrawing.setImageBitmap(bitmap);
            } else {
                ivDrawing.setImageResource(R.drawable.field);
            }
        }
    }
}
