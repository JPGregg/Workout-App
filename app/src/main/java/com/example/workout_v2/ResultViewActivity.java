//https://nabeelj.medium.com/making-a-simple-get-and-post-request-using-volley-beginners-guide-ee608f10c0a9
package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResultViewActivity extends AppCompatActivity {

    String globalTotalReps = "";
    String globalWeights = "";
    String globalCount = "";


    class PrepImage extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
            URL url;
            try {
                url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                connection.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public void postRequest() {
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
        addTable(db);
        postRequest();
    }


    public int countRows(SQLiteDatabase db, String tableName) {
        int numOfRows = 0;
        String sqlRowCount = "SELECT COUNT(1) FROM "+tableName;
        Cursor c = db.rawQuery(sqlRowCount,null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            numOfRows = c.getInt(0);
        }
        c.close();
        return numOfRows;
    }


    public List<Integer> colInfoInt(SQLiteDatabase db, int colNum) {
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


    public List<String> colInfoString(SQLiteDatabase db, int colNum) {
        List<String> listInfo = new ArrayList<>();
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        String query = "SELECT * FROM " + extraTableName;
        Cursor c = db.rawQuery(query,null);
        if (c.moveToFirst()) {
            do {
                listInfo.add(c.getString(colNum));
            } while (c.moveToNext());
        }
        c.close();
        return listInfo;
    }


    public List<Integer> listBuilder(int listSize) {
        List<Integer> returnList = new ArrayList<>();
        for (int i = 1; i <= listSize; i++) {
            returnList.add(i);
        }
        return returnList;
    }


    //part 3 of 3 for building the table. Builds headers.
    public void headerBuilder(TableRow tblRow){
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
    }


    //part 2 of 3 for building the table. Starts the headers and fills in rows.
    public void tblBuilder(int rowCount, List<String> dates,
                           List<Integer> weights, List<Integer> totalReps) {
        //builds headers
        TableLayout tbl = (TableLayout) findViewById(R.id.exerciseTable);
        TableRow tblRow = (new TableRow(this));
        headerBuilder(tblRow);

        //adds the data to each row
        tbl.addView(tblRow);
        for (int i = 0; i < rowCount; i++) {
            TableRow tblRow2 = new TableRow(this);
            TextView nextCell0 = new TextView(this);
            nextCell0.setText(" " + (i + 1) + "   ");
            tblRow2.addView(nextCell0);
            TextView nextCell1 = new TextView(this);
            nextCell1.setText(dates.get(i).toString() + "     ");
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


    //part 1 of 3 for building the table. Compiles the data together
    public void addTable(SQLiteDatabase db) {
        Intent intent = getIntent();
        String extraTableName = intent.getStringExtra("tableName");
        List<Integer> totalReps = new ArrayList<>();    //Lists are for chart and graph.
        List<Integer> weights = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        totalReps = colInfoInt(db, 1); //1 is col 1 in table, this is totalReps.
        weights = colInfoInt(db, 2);   //col 2 is weights
        dates = colInfoString(db, 4);  //col 4 is date info.
        globalTotalReps = totalReps.toString();
        globalWeights = weights.toString();
        globalCount = listBuilder(totalReps.size()).toString();
        int rowCount = countRows(db, extraTableName);
        tblBuilder(rowCount, dates, weights, totalReps);
    }
}
