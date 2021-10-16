package com.zeno.top10downloader;

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
        Feedentry currentRecords;
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

            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}
