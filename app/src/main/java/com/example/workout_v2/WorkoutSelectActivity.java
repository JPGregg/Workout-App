package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class WorkoutSelectActivity extends AppCompatActivity {
    @SuppressLint("Range")
    public void listBuilder(ArrayList<String> exerciseList){
        SQLiteDatabase db = this.openOrCreateDatabase("test1", MODE_PRIVATE, null);
        Cursor c = db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table'", null);
        if (c.moveToPosition(2)) { //skips metadata entry in database
            while (!c.isAfterLast() ) {
                exerciseList.add( c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
        }
        c.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_select);
        ListView exerciseListView = findViewById(R.id.exerciseListView);
        ArrayList<String> exerciseList = new ArrayList<String>();
        listBuilder(exerciseList);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1, exerciseList);
        exerciseListView.setAdapter(arrayAdapter);
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), WorkoutStartActivity.class);
                intent.putExtra("tableName", exerciseList.get(i));
                startActivity(intent);
            }
        });
    }
}