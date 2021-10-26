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

//    public class ExerciseComplete {
//        int[] numOfReps;
//        int weight;
//        String comments;
//        Date date;
//        public ExerciseComplete(int[] repList, int weight, String comments) {
//            numOfReps = repList;
//            this.weight = weight;
//            this.comments = comments;
//            date = new Date();
//        }
//    }

//    public class ExerciseBuild {
//        int exerciseID;
//        String exerciseName;
//        int weightType;
//        int numOfSets;
//        ArrayList<ExerciseComplete> exerciseCompleteArray = new ArrayList<ExerciseComplete>();
//        public ExerciseBuild(int ID, String exerciseName, int weightType, int sets) {
//            exerciseID = ID;
//            this.exerciseName = exerciseName;
//            this.weightType = weightType;
//            numOfSets = sets;
//        }
//
//    }

    public void exerciseSaveClick(View view) {

        try {
            //this.deleteDatabase("test1.db");
            String exerciseName = ((EditText) findViewById(R.id.exerciseNameEditText)).getText().toString();
            SQLiteDatabase exerciseDatabase = this.openOrCreateDatabase("test1",
                    MODE_PRIVATE, null);
            exerciseDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+exerciseName+" (exCount INT PRIMARY KEY," +
                    "sets INT(2), totalReps INT(2), weight INT(3), weightType VARCHAR, date VARCHAR," +
                    "comments VARCHAR, set1 INT(2), set2 INT(2), set3 INT(2), set4 INT(2), set5 INT(2)," +
                    "set6 INT(2), set7 INT(2), set8 INT(2), set9 INT(2), set10 INT(2))");

            int setsForInput = Math.min(10,Integer.parseInt(((EditText) findViewById(R.id.numOfSetsEditText)).getText().toString()));
            int repsForInput = Integer.parseInt(((EditText) findViewById(R.id.numOfRepsEditText)).getText().toString());
            int weightForInput = Integer.parseInt(((EditText) findViewById(R.id.exerciseWeightEditText)).getText().toString());
            String tempRadioResult = "temp Pounds";
            String dateForInput = "10-25-2021";
            String commentsForInput = "blah blah";

//            Cursor cd= exerciseDatabase.rawQuery("SELECT * FROM newExercise", null);
//            int setInfo = cd.getColumnIndex("sets");
//            cd.moveToFirst();
//            while (!cd.isAfterLast()) {
//                Log.i("sets",Integer.toString(cd.getInt(setInfo)));
//                cd.moveToNext();
//            }

            exerciseDatabase.execSQL("INSERT INTO "+exerciseName+" (sets,weight,weightType,date,comments)"+
                    "VALUES ("+setsForInput+","+weightForInput+",'"+tempRadioResult+"'," +
                    "'"+dateForInput+"','"+dateForInput+"')");

            Cursor c = exerciseDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
            if (c.moveToPosition(1)) {
                while ( !c.isAfterLast() ) {
                    Toast.makeText(this, "Table Name=> "+c.getString(0), Toast.LENGTH_SHORT).show();
                    c.moveToNext();
                }
            }
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_build);
    }
}