package com.example.SmartNoteApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class Edit extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteDetails;
    Calendar c;
    String todaysDate, currentTime;
    NoteDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        Intent i = getIntent();
        Long id = i.getLongExtra("ID",0);
            db = new NoteDatabase(this);
            note = db.getNote(id);



        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(note.getTitle());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteTitle = findViewById(R.id.courseTitle);
        noteDetails = findViewById(R.id.noteDetails);

        noteTitle.setText(note.getTitle());
        noteDetails.setText(note.getContent());

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //get current time and date
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
            if (noteTitle.getText().length() != 0) {
                note.setTitle(noteTitle.getText().toString());
                note.setContent(noteDetails.getText().toString());

                int id = db.editNote(note);
                if (id==note.getID()) {
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(this, "Error Updating Note", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Details.class);
                intent.putExtra("ID", note.getID());
                goToMain();

            }else {
                noteTitle.setError("Title cannot be blank");
            }

        }else if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

