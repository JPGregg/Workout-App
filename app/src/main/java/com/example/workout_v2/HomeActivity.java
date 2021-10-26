package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    public void homeExerciseClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ExerciseBuildActivity.class);
        startActivity(intent);
        //ExerciseBuildActivity.ExerciseBuild testForResults;
    }

//    public void homeWorkoutClick(View view) {
//        Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);
//        startActivity(intent);
//    }

    public void homeResultsClick(View view) {
        Intent intent = new Intent(getApplicationContext(), ResultSelectionActivity.class);
        startActivity(intent);
    }

//    public void homeStartClick(View view) {
//        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
//        startActivity(intent);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ////////////////////////////////////////            table deleter
//        SQLiteDatabase exerciseDatabase = this.openOrCreateDatabase("test1",
//                MODE_PRIVATE, null);
//        exerciseDatabase.execSQL("DROP TABLE IF EXISTS Bench");
        //////////////////////////////////////////
    }
}