package com.zeno.top10downloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

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
        Viewholder viewholder ;
        /*
            checking the view is empty of  like its created ot not if not means
            so as for now we just created the object not initalized ok
         */
        if(convertView == null) {
            /*
                This part get excute if the view is null or empty you can underatand by seeing abovw coundiation

             */

            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewholder = new Viewholder(convertView);
            convertView.setTag(viewholder);
            /*
            that objecet we created the  at the top will be initalized here if only the view is empty
            */

        }else{
            viewholder = (Viewholder) convertView.getTag();/*
            if view is alredy created then this block will execute it will just set the Tag that alredy (found view by id)  by the
            cunstroctor
            */

        }


//        TextView Tvname = (TextView) convertView.findViewById(R.id.Tv_name);
//        TextView Tv_aname = (TextView) convertView.findViewById(R.id.Tv_aname);
//        TextView Tv_release = (TextView) convertView.findViewById(R.id.Tv_release);
//        TextView Tvcost = (TextView) convertView.findViewById(R.id.Tvcost);

        Feedentry currentApp = applications.get(position);/*
        viewholder object of Viewholder class as name its hold the Views by thier id
        we can get them by the object easy pissy so the program no need go through all id's to
        get the view cool right !!!
        */
        viewholder.Tv_name.setText(currentApp.getName());
        viewholder.Tv_athour.setText(currentApp.getAuthor_and_singer());
        viewholder.Tv_Relase.setText(currentApp.getRealse_date());
        viewholder.Tv_cost.setText(currentApp.getCost());


        return convertView;
    }
    /*
            class created with constructor to find id of the view at time of the exucation of the]\
            program or app starting
     */

    private class Viewholder{/*
        Final means it's value be final no change at all haha
    */
        final TextView Tv_name;
        final TextView Tv_athour;
        final TextView Tv_Relase;
        final TextView Tv_cost;

        Viewholder(View v){
            this.Tv_name = v.findViewById(R.id.Tv_name);
            this.Tv_athour = v.findViewById(R.id.Tv_aname);
            this.Tv_Relase = v.findViewById(R.id.Tv_release);
            this.Tv_cost = v.findViewById(R.id.Tvcost);
        }


    }
}
