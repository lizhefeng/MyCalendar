package com.example.zhefengli.mycalendar;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by zhefengli on 3/7/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "remindersManager";

    private static final String TABLE_REMINDERS = "reminders";

    private static final String KEY_DATE = "date";
    private static final String KEY_TITLE = "title";
    private static final String KEY_MEMO = "memo";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REMINDERS_TABLE = "CREATE TABLE " + TABLE_REMINDERS + "("
                + KEY_DATE + " TEXT," + KEY_TITLE + " TEXT," + KEY_MEMO + " TEXT" + ")";
        db.execSQL(CREATE_REMINDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDERS);

        // Create tables again
        onCreate(db);
    }

    public void addReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, reminder.getDate()); // Date
        values.put(KEY_TITLE, reminder.getTitle()); // Title
        values.put(KEY_MEMO, reminder.getMemo()); // Memo

        // Inserting Row
        db.insert(TABLE_REMINDERS, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<Reminder> getAllReminders() {
        ArrayList<Reminder> reminderList = new ArrayList<Reminder>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_REMINDERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Reminder reminder = new Reminder();
                reminder.setDate(cursor.getString(0));
                reminder.setTitle(cursor.getString(1));
                reminder.setMemo(cursor.getString(2));
                // Adding contact to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }

        // return contact list
        return reminderList;
    }
}
