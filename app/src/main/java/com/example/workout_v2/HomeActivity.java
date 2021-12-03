package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    //These functions solely exist to move between different activities.
    public void homeDeleteClick(View view) {
        Intent intent = new Intent(getApplicationContext(), DeleteSelectionActivity.class);
        startActivity(intent);
    }


    public void homeExerciseClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ExerciseBuildActivity.class);
        startActivity(intent);
    }


    public void homeResultsClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ResultSelectionActivity.class);
        startActivity(intent);
    }


    public void homeStartClick(View view) {
        Intent intent = new Intent(getApplicationContext(), WorkoutSelectActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
