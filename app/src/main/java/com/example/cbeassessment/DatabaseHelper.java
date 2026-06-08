package com.example.cbeassessment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CBE_App.db";
    // IMPORTANT: Increment this version from 1 to 2
    private static final int DATABASE_VERSION = 2;

    // Users Table
    private static final String TABLE_USERS = "users";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

    // Students Table (New)
    private static final String TABLE_STUDENTS = "students";
    private static final String COL_STUDENT_ID = "id";
    private static final String COL_STUDENT_NAME = "name";
    private static final String COL_ADM_NO = "admission_no";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users Table
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " TEXT UNIQUE, " + COL_PASSWORD + " TEXT)";
        db.execSQL(createUsersTable);

        // Create Students Table
        String createStudentsTable = "CREATE TABLE " + TABLE_STUDENTS + " (" +
                COL_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_STUDENT_NAME + " TEXT, " +
                COL_ADM_NO + " TEXT)";
        db.execSQL(createStudentsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        // Create tables again
        onCreate(db);
    }

    // --- EXISTING METHODS ---
    public boolean insertUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " +
                COL_EMAIL + "=? AND " + COL_PASSWORD + "=?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // --- NEW STUDENT METHODS ---
    public boolean insertStudent(String name, String admNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_STUDENT_NAME, name);
        contentValues.put(COL_ADM_NO, admNo);
        long result = db.insert(TABLE_STUDENTS, null, contentValues);
        return result != -1;
    }

    // NEW METHOD: Fetches all students to display in the List View
    public Cursor getAllStudents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);
    }
}