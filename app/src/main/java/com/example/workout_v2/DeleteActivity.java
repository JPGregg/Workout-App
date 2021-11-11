package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class DeleteActivity extends AppCompatActivity {

    public void backHomeClick (View view) {
        Intent intentHome = new Intent(DeleteActivity.this, HomeActivity.class);
        startActivity(intentHome);
    }

    public void deleteExerciseClick (View view) {
        Intent intent = getIntent();
        Intent intentHome = new Intent(DeleteActivity.this, HomeActivity.class);
        String extraTableName = intent.getStringExtra("tableName");

        //I have the table name, now we need to delete it.
        SQLiteDatabase exerciseDatabase = this.openOrCreateDatabase("test1",
                MODE_PRIVATE, null);
        exerciseDatabase.execSQL("DROP TABLE IF EXISTS "+extraTableName);
        startActivity(intentHome);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
    }
}
