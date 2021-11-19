package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class ExerciseBuildActivity extends AppCompatActivity {

    public void exerciseSaveClick(View view) {

        //attempts to build out a new table in the database.
        try {
            String exerciseName = ((EditText) findViewById(R.id.exerciseNameEditText)).getText().toString();
            SQLiteDatabase exerciseDatabase = this.openOrCreateDatabase("test1",
                    MODE_PRIVATE, null);
            String newQuery = "CREATE TABLE IF NOT EXISTS " +exerciseName+" (sets INT(2), totalReps INT(2), weight INT(3), weightType TEXT," +
                    "date TEXT, comments TEXT, set1 INT(2), set2 INT(2), set3 INT(2), set4 INT(2), set5 INT(2))";
            exerciseDatabase.execSQL(newQuery);

            int setsForInput = Math.min(5,Integer.parseInt(((EditText) findViewById(R.id.numOfSetsEditText)).getText().toString()));
            int repsForInput = Integer.parseInt(((EditText) findViewById(R.id.numOfRepsEditText)).getText().toString());
            int weightForInput = Integer.parseInt(((EditText) findViewById(R.id.exerciseWeightEditText)).getText().toString());
            String tempRadioResult = "temp Pounds";
            String dateForInput = "11/11/2021";
            //String commentsForInput = "low priority item";

            //This creates the new table in the database. It then populates it with the provided data.
            exerciseDatabase.execSQL("INSERT INTO "+exerciseName+" (sets,weight,weightType,date,comments,totalReps)"+
                    "VALUES ("+setsForInput+","+weightForInput+",'"+tempRadioResult+"'," +
                    "'"+dateForInput+"','"+dateForInput+"','"+repsForInput+"')");

            Cursor c = exerciseDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            if (c.moveToPosition(1)) {
                while ( !c.isAfterLast() ) {
                    c.moveToNext();
                }
            }
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_build);
    }
}