//https://nabeelj.medium.com/making-a-simple-get-and-post-request-using-volley-beginners-guide-ee608f10c0a9
package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
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
    }

    public void volleyPost(View view) {
        String BASE = "http://192.168.1.14:5000/%22title%22:%22TestGraph%22,%22entries%22:[[";
        String urlForMS = BASE + globalCount + "," + globalTotalReps + ",%22r%22,%22line1%22]]";
        PrepImage test = new PrepImage();
        try {
            test.execute(urlForMS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_view);
        SQLiteDatabase db = this.openOrCreateDatabase("test1",MODE_PRIVATE,null);
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
            tbl.addView(tblRow2);
        }
    }
}