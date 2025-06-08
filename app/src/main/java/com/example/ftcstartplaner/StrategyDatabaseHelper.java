package com.example.ftcstartplaner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StrategyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "strategies.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "strategies";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_ALLIANCE = "alliance";
    public static final String COLUMN_START_POS = "start_position";
    public static final String COLUMN_END_GOAL = "end_goal";
    public static final String COLUMN_DRAWING_PATH = "drawing_path";

    public StrategyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_ALLIANCE + " TEXT, " +
                        COLUMN_START_POS + " TEXT, " +
                        COLUMN_END_GOAL + " TEXT, " +
                        COLUMN_DRAWING_PATH + " TEXT);";
        db.execSQL(CREATE_TABLE);
    }

    public void deleteStrategy(String strategyName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NAME + "=?", new String[]{strategyName});
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
