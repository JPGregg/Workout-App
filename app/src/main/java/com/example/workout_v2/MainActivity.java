package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    public void loginClick(View view) {
        String usernameCheck = ((EditText) findViewById(R.id.usernameEditText)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

        SQLiteDatabase exerciseDatabase = this.openOrCreateDatabase("test1",
                MODE_PRIVATE, null);
        exerciseDatabase.execSQL("CREATE TABLE IF NOT EXISTS loginInfo (username VARCHAR," +
                "password VARCHAR)");

        Cursor c = exerciseDatabase.rawQuery("SELECT username FROM loginInfo WHERE username='"+usernameCheck+"'", null);
        if (c.moveToPosition(0)) {

            while ( !c.isAfterLast() ) {
//                Toast.makeText(this, "Table Name=> "+c.getString(0), Toast.LENGTH_SHORT).show();
                c.moveToNext();
            }
        }


        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//        Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void loginSkip(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
//      exerciseDatabase.execSQL("INSERT INTO loginInfo (username,password)" +
//              "VALUES ('jgregg', 'password')");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}