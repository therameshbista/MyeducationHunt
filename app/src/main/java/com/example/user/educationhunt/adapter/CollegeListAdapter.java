package com.example.user.educationhunt.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.OurCollege;
import com.example.user.educationhunt.pojos.OurSchool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/14/2016.
 */
public class CollegeListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<OurCollege> ourCollegeList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CollegeListAdapter(Activity activity, List<OurCollege> ourCollegeList) {
        this.activity = activity;
        this.ourCollegeList = ourCollegeList;
    }

    @Override
    public int getCount() {
        return ourCollegeList.size();
    }

    @Override
    public Object getItem(int location) {
        return ourCollegeList.get(location);
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
            convertView = inflater.inflate(R.layout.list_college_view, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView collegeLogo = (NetworkImageView) convertView
                .findViewById(R.id.collegeLogo);
        TextView collegeName = (TextView) convertView.findViewById(R.id.collegeName);
        TextView collegeLocation = (TextView) convertView.findViewById(R.id.collegeLocation);
        OurCollege m = ourCollegeList.get(position);

        collegeLogo.setImageUrl(m.getCollegeLogoCollege(), imageLoader);

        collegeName.setText("Name: " +m.getNameCollege());

        collegeLocation.setText("Address: " +String.valueOf(m.getLocationCollege()));


        return convertView;
    }

}