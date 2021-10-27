package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_start);

        TextView exerciseName = findViewById(R.id.currentExerciseTextView);
        TextView exerciseWeight = findViewById(R.id.currentWeightTextView);
        String test = "testing";


        //Current Exercise:


        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        Toast.makeText(this, extraTableName, Toast.LENGTH_SHORT).show();

        exerciseName.setText("Current Exercise: " + extraTableName);
        exerciseWeight.setText("Weight: " + test);
    }
}