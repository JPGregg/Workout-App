package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class ResultViewActivity extends AppCompatActivity {

    public void volleyPost(){
        String url = "https://graph-microservice.herokuapp.com/";

        String base = "http://127.0.0.1:5000/";



        //JSONObject postData = new JSONObject();
//        try {
////            postData.put("filename","newimage2");
////            postData.put("dpi",300;
////            postData.put("format","line");
////            postData.put("output","PNG");
////            postData.put("filename","graph");
////            postData.put("title","Test Graph");
////            postData.put("xlabel","X");
////            postData.put("ylabel","Y");
////            postData.put("legend",true);
////            postData.put("grid",false);
////            postData.put("entries",[1,2,3,4,5]);
//                    //([[[1,2,3,4,5], [7,4,2,8,9], "r", "line1"], [[2,5,7,8,9], [-10,2,5,9,-3], "b", "line2"]]));
//              postData.
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

//    String url = "https://graph-mocroservice.herokuapp.com/";
//    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response ->
//            Toast.makeText(ResultViewActivity.this, "Success", Toast.LENGTH_SHORT).show(),
//            error -> Toast.makeText(ResultViewActivity.this, "Error",
//                    Toast.LENGTH_SHORT).show()){
//        //add parameters to the request
//        @Override
//        protected Map
//    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);

        addtable();
    }

    public void addtable() {
        TableLayout tbl = (TableLayout) findViewById(R.id.exerciseTable);
        TableRow tblRow = (new TableRow(this));

        TextView header0 = new TextView(this);
        header0.setText("#");
        tblRow.addView(header0);

        TextView header1 = new TextView(this);
        header1.setText("Date");
        tblRow.addView(header1);

        TextView header2 = new TextView(this);
        header2.setText("Weight");
        tblRow.addView(header2);

        TextView header3 = new TextView(this);
        header3.setText("Total Reps");
        tblRow.addView(header3);

        tbl.addView(tblRow);
        for (int i = 0; i < 30; i++) {
            TableRow tblRow2 = new TableRow(this);
            TextView nextCell0 = new TextView(this);
            nextCell0.setText(" " + (i+1));
            tblRow2.addView(nextCell0);

            TextView nextCell1 = new TextView(this);
            nextCell1.setText("blah");
            tblRow2.addView(nextCell1);

            TextView nextCell2 = new TextView(this);
            nextCell2.setText("blah");
            tblRow2.addView((nextCell2));

            TextView nextCell3 = new TextView(this);
            nextCell3.setText("blah");
            tblRow2.addView((nextCell3));

            tbl.addView(tblRow2);
        }






    }
}









//
//
//    class DownloadTask extends AsyncTask<String,Void,String> {
//
//        @Override
//        protected String doInBackground(String... urls) {
//            String result = "";
//            URL url;
//            HttpURLConnection urlConnection = null;
//
//            try {
//                url = new URL(urls[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream input = urlConnection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(input);
//                int data = reader.read();
//
//                while (data != -1) {
//                    char current = (char) data;
//                    result += current;
//                    data = reader.read();
//                }
//                return result;
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        //return null; //delete this later. Needed a return statement when I commented out the try/catch.
//        }
//
////        @Override
////        protected void onPostExecute(String s) {
////            super.onPostExecute(s);
////
////            Log.i("JSON:",s);
////        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_result_view);
//
//        Intent intent = getIntent();
//        String extraTableName = intent.getStringExtra("tableName");
//        //Toast.makeText(this, extraTableName, Toast.LENGTH_SHORT).show();
//
//
//        //Get request to program on computer. Same as from homework assignment.
//        //Send GET request to ??? with same info in section below.
//
//        //resultsTextData section
//        //Grab info from table extraTableName
//        //Need exCount, totalReps, Weight, Date.
//        //4 columns total.
//
//        DownloadTask task = new DownloadTask();
//        task.execute("http://127.0.0.1:5000/helloworld");
//
////        class GraphTask extends AsyncTask<String, Void, String> {
////
////            @Override
////            protected String doInBackground(String... urls) {
////
////                String newResult = "";
////                URL url;
////                HttpURLConnection urlConnection = null;
////                try {
////                    url = new URL(urls[0]);
////                    urlConnection = (HttpURLConnection) url.openConnection();
//////                    InputStream input = urlConnection.getInputStream();
//////                    InputStreamReader reader = new InputStreamReader(input);
//////                    reader.read();
////
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////
////
////                //Log.i("URL",strings[0]);
////                return "Done";
////            }
////        }
////
////        GraphTask task = new GraphTask();
////        String result;
////        try {
////            result = task.execute("http://127.0.0.1:5000/").get();
////            Log.i("Connected:",result);
////        } catch (Exception e){
////            e.printStackTrace();
////            Log.i("Not connected", "Connection failed.");
////        }
//
//        //resultsTextData
//
//    }
//}