package com.example.SmartNoteApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmConfiguration;

public class AddSchoolNote extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    Toolbar toolbar;
    EditText schCourse, schLecturer, schDept, schLvl, schNoteDetails;
    Calendar c;
    String todaysDate, currentTime;
    Realm myRealm;
    RealmAsyncTask realmAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_school_note);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New School Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        schCourse = findViewById(R.id.courseTitle);
        schLecturer = findViewById(R.id.schoolLecturer);
        schDept = findViewById(R.id.dept);
        schLvl = findViewById(R.id.lvl);
        schNoteDetails = findViewById(R.id.schoolNoteDetails);

//        myRealm = Realm.getDefaultInstance();

        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));

        Log.d("calender", "Date and Time: " + todaysDate + " and " + currentTime);
    }

    private String pad(int i) {
        if (i < 10)
            return "0" + i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            if (schCourse.getText().length() != 0) {
            SchoolNote schoolNote = new SchoolNote(schCourse.getText().toString(),
                        schLecturer.getText().toString(), schDept.getText().toString(),
                        schLvl.getText().toString(), schNoteDetails.getText().toString(),
                        todaysDate,currentTime);
            SchNoteDb db = new SchNoteDb(this);
            db.addSchoolNote(schoolNote);

                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();

                goToMain();

            }else {
                schCourse.setError("Course cannot be blank");
            }
        }else if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Note Not Saved", Toast.LENGTH_SHORT).show();
            onBackPressed();


            schCourse.setText(null);
            schLecturer.setText(null);
            schDept.setText(null);
            schLvl.setText(null);
            schNoteDetails.setText(null);

 return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);

//    private void goToMain() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
