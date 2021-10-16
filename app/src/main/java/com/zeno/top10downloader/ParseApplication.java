package com.zeno.top10downloader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseApplication {
    private static final String TAG = "ParseAPllication";
    private ArrayList<Feedentry> applications;

    public ParseApplication() {

        this.applications = new ArrayList<>();
    }

    public ArrayList<Feedentry> getApplications() {

        return applications;
    }

    public boolean parse(String xmlData) {
        boolean status = true;
        Feedentry currentRecords = null;
        boolean inEntry = false;
        String textValue = "";

        try {
//          to check and know about more below class(XmlPullPareserFactory)  https://developer.android.com/reference/org/xmlpull/v1/XmlPullParserFactory
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));   // String reader convert normal string to stream to know more about stream https://www.geeksforgeeks.org/stream-in-java/
            //passing value that we get from the parameter of the function
            int eventType = xpp.getEventType(); // intilazing int veriable to check weither geven XML is completed or not
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        Log.d(TAG, "parse: Starting tag for "+tagname);
                        if("entry".equalsIgnoreCase(tagname)) {
                            inEntry = true;
                            currentRecords = new Feedentry(); //initalizing Feedentry object here
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        Log.d(TAG, "parse: Ending tag for"+tagname);
                        if(inEntry){

                                if("entry".equalsIgnoreCase(tagname)) {
                                    applications.add(currentRecords);   // adding object in the applications array list
                                    inEntry = false;
                                }else if("name".equalsIgnoreCase(tagname)) { //setting values for the variables preseant in the Feedentry class
                                        currentRecords.setName(textValue);

                                }else if("artist".equalsIgnoreCase(tagname)) {
                                       currentRecords.setAuthor_and_singer(textValue);

                                }else if("image".equalsIgnoreCase(tagname)){
                                       currentRecords.setImage_url(textValue);

                                }else if("releasedate".equalsIgnoreCase(tagname)){
                                        currentRecords.setRealse_date(textValue);
                                }else if("category".equalsIgnoreCase(tagname)){
                                        currentRecords.setCategory(textValue);
                                }
                        }
                                break;
                    default:
                        // nothing to de here lol

                }


            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
