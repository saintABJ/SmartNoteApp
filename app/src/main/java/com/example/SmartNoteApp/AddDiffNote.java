package com.example.SmartNoteApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AddDiffNote extends AppCompatActivity {

    Button lecture_btn, sermon_btn, other_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diff_note);


            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            lecture_btn = findViewById(R.id.lecture_btn);
            sermon_btn = findViewById(R.id.sermon_btn);
            other_btn = findViewById(R.id.other_btn);

            other_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), AddNote.class);
                    startActivity(i);
                }
            });
            lecture_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), AddSchoolNote.class);
                    startActivity(i);

                }
            });
        }

    }
