package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ResultViewActivity extends AppCompatActivity {

    class DownloadTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream input = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        //return null; //delete this later. Needed a return statement when I commented out the try/catch.
        }

//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            Log.i("JSON:",s);
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        //Toast.makeText(this, extraTableName, Toast.LENGTH_SHORT).show();


        //Get request to program on computer. Same as from homework assignment.
        //Send GET request to ??? with same info in section below.

        //resultsTextData section
        //Grab info from table extraTableName
        //Need exCount, totalReps, Weight, Date.
        //4 columns total.

        DownloadTask task = new DownloadTask();
        task.execute("http://127.0.0.1:5000/helloworld");

//        class GraphTask extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected String doInBackground(String... urls) {
//
//                String newResult = "";
//                URL url;
//                HttpURLConnection urlConnection = null;
//                try {
//                    url = new URL(urls[0]);
//                    urlConnection = (HttpURLConnection) url.openConnection();
////                    InputStream input = urlConnection.getInputStream();
////                    InputStreamReader reader = new InputStreamReader(input);
////                    reader.read();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//
//                //Log.i("URL",strings[0]);
//                return "Done";
//            }
//        }
//
//        GraphTask task = new GraphTask();
//        String result;
//        try {
//            result = task.execute("http://127.0.0.1:5000/").get();
//            Log.i("Connected:",result);
//        } catch (Exception e){
//            e.printStackTrace();
//            Log.i("Not connected", "Connection failed.");
//        }

        //resultsTextData

    }
}