package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

public class ExerciseBuildActivity extends AppCompatActivity {

    public class ExerciseComplete {
        int[] numOfReps;
        int weight;
        String comments;
        Date date;

        public ExerciseComplete(int[] repList, int weight, String comments) {
            numOfReps = repList;
            this.weight = weight;
            this.comments = comments;
            date = new Date();
        }

    }

    public class ExerciseBuild {
        int exerciseID;
        String exerciseName;
        int weightType;
        int numOfSets;
        ArrayList<ExerciseComplete> exerciseComplete = new ArrayList<ExerciseComplete>();

        public ExerciseBuild(int ID, String exerciseName, int weightType, int sets) {
            exerciseID = ID;
            this.exerciseName = exerciseName;
            this.weightType = weightType;
            numOfSets = sets;
        }

    }

    public void exerciseSaveClick(View view) {

        ExerciseBuild test = new ExerciseBuild(1, "test", 1, 1); //delete later. Just using as template for other .xml files

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_build);
    }
}