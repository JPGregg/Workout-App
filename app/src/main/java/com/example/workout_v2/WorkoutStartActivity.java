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
    public List<Integer> colInfoInt(int colNum) {
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        List<Integer> listInfo = new ArrayList<>();
        String query = "SELECT * FROM " + extraTableName;
        Cursor c = db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                listInfo.add(c.getInt(colNum));
            } while (c.moveToNext());
        }
        c.close();
        return listInfo;
    }

    //acts as failsafe for a blank set entry.
    public int setValue(int setNum) {
        int returnVal;
        if (((EditText) findViewById(setNum)).getText().toString().equals("")) {
            returnVal = 0;
        } else {
            returnVal = Integer.parseInt(((EditText) findViewById(setNum)).getText().toString());
        }
        return returnVal;
    }

    //saves user values into database
    public void saveValues(SQLiteDatabase db, String extraTableName){
        ContentValues cv = new ContentValues();
        cv.put("set1", setValue(R.id.editTextSet1));
        cv.put("set2", setValue(R.id.editTextSet2));
        cv.put("set3", setValue(R.id.editTextSet3));
        cv.put("set4", setValue(R.id.editTextSet4));
        cv.put("set5", setValue(R.id.editTextSet5));
        cv.put("totalReps", setValue(R.id.editTextSet1) + setValue(R.id.editTextSet2) +
                setValue(R.id.editTextSet3) + setValue(R.id.editTextSet4) +
                setValue(R.id.editTextSet5));
        cv.put("weight", Integer.parseInt(((EditText) findViewById(
                R.id.currentWeightEditText)).getText().toString()));
        cv.put("date",((EditText)findViewById(
                R.id.editTextDate)).getText().toString());
        cv.put("comments", ((EditText) findViewById(
                R.id.editTextCommentBox)).getText().toString());
        db.insert(extraTableName, null, cv);
    }

    //sets visibility of the sets
    public void setVisibility(){
        List<Integer> setCountList;
        setCountList = colInfoInt(0);
        int setCount = setCountList.get(0);
        List<Integer> setETValues = Arrays.asList(R.id.editTextSet1, R.id.editTextSet2,
                R.id.editTextSet3, R.id.editTextSet4, R.id.editTextSet5);
        if (setCount < 5) {
            for(int i = 5; i > setCount; i--) {
                findViewById(setETValues.get(i-1)).setVisibility(View.INVISIBLE);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_start);
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        Button exerciseDataSave = findViewById(R.id.buttonExerciseDataSave);
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        String newQuery = "CREATE TABLE IF NOT EXISTS "+extraTableName+" (sets INT(2)," +
                "totalReps INT(2), weight INT(3), weightType TEXT, date TEXT, comments TEXT," +
                "set1 INT(2), set2 INT(2), set3 INT(2), set4 INT(2), set5 INT(2))";
        db.execSQL(newQuery);
        setVisibility();

        exerciseDataSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //save values from the user into database
                    saveValues(db, extraTableName);
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
