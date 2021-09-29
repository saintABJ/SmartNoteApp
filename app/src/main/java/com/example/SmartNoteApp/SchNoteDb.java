package com.example.SmartNoteApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SchNoteDb extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "schnotesdbx";
    private static final String DATABASE_TABLE = "schnotestablex";

    private static final String NOTE_KEY_ID = "id";
    private static final String LECTURE_KEY_COURSE = "course";
    private static final String LECTURE_KEY_LECTURER = "lecturer";
    private static final String LECTURE_KEY_DEPT = "dept";
    private static final String LECTURE_KEY_LEVEL = "level";
    private static final String LECTURE_KEY_NOTE_DETAILS = "note details";
    private static final String LECTURE_KEY_DATE = "date";
    private static final String LECTURE_KEY_TIME = "time";

    SchNoteDb(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + NOTE_KEY_ID + " INTEGER PRIMARY KEY," +

                LECTURE_KEY_COURSE + " TEXT," +
                LECTURE_KEY_LECTURER + " TEXT," +
                LECTURE_KEY_DEPT + " TEXT," +
                LECTURE_KEY_LEVEL + " TEXT," +
                LECTURE_KEY_NOTE_DETAILS + "TEXT," +
                LECTURE_KEY_DATE + "TEXT," +
                LECTURE_KEY_TIME + "TEXT" + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);

    }
    public long addSchoolNote(SchoolNote schoolNote) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put(LECTURE_KEY_TIME, schoolNote.getSchoolTime());
        c.put(LECTURE_KEY_DATE, schoolNote.getSchoolDate());
        c.put(LECTURE_KEY_LECTURER, schoolNote.getLecturer());
        c.put(LECTURE_KEY_COURSE, schoolNote.getCourse());
        c.put(LECTURE_KEY_LEVEL, schoolNote.getLevel());
        c.put(LECTURE_KEY_DEPT, schoolNote.getDepartment());
        c.put(LECTURE_KEY_NOTE_DETAILS, schoolNote.getDetails());

        long ID = db.insert(DATABASE_TABLE, null, c);
        Log.d("Inserted", "ID -> " + ID);
        return ID;

    }
}



