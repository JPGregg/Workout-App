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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WorkoutStartActivity extends AppCompatActivity {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Button exerciseDataSave;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_start);

        //TextView exerciseName = findViewById(R.id.currentExerciseTextView);
        EditText exerciseWeight = (findViewById(R.id.currentWeightEditText));
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
//        Toast.makeText(this, extraTableName, Toast.LENGTH_SHORT).show();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        exerciseDataSave = findViewById(R.id.buttonExerciseDataSave);
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        String newQuery = "CREATE TABLE IF NOT EXISTS "+extraTableName+" (sets INT(2), totalReps INT(2), weight INT(3), weightType TEXT, " +
                "date TEXT, comments TEXT, set1 INT(2), set2 INT(2), set3 INT(2), set4 INT(2), set5 INT(2))";
        db.execSQL(newQuery);
        exerciseDataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int set1Value = Integer.parseInt(((EditText) findViewById(R.id.editTextSet1)).getText().toString());
                    int set2Value = Integer.parseInt(((EditText) findViewById(R.id.editTextSet2)).getText().toString());
                    int set3Value = Integer.parseInt(((EditText) findViewById(R.id.editTextSet3)).getText().toString());
                    int set4Value = Integer.parseInt(((EditText) findViewById(R.id.editTextSet4)).getText().toString());
                    int set5Value = Integer.parseInt(((EditText) findViewById(R.id.editTextSet5)).getText().toString());
                    int totalRepValue = set1Value + set2Value + set3Value + set4Value + set5Value;
                    int currentWeight = Integer.parseInt(((EditText) findViewById(R.id.currentWeightEditText)).getText().toString());
                    String commentBox = ((EditText) findViewById(R.id.editTextCommentBox)).getText().toString();
                    String currentDate = ((EditText) findViewById(R.id.editTextDate)).getText().toString();

//                    Toast.makeText(WorkoutStartActivity.this, String.valueOf(totalRepValue), Toast.LENGTH_SHORT).show();    ////////////////safe to delete later. For debugging only.

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

                    long insert = db.insert(extraTableName, null, cv);    //IMPORTANT. DO NOT DELETE

//                    if (insert == -1) {             ////////////////safe to delete later. For debugging only.
//                        Toast.makeText(WorkoutStartActivity.this, "NOPE", Toast.LENGTH_SHORT).show(); ////////////////safe to delete later. For debugging only.
//                    } else {    ////////////////safe to delete later. For debugging only.
//                        Toast.makeText(WorkoutStartActivity.this, "YES", Toast.LENGTH_SHORT).show();  ////////////////safe to delete later. For debugging only.
//                    }   ////////////////safe to delete later. For debugging only.


                    ArrayList newListSet1 = new ArrayList();        ////////////////safe to delete later. For debugging only.
                    ArrayList newListTotalReps = new ArrayList();   ////////////////safe to delete later. For debugging only.
                    ArrayList newListDate = new ArrayList();        ////////////////safe to delete later. For debugging only.
                    ArrayList newListComments = new ArrayList();    ////////////////safe to delete later. For debugging only.

                    String queryStringNew = "SELECT * FROM " + extraTableName;
                    Cursor c = db.rawQuery(queryStringNew, null);
                    if (c.moveToFirst()) {
                        do {
                            newListSet1.add(c.getInt(6));
                            newListTotalReps.add(c.getInt(1));
                            newListDate.add(c.getString(4));
                            newListComments.add(c.getString(5));
                        } while (c.moveToNext());
                    }
//                    Toast.makeText(WorkoutStartActivity.this, newListSet1.toString(), Toast.LENGTH_SHORT).show();         ////////////////safe to delete later. For debugging only.
//                    Toast.makeText(WorkoutStartActivity.this, newListTotalReps.toString(), Toast.LENGTH_SHORT).show();    ////////////////safe to delete later. For debugging only.
//                    Toast.makeText(WorkoutStartActivity.this, newListDate.toString(), Toast.LENGTH_SHORT).show();         ////////////////safe to delete later. For debugging only.
//                    Toast.makeText(WorkoutStartActivity.this, newListComments.toString(), Toast.LENGTH_SHORT).show();     ////////////////safe to delete later. For debugging only.

                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(WorkoutStartActivity.this, "Missing exercise information",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}