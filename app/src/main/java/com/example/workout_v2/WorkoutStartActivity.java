package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkoutStartActivity extends AppCompatActivity {
    Button exerciseDataSave;

    public List<Integer> colInfoInt(int colNum) {
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        List<Integer> listInfo = new ArrayList<>();
        String query = "SELECT * FROM " + extraTableName;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                listInfo.add(cursor.getInt(colNum));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listInfo;
    }

    public int setValue(int setNum) {
        int returnVal;
        if (((EditText) findViewById(setNum)).getText().toString().equals("")) {
            returnVal = 0;
        } else {
            returnVal = Integer.parseInt(((EditText) findViewById(setNum)).getText().toString());
        }
        return returnVal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_start);
        EditText exerciseWeight = (findViewById(R.id.currentWeightEditText));
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        exerciseDataSave = findViewById(R.id.buttonExerciseDataSave);
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        String newQuery = "CREATE TABLE IF NOT EXISTS "+extraTableName+" (sets INT(2), totalReps INT(2), weight INT(3), weightType TEXT, " +
                "date TEXT, comments TEXT, set1 INT(2), set2 INT(2), set3 INT(2), set4 INT(2), set5 INT(2))";
        db.execSQL(newQuery);

        List<Integer> setCountList = new ArrayList<>();
        setCountList = colInfoInt(0);
        int setCount = setCountList.get(0);
        //Toast.makeText(WorkoutStartActivity.this, Integer.toString(setCount), Toast.LENGTH_SHORT).show();
        List<Integer> setETValues = Arrays.asList(R.id.editTextSet1, R.id.editTextSet2,
                R.id.editTextSet3, R.id.editTextSet4, R.id.editTextSet5);
        if (setCount < 5) {
            for(int i = 5; i > setCount; i--) {
                findViewById(setETValues.get(i-1)).setVisibility(View.INVISIBLE);
            }
        }

        exerciseDataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int set1Value = setValue(R.id.editTextSet1);
                    int set2Value = setValue(R.id.editTextSet2);
                    int set3Value = setValue(R.id.editTextSet3);
                    int set4Value = setValue(R.id.editTextSet4);
                    int set5Value = setValue(R.id.editTextSet5);
                    int totalRepValue = set1Value + set2Value + set3Value + set4Value + set5Value;
                    int currentWeight = Integer.parseInt(((EditText) findViewById(
                            R.id.currentWeightEditText)).getText().toString());
                    String commentBox = ((EditText) findViewById(
                            R.id.editTextCommentBox)).getText().toString();
                    String currentDate = ((EditText) findViewById(
                            R.id.editTextDate)).getText().toString();

                    ContentValues cv = new ContentValues();
                    cv.put("set1", set1Value); //set1
                    cv.put("set2", set2Value); //set2
                    cv.put("set3", set3Value); //set3
                    cv.put("set4", set4Value); //set4
                    cv.put("set5", set5Value); //set5
                    cv.put("totalReps", totalRepValue); //totalReps
                    cv.put("weight", currentWeight); //weight
                    cv.put("date", currentDate); //date
                    cv.put("comments", commentBox); //comments
                    db.insert(extraTableName, null, cv);

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(WorkoutStartActivity.this,
                            "Missing exercise information",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}


