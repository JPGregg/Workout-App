package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ExerciseBuildActivity extends AppCompatActivity {
    public void dbNewTableEntry(int sets, int reps, int weight, String radio, String date){
        //creates the new table
        String exerciseName = ((EditText) findViewById(R.id.exerciseNameEditText)
                              ).getText().toString();
        SQLiteDatabase db = this.openOrCreateDatabase("test1",
                                          MODE_PRIVATE, null);
        String newQuery = "CREATE TABLE IF NOT EXISTS " +exerciseName+" (sets INT(2)," +
                "totalReps INT(2), weight INT(3), weightType TEXT, date TEXT, comments TEXT," +
                "set1 INT(2), set2 INT(2), set3 INT(2), set4 INT(2), set5 INT(2))";
        db.execSQL(newQuery);

        // populates table with the provided data
        db.execSQL("INSERT INTO "+exerciseName+" (" +
                "sets,weight,weightType,date,totalReps)"+
                "VALUES ("+sets+","+weight+",'"+radio+"'," +
                "'"+date+"','"+reps+"')");
    }


    public void exerciseSaveClick(View view) {
        try {
            //get values from user
            int setsForInput = Math.min(5,Integer.parseInt(((EditText) findViewById(
                    R.id.numOfSetsEditText)).getText().toString()));
            int repsForInput = Integer.parseInt(((EditText) findViewById(
                    R.id.numOfRepsEditText)).getText().toString());
            int weightForInput = Integer.parseInt(((EditText) findViewById(
                    R.id.exerciseWeightEditText)).getText().toString());
            String tempRadioResult = "temp Pounds";
            String dateForInput = new SimpleDateFormat("MM/dd/yyyy").format(new Date());

            //save results
            dbNewTableEntry(setsForInput, repsForInput, weightForInput, tempRadioResult,
                    dateForInput);

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