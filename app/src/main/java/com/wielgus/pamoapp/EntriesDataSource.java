package com.wielgus.pamoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EntriesDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_ENTRY};

    public EntriesDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Entry createEntry(String entry) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ENTRY, entry);
        long insertId = database.insert(MySQLiteHelper.TABLE_USER_ENTRIES, null, values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_USER_ENTRIES, allColumns,
        MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Entry newEntry = cursorToEntry(cursor);
        cursor.close();
        return newEntry;
    }

    public void clearEntries() {
        database.delete(MySQLiteHelper.TABLE_USER_ENTRIES, null, null);
    }

    public int getEntriesCount() {
        String countQuery = "SELECT * FROM " + MySQLiteHelper.TABLE_USER_ENTRIES;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public List<String> getEntryValues() {
        List<String> entriesList = new ArrayList<>();
        String collectQuery = "SELECT * FROM " + MySQLiteHelper.TABLE_USER_ENTRIES;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(collectQuery, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Entry entry = cursorToEntry(cursor);
            entriesList.add(entry.getEntry());
            cursor.moveToNext();
        }
        cursor.close();
        return entriesList;
    }

    private Entry cursorToEntry(Cursor cursor) {
        Entry entry = new Entry();
        entry.setId(cursor.getLong(0));
        entry.setEntry(cursor.getString(1));
        return entry;
    }

}

//    public long insertData(String entry) {
//        ContentValues content = new ContentValues();
//        content.put(ENTRY, entry);
//        return database.insertOrThrow(TABLE_NAME, null, content);
//    }
//

//
//    public void clearData() {
//        database.execSQL("DELETE * FROM " + TABLE_NAME);
//    }