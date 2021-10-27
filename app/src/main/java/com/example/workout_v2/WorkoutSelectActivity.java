package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_select);

        SQLiteDatabase exerciseDatabase = this.openOrCreateDatabase("test1",
                MODE_PRIVATE, null);

        ListView exerciseListView = findViewById(R.id.exerciseListView);
        ArrayList<String> exerciseList = new ArrayList<String>();
        Cursor c = exerciseDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToPosition(2)) { //should skip metadata
            while (!c.isAfterLast() ) {
                exerciseList.add( c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, exerciseList);
        exerciseListView.setAdapter(arrayAdapter);

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), WorkoutStartActivity.class);
            }
        });

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