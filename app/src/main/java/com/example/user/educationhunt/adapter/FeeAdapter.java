package com.example.user.educationhunt.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.FeeClass;
import com.example.user.educationhunt.pojos.OurSchool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/3/2016.
 */
public class FeeAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    private List<FeeClass> ourFeeList;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public FeeAdapter(Activity activity, List<FeeClass> ourFeeList) {
        this.activity = activity;
        this.ourFeeList = ourFeeList;
    }


    @Override
    public int getCount() {
        return ourFeeList.size();
    }

    @Override
    public Object getItem(int location) {
        return ourFeeList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_school_grade, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        TextView schoolGrade = (TextView) convertView.findViewById(R.id.schoolGrade);
        TextView schoolFee = (TextView) convertView.findViewById(R.id.schoolFee);
        FeeClass m = ourFeeList.get(position);


        schoolGrade.setText("Grade: " + m.getGrade());

        schoolFee.setText( m.getFee());


        return convertView;
    }
}