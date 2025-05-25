package com.example.ftcstartplaner;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ftcstartplaner.R;

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

        // Çizimi bitmap olarak al
        Bitmap drawingBitmap = drawingView.getBitmap();
        String drawingPath = saveBitmapToFile(drawingBitmap);
        if (drawingPath == null) {
            Toast.makeText(this, "Failed to save drawing!", Toast.LENGTH_SHORT).show();
            return;
        }

        // DB’ye kaydet
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
            finish(); // Kaydettikten sonra geri dön
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
}
