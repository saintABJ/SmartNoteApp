package com.example.SmartNoteApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabase extends SQLiteOpenHelper {
    //notesdbx
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "notesdbx";
    private static final String DATABASE_TABLE = "notestablex";
//    private static final String DATABASE_TABLE2 = "schoolnotetable";


    // columns name for database table
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";



    //    columns name for database table2
//    private static final String LECTURE_KEY_ID = "id";
//    private static final String LECTURE_KEY_COURSE = "course";
//    private static final String LECTURE_KEY_LECTURER = "lecturer";
//    private static final String LECTURE_KEY_DEPT = "dept";
//    private static final String LECTURE_KEY_LEVEL = "level";
//    private static final String LECTURE_KEY_NOTE_DETAILS = "note details";
//    private static final String LECTURE_KEY_DATE = "date";
//    private static final String LECTURE_KEY_TIME = "time";

    NoteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE tablename(id INT PRIMARY KEY. title TEXT, content TEXT, date TEXT, time TEXT);
        String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY," +

                KEY_TITLE + " TEXT," +
                KEY_CONTENT + " TEXT," +
                KEY_DATE + " TEXT," +
                KEY_TIME + " TEXT" + ")";

//        String querytable2 = "CREATE TABLE " + DATABASE_TABLE2 + "(" + LECTURE_KEY_ID + " INTEGER PRIMARY KEY," +
//
//                LECTURE_KEY_COURSE + " TEXT," +
//                LECTURE_KEY_LECTURER + " TEXT," +
//                LECTURE_KEY_DEPT + " TEXT," +
//                LECTURE_KEY_LEVEL + " TEXT," +
//                LECTURE_KEY_NOTE_DETAILS + "TEXT," +
//                LECTURE_KEY_DATE + "TEXT," +
//                LECTURE_KEY_TIME + "TEXT" + ")";

        db.execSQL(query);
//        db.execSQL(querytable2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE2);
        onCreate(db);

    }

    public long addNote(Note note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();

        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CONTENT, note.getContent());
        c.put(KEY_DATE, note.getDate());
        c.put(KEY_TIME, note.getTime());

        long ID = db.insert(DATABASE_TABLE, null, c);
        Log.d("Inserted", "ID -> " + ID);
        return ID;
    }

//    public long addSchoolNote(SchoolNote schoolNote) {
//
//        SQLiteDatabase dbs = this.getWritableDatabase();
//        ContentValues c = new ContentValues();
//
//        c.put(LECTURE_KEY_TIME, schoolNote.getSchoolTime());
//        c.put(LECTURE_KEY_DATE, schoolNote.getSchoolDate());
//        c.put(LECTURE_KEY_LECTURER, schoolNote.getLecturer());
//        c.put(LECTURE_KEY_COURSE, schoolNote.getCourse());
//        c.put(LECTURE_KEY_LEVEL, schoolNote.getLevel());
//        c.put(LECTURE_KEY_DEPT, schoolNote.getDepartment());
//
//        long ID = dbs.insert(DATABASE_TABLE2, null, c);
//        Log.d("Inserted", "ID -> " + ID);
//        return ID;
//
//    }


    public Note getNote(long id) {
        // select * from databaseTable where id=1
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(DATABASE_TABLE,new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME}, KEY_ID+"=?",
                    new String[]{String.valueOf(id)}, null, null, null);

            if (cursor != null)
                cursor.moveToFirst();


        return new Note(cursor.getLong(0),cursor.getString(1),cursor.getString(2),
                cursor.getString(3),cursor.getString(4));


    }

    public List<Note> getnotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Note> allNotes = new ArrayList<>();
        // select * from databaseName

        String query = "SELECT * FROM "+ DATABASE_TABLE+" ORDER BY "+KEY_ID+" DESC";
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setID(cursor.getLong(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setTime(cursor.getString(4));

                allNotes.add(note);

            }while (cursor.moveToNext());

        }

        return allNotes;

    }

    public int editNote (Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> " + note.getTitle() + "\n ID -> " + note.getID());
        c.put(KEY_TITLE, note.getTitle());
        c.put(KEY_CONTENT, note.getContent());
        c.put(KEY_DATE, note.getDate());
        c.put(KEY_TIME, note.getTime());
        return db.update(DATABASE_TABLE, c, KEY_ID + "=?", new String[]{String.valueOf(note.getID())});

    }

    void deleteNote (long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_ID+"=?", new String[]{String.valueOf(id)});
        db.close();
    }


}