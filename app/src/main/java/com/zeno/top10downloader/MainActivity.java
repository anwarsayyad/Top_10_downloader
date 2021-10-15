package com.zeno.top10downloader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; //just type logt to get this and add class name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: starting Asynctask "); 
        DownloadData downloadData = new DownloadData();  // creating object of Downloaddata class 
        downloadData.execute("Url goes here"); //using inbuilt command exucate to from AyncTask class
        Log.d(TAG, "onCreate: done");
        
    }

    private class DownloadData extends AsyncTask<String,Void,String>{
        private static final String TAG = "DownloadData"; // same here also logt
        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: Peramerte is "+s ); // but here logd
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: start with "+strings[0]); // here also logd and and stting in message
            return "Downloadingbackground completed.";
        }
    }
}