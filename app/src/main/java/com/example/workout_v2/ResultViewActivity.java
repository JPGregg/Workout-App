//https://nabeelj.medium.com/making-a-simple-get-and-post-request-using-volley-beginners-guide-ee608f10c0a9
package com.example.workout_v2;

import androidx.appcompat.app.AppCompatActivity;

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

public class ResultViewActivity extends AppCompatActivity {


    public void refreshImageClick(View view) {
        ImageView image = (ImageView) findViewById(R.id.graphImageView);
        image.setImageResource(R.drawable.graph);
        Toast.makeText(ResultViewActivity.this, "Working", Toast.LENGTH_SHORT).show();
    }


    public void volleyPost(View view){



        String url1 = "https://graph-microservice.herokuapp.com/";
        //String localURL = "http://127.0.0.1:5000/";
        String localURL = "http://10.0.2.2/";
        String testSite = "https://ptsv2.com/t/ztn2r-1636494138/post";
        String myIP = "http://47.154.107.175:5000";
        //String data = "{\"filename\": \"newimage2\",\"dpi\": 300,\"format\": \"line\",\"output\": \"PNG\",\"filename\": \"graph\",\"title\": \"Test Graph\",\"xlabel\": \"X\",\"ylabel\": \"Y\",\"legend\": true,\"grid\": false,\"entries\": [[[1,2,3,4,5], [7,4,2,8,9], \"r\", \"line1\"], [[2,5,7,8,9], [-10,2,5,9,-3], \"b\", \"line2\"]]}";
        //String data = "title\": \"Test Graph\",\"entries\": [[[1,2,3,4,10], [7,4,2,8,9], \"r\", \"line1\"]]}";
        String data = "\"title\": \"Test Graph\",\"entries\": [[[1,2,3,4,10], [7,4,2,8,9], \"r\", \"line1\"]]}";
        String combo = localURL+data;

        String SUCCESSFUL = "http://127.0.0.1:5000/%22title%22:%22TestGraph%22,%22entries%22:[[[1,2,3,4,10],[7,4,2,8,9],%22r%22,%22line1%22]]";
        String successful_DONT_USE = "http://127.0.0.1:5000/\"title\":\"TestGraph\",\"entries\":[[[1,2,3,4,10],[7,4,2,8,9],\"r\",\"line1\"]]"; //DONT USE, USE ABOVE.
        String SUCCESSFUL_test = "http://192.168.1.14:5000/%22title%22:%22TestGraph%22,%22entries%22:[[[1,2,3,4,10],[7,4,2,8,-9],%22r%22,%22line1%22]]";

        //////////////////////////////new stuff today Wed

        PrepImage test = new PrepImage();

        try {
            //Toast.makeText(ResultViewActivity.this, "worked?", Toast.LENGTH_SHORT).show();
            test.execute(SUCCESSFUL_test);
        } catch (Exception e) {
            e.printStackTrace();
        }





//        }




        //this was the closest I got. Got an android.os.networkonmainthreadexception. Moving back to Async.
//        try {
//            URL url = new URL(SUCCESSFUL);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//            //Toast.makeText(ResultViewActivity.this, url.toString(), Toast.LENGTH_SHORT).show();
//
//        } catch (MalformedURLException e) {
//            Toast.makeText(ResultViewActivity.this, "busted1", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        } catch (IOException e) {
//            Toast.makeText(ResultViewActivity.this, "busted2", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        } catch (Exception e) {
//            Toast.makeText(ResultViewActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }
//        Toast.makeText(ResultViewActivity.this, "working", Toast.LENGTH_SHORT).show();








//        RequestQueue requestQueue = Volley.newRequestQueue(ResultViewActivity.this);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, myIP, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(ResultViewActivity.this, "Successful GET String response", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ResultViewActivity.this, "Error in String response", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myIP, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(ResultViewActivity.this, "Successful GET JSON response", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ResultViewActivity.this, "Error in Object response", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //json post
//        JSONObject object = new JSONObject();
//        try {
//            object.put("string",combo);
//        } catch (JSONException e) {
//            Toast.makeText(ResultViewActivity.this, "Error in object build", Toast.LENGTH_SHORT).show();
//        }
//        JsonObjectRequest jsonObjectRequestPost = new JsonObjectRequest(Request.Method.POST, myIP, object, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(ResultViewActivity.this, "Object Post successful", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ResultViewActivity.this, "Object Post Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //json post
//
//
//        //Toast.makeText(ResultViewActivity.this, combo, Toast.LENGTH_SHORT).show();
//        requestQueue.add(jsonObjectRequest);
//        requestQueue.add(stringRequest);
//        requestQueue.add(jsonObjectRequestPost);









//        try {
//
//        } catch (Exception e) {
//            Toast.makeText(this, "broken", Toast.LENGTH_SHORT).show();
//        }


        //////////////////////////////new stuff today Wed



//        try {
//            URL url = new URL(url1);
//            URLConnection con = url.openConnection();
//            HttpURLConnection http = (HttpURLConnection) con;
//            http.setRequestMethod("POST");
//            http.setDoOutput(true);
//
//            byte[] out = "{\"filename\": \"newimage2\",\"dpi\": 300,\"format\": \"line\",\"output\": \"PNG\",\"filename\": \"graph\",\"title\": \"Test Graph\",\"xlabel\": \"X\",\"ylabel\": \"Y\",\"legend\": true,\"grid\": false,\"entries\": [[[1,2,3,4,5], [7,4,2,8,9], \"r\", \"line1\"], [[2,5,7,8,9], [-10,2,5,9,-3], \"b\", \"line2\"]]}" .getBytes(StandardCharsets.UTF_8);
//            int length = out.length;
//
//            http.setFixedLengthStreamingMode(length);
//            http.setRequestProperty("Content-Type", "application/json");//; charset=UTF-8");
//            http.connect();
//            try(OutputStream os = http.getOutputStream()) {
//                os.write(out);
//            }
//            Toast.makeText(ResultViewActivity.this, "Yup", Toast.LENGTH_SHORT).show();
//// Do something with http.getInputStream()
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(ResultViewActivity.this, "Nope", Toast.LENGTH_SHORT).show();
//        }


        //RequestQueue requestQueue = Volley.newRequestQueue(ResultViewActivity.this);



//        try {
//            String result = "";
//            HttpURLConnection urlConnection = null;
//            URL url = new URL(url1);
//            urlConnection = (HttpURLConnection) url.openConnection();
//            InputStream in = urlConnection.getInputStream();
//            InputStreamReader reader = new InputStreamReader(in);
//            int new_data = reader.read();
//
//            while (new_data != -1) {
//                char current = (char) new_data;
//                result += current;
//                new_data = reader.read();
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
//        }


//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//        JSONObject returnObject = new JSONObject();


            ///////////////Testing ground start
//        Map<String,String> sendMap = new HashMap<String,String>();
//        sendMap.put("data",data);
//        JSONObject sendJSONObject = new JSONObject(sendMap);
//
//        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, sendJSONObject, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(ResultViewActivity.this, "worked", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ResultViewActivity.this, "Nope", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params=new HashMap<String,String>();
//                params.put("data",data);
//                return params;
//            }
//        };
//////////////////////////////////////////////////////////////////////////////////////////////////////////


//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            JSONObject returnObject = new JSONObject();
//
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(ResultViewActivity.this, "worked", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ResultViewActivity.this, "Nope", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams(){
//                Map<String,String> params=new HashMap<String, String>();
//                params.put("data",data);
//                return params;
//            }
//            @Override
//            public Map<String,String> getHeaders() throws AuthFailureError{
//                Map<String,String> params=new HashMap<String,String>();
//                params.put("Content-Type","application/json");
//                return params;
//            }

            ////////////////The testing ground end


//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(ResultViewActivity.this, "worked", Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ResultViewActivity.this, "Nope", Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams(){
//                Map<String,String> params=new HashMap<String, String>();
//                params.put("data",data);
//                return params;
//            }

//            @Override
//            public Map<String,String> getHeaders() throws AuthFailureError{
//                Map<String,String> params=new HashMap<String,String>();
//                params.put("Content-Type","application/json");
//                return params;
////            }
//        };






//        Toast.makeText(ResultViewActivity.this, jsonRequest.toString(), Toast.LENGTH_SHORT).show();
//        requestQueue.add(jsonRequest);







//        JSONObject postData = new JSONObject();
//        try {
//            postData.put("data", data);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(ResultViewActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                //System.out.println(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError{
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Content-Type", "application/json");
//                return headers;
//            }
//        };
//
//        requestQueue.add(jsonObjectRequest);
//        //Toast.makeText(ResultViewActivity.this, requestQueue.toString(), Toast.LENGTH_SHORT).show();
//        requestQueue.start();
//        //Toast.makeText(ResultViewActivity.this, "started", Toast.LENGTH_SHORT).show();















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





    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class PrepImage extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... urls) {
//                Toast.makeText(ResultViewActivity.this, "worked?", Toast.LENGTH_SHORT).show();
//
            URL url;
//
            try {
                url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();


            } catch (Exception e) {
//                    Toast.makeText(ResultViewActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
//
            return null;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addtable() {
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
        for (int i = 0; i < 30; i++) {
            TableRow tblRow2 = new TableRow(this);
            TextView nextCell0 = new TextView(this);
            nextCell0.setText(" " + (i+1)+ "   ");
            tblRow2.addView(nextCell0);

            TextView nextCell1 = new TextView(this);
            nextCell1.setText("blah1blah   ");
            tblRow2.addView(nextCell1);

            TextView nextCell2 = new TextView(this);
            nextCell2.setText("blah2   ");
            tblRow2.addView((nextCell2));

            TextView nextCell3 = new TextView(this);
            nextCell3.setText("blah3");
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


