package com.example.ftcstartplaner;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.FileOutputStream;

public class CreateStrategyActivity extends AppCompatActivity {

    private EditText etName, etDesc, etAlliance, etStartPos, etEndGoal;
    private DrawingView drawingView;
    private StrategyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_strategy);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Strategy");

        Button btnColorBlack = findViewById(R.id.btnColorBlack);
        Button btnColorRed = findViewById(R.id.btnColorRed);
        Button btnColorBlue = findViewById(R.id.btnColorBlue);


        // View bağlantıları
        etName = findViewById(R.id.etStrategyName);
        etDesc = findViewById(R.id.etDescription);
        etAlliance = findViewById(R.id.etAlliance);
        etStartPos = findViewById(R.id.etStartPos);
        etEndGoal = findViewById(R.id.etEndGoal);
        drawingView = findViewById(R.id.drawingView);
        Button btnClear = findViewById(R.id.btnClearDrawing);
        Button btnSave = findViewById(R.id.btnSaveStrategy);

        dbHelper = new StrategyDatabaseHelper(this);

        btnClear.setOnClickListener(v -> drawingView.clear());
        btnSave.setOnClickListener(v -> saveStrategy());


        btnColorBlack.setOnClickListener(v -> {

            drawingView.setPaintColor(Color.BLACK);
        });

        btnColorRed.setOnClickListener(v -> {

            drawingView.setPaintColor(Color.RED);
        });

        btnColorBlue.setOnClickListener(v -> {

            drawingView.setPaintColor(Color.BLUE);
        });

    }

    private void saveStrategy() {
        String name = etName.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();
        String alliance = etAlliance.getText().toString().trim();
        String startPos = etStartPos.getText().toString().trim();
        String endGoal = etEndGoal.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Strategy name required!", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap drawingBitmap = drawingView.getBitmap();
        String drawingPath = saveBitmapToFile(drawingBitmap);
        if (drawingPath == null) {
            Toast.makeText(this, "Failed to save drawing!", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(StrategyDatabaseHelper.COLUMN_NAME, name);
        values.put(StrategyDatabaseHelper.COLUMN_DESCRIPTION, desc);
        values.put(StrategyDatabaseHelper.COLUMN_ALLIANCE, alliance);
        values.put(StrategyDatabaseHelper.COLUMN_START_POS, startPos);
        values.put(StrategyDatabaseHelper.COLUMN_END_GOAL, endGoal);
        values.put(StrategyDatabaseHelper.COLUMN_DRAWING_PATH, drawingPath);

        long id = dbHelper.getWritableDatabase().insert(StrategyDatabaseHelper.TABLE_NAME, null, values);

        if (id > 0) {
            Toast.makeText(this, "Strategy saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error saving strategy!", Toast.LENGTH_SHORT).show();
        }
    }

    private String saveBitmapToFile(Bitmap bitmap) {
        String filename = "strategy_" + System.currentTimeMillis() + ".png";
        File file = new File(getFilesDir(), filename);
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
