package com.wielgus.pamoapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_USER_ENTRIES = "userentries";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ENTRY = "entry";

    public static final String DATABASE_NAME = "PAMOAppDB";
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_USER_ENTRIES
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ENTRY
            + " TEXT NOT NULL);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all existing data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ENTRIES);
        onCreate(db);
    }
}
