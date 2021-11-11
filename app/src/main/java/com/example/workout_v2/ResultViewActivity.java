//https://nabeelj.medium.com/making-a-simple-get-and-post-request-using-volley-beginners-guide-ee608f10c0a9
package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResultViewActivity extends AppCompatActivity {

    String globalTotalReps = "";
    String globalWeights = "";
    String globalCount = "";

    public void refreshImageClick(View view) {
        ImageView image = (ImageView) findViewById(R.id.graphImageView);
        image.setImageResource(R.drawable.graph);
//        Toast.makeText(ResultViewActivity.this, "Working", Toast.LENGTH_SHORT).show();
    }


    public void volleyPost(View view) {
//        String url1 = "https://graph-microservice.herokuapp.com/";
//        //String localURL = "http://127.0.0.1:5000/";
//        String localURL = "http://10.0.2.2/";
//        String testSite = "https://ptsv2.com/t/ztn2r-1636494138/post";
//        String data = "\"title\": \"Test Graph\",\"entries\": [[[1,2,3,4,10], [7,4,2,8,9], \"r\", \"line1\"]]}";
//        String combo = localURL + data;
        //String SUCCESSFUL = "http://127.0.0.1:5000/%22title%22:%22TestGraph%22,%22entries%22:[[[1,2,3,4,10],[7,4,2,8,9],%22r%22,%22line1%22]]";
        //String successful_DONT_USE = "http://127.0.0.1:5000/\"title\":\"TestGraph\",\"entries\":[[[1,2,3,4,10],[7,4,2,8,9],\"r\",\"line1\"]]"; //DONT USE, USE ABOVE.
        //String SUCCESSFUL_test = "http://192.168.1.14:5000/%22title%22:%22TestGraph%22,%22entries%22:[[[1,2,3,4,10],[7,4,2,8,-9],%22r%22,%22line1%22]]";
        String BASE = "http://192.168.1.14:5000/%22title%22:%22TestGraph%22,%22entries%22:[[";
        String urlForMS = BASE + globalCount + "," + globalTotalReps + ",%22r%22,%22line1%22]]";
        PrepImage test = new PrepImage();
        try {
            //Toast.makeText(ResultViewActivity.this, "worked?", Toast.LENGTH_SHORT).show();
            test.execute(urlForMS);  //SUCCESSFUL_test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        addTable();
    }


    public int countRows(String tableName) {
        int numOfRows = 0;
        String sqlRowCount = "SELECT COUNT(1) FROM "+tableName;
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery(sqlRowCount,null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            numOfRows = cursor.getInt(0);
        }
        cursor.close();
        return numOfRows;
    }


    class PrepImage extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


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
//        Toast.makeText(ResultViewActivity.this,listInfo.toString(),Toast.LENGTH_SHORT).show();
        return listInfo;
    }


    public List<String> colInfoString(int colNum) {
        List<String> listInfo = new ArrayList<>();
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        String query = "SELECT * FROM " + extraTableName;
        Cursor cursor = db.rawQuery(query,null);
        if (cursor.moveToFirst()) {
            do {
                listInfo.add(cursor.getString(colNum));
            } while (cursor.moveToNext());
        }
        cursor.close();
//        Toast.makeText(ResultViewActivity.this,listInfo.toString(),Toast.LENGTH_SHORT).show();
        return listInfo;
    }


    public List<Integer> listBuilder(int listSize) {
        List<Integer> returnList = new ArrayList<>();
        for (int i = 1; i <= listSize; i++) {
            returnList.add(i);
        }
        return returnList;
    }


    public void addTable() {
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        List<Integer> totalReps = new ArrayList<>();    //Lists are for chart and graph.
        List<Integer> weights = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        List<String> comments = new ArrayList<>();
        totalReps = colInfoInt(1); //1 is col 1 in table, this is totalReps.
        weights = colInfoInt(2);   //col 2 is weights
        dates = colInfoString(4);  //col 4 is date info.
//        comments = colInfoString(5);


        globalTotalReps = totalReps.toString();
        globalWeights = weights.toString();
        globalCount = listBuilder(totalReps.size()).toString();

        int rowCount = countRows(extraTableName);

        TableLayout tbl = (TableLayout) findViewById(R.id.exerciseTable);
        TableRow tblRow = (new TableRow(this));

        TextView header0 = new TextView(this);
        header0.setText("#   ");
        tblRow.addView(header0);

        TextView header1 = new TextView(this);
        header1.setText("Date   ");
        tblRow.addView(header1);

        TextView header2 = new TextView(this);
        header2.setText("Weight   ");
        tblRow.addView(header2);

        TextView header3 = new TextView(this);
        header3.setText("Total Reps");
        tblRow.addView(header3);

        ////////////////////////////
//        TextView header4 = new TextView(this);
//        header4.setText("Comments");
//        tblRow.addView(header4);
        /////////////////////////////

        tbl.addView(tblRow);
        for (int i = 0; i < rowCount; i++) {
            TableRow tblRow2 = new TableRow(this);
            TextView nextCell0 = new TextView(this);
            nextCell0.setText(" " + (i+1)+ "   ");
            tblRow2.addView(nextCell0);

            TextView nextCell1 = new TextView(this);
            nextCell1.setText(dates.get(i).toString()+"     ");
            tblRow2.addView(nextCell1);

            TextView nextCell2 = new TextView(this);
            nextCell2.setText(weights.get(i).toString());
            tblRow2.addView((nextCell2));

            TextView nextCell3 = new TextView(this);
            nextCell3.setText(totalReps.get(i).toString());
            tblRow2.addView((nextCell3));

//            ////////////////////////////////////
//            TextView nextCell4 = new TextView(this);
//            nextCell4.setText(comments.get(i).toString());
//            tblRow2.addView((nextCell4));
//            ////////////////////////////////////

            tbl.addView(tblRow2);
        }
    }
}