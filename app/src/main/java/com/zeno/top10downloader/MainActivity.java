package com.zeno.top10downloader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; //just type logt to get this and add class name
    private ListView listApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listApps = (ListView) findViewById(R.id.XmlList);


        Log.d(TAG, "onCreate: starting Asynctask ");
        DownloadData downloadData = new DownloadData();  // creating object of Downloaddata class 
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=25/xml"); //using inbuilt command exucate to from AyncTask class
        Log.d(TAG, "onCreate: done");

    }

    private class DownloadData extends AsyncTask<String, Void, String> {
        private static final String TAG = "DownloadData"; // same here also logt

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: Peramerte is " + s); // but here logd
            super.onPostExecute(s);
            ParseApplication parseApplication = new ParseApplication();
            parseApplication.parse(s);

//            ArrayAdapter<Feedentry> arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.list_item, parseApplication.getApplications());
//            listApps.setAdapter(arrayAdapter);
             FeedAdapter feedAdapter = new FeedAdapter(MainActivity.this,R.layout.list_record,parseApplication.getApplications());
             parseApplication.getApplications();
             listApps.setAdapter(feedAdapter);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: start with " + strings[0]);
            // here also logd and and stting in message
            String rssFeed = DownloadXML(strings[0]);
            if (rssFeed == null) {
                Log.e(TAG, "doInBackground: Downloading");
            }

            return rssFeed; //returning ressFeed
        }

        /*
                imprortant read this before trying to understand the below code XD

            using input stream reader as buffer reader buffer the data comming from the stream
            like internet , device may be andriod or other , file extra
            * An InputStreamReader is a bridge from byte streams to character streams: It reads bytes and decodes them into characters using a specified charset.
                The charset that it uses may be specified by name or may be given explicitly, or the platform's default charset may be accepted.
                Each invocation of one of an InputStreamReader's read() methods may cause one or more bytes to be read from the underlying byte-input stream.
                To enable the efficient conversion of bytes to characters, more bytes may be read ahead from the underlying stream than are necessary to satisfy the current read operation.
                For top efficiency, consider wrapping an InputStreamReader within a BufferedReader. For example:
                BufferedReader in
                 = new BufferedReader(new InputStreamReader(System.in));
         *  Java BufferedReader class is used to read the text from a character-based input stream. It can be used to read data line by line by readLine() method. It makes the performance fast. It inherits Reader
           class
             .
         */
        private String DownloadXML(String urlPath) {
            StringBuilder xmlResult = new StringBuilder(); // Help to get muitable squance of charecters and in syncronized way

            try {
                URL url = new URL(urlPath); // creates an  instarce of a URL  from the String representation
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();//Uded to interact with web server inmportant in andriod developmnet
                int response = connection.getResponseCode();
                Log.d(TAG, "downloadXML: The response code was " + response);
//                InputStream inputStream = connection.getInputStream();//This abstract class is the superclass of all classes representing an input stream of bytes.
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader reader = new BufferedReader(inputStreamReader);
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                int charRead;
                char[] inputBuffer = new char[500];
                while (true) {
                    charRead = reader.read(inputBuffer);
                    if (charRead < 0) {
                        break;
                    }
                    if (charRead > 0) {
                        xmlResult.append(String.copyValueOf(inputBuffer, 0, charRead));
                    }
                }
                reader.close();
                return xmlResult.toString();
            } catch (MalformedURLException e) {
                Log.e(TAG, "DownloadXML: invalid URL " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "DownloadXML: IO expection while reading the data " + e.getMessage());
            } catch (SecurityException e) {
                Log.e(TAG, "DownloadXML: Need permisson please" + e.getMessage());
                e.printStackTrace();
            }
            return null;

        }

    }
}