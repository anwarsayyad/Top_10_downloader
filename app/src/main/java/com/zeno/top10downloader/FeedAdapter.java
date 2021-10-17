package com.zeno.top10downloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private  final int layoutResource;                  // final key word used to intiliaze the value the will not be changed by the method or class
    private final LayoutInflater layoutInflater;        // for more info about final click here https://techvidvan.com/tutorials/java-final-keyword
    private List<Feedentry> applications;               // creating the list that contain the objectes of the class Feedenty


    /*
        Constronctor created as we extended from the ArrayAdapter
        it has two values context and resources  and also we passing
        one more peramerter List<Feedentry>
     */
    public FeedAdapter(@NonNull Context context, int resource, List<Feedentry> applications) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context); // about inflater is  https://developer.android.com/reference/android/view/LayoutInflater
        this.applications = applications;                   // for more info about context click here https://developer.android.com/reference/android/content/Context

    }

    @Override
    public int getCount() {
       return  applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource,parent,false);
        TextView Tvname = (TextView) view.findViewById(R.id.Tv_name);
        TextView Tv_aname = (TextView) view.findViewById(R.id.Tv_aname);
        TextView Tv_release = (TextView) view.findViewById(R.id.Tv_release);

        Feedentry currentApp = applications.get(position);
        Tvname.setText(currentApp.getName());
        Tv_aname.setText(currentApp.getAuthor_and_singer());
        Tv_release.setText(currentApp.getRealse_date());


        return view;
    }
}
