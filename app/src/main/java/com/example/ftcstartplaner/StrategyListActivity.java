package com.example.ftcstartplaner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StrategyListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StrategyAdapter adapter;
    private StrategyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_list);

        recyclerView = findViewById(R.id.recyclerViewStrategies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new StrategyDatabaseHelper(this);

        loadStrategies();
    }

    private void loadStrategies() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(StrategyDatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Strategy> strategies = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(StrategyDatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(StrategyDatabaseHelper.COLUMN_NAME));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow(StrategyDatabaseHelper.COLUMN_DESCRIPTION));
                String alliance = cursor.getString(cursor.getColumnIndexOrThrow(StrategyDatabaseHelper.COLUMN_ALLIANCE));
                String startPos = cursor.getString(cursor.getColumnIndexOrThrow(StrategyDatabaseHelper.COLUMN_START_POS));
                String endGoal = cursor.getString(cursor.getColumnIndexOrThrow(StrategyDatabaseHelper.COLUMN_END_GOAL));
                String drawingPath = cursor.getString(cursor.getColumnIndexOrThrow(StrategyDatabaseHelper.COLUMN_DRAWING_PATH));

                strategies.add(new Strategy(name, desc, alliance, startPos, endGoal, drawingPath));
            }
            cursor.close();
        }

        if (strategies.isEmpty()) {
            Toast.makeText(this, "No strategies found!", Toast.LENGTH_SHORT).show();
        }

        adapter = new StrategyAdapter(strategies);
        recyclerView.setAdapter(adapter);
    }
}
