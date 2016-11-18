package com.example.user.educationhunt.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.OurSchool;

import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;

/**
 * Created by user on 11/3/2016.
 */
public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<OurSchool> ourSchoolsList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<OurSchool> ourSchoolsList) {
        this.activity = activity;
        this.ourSchoolsList = ourSchoolsList;
    }


    @Override
    public int getCount() {
        return ourSchoolsList.size();
    }

    @Override
    public Object getItem(int location) {
        return ourSchoolsList.get(location);
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
            convertView = inflater.inflate(R.layout.list_school_view, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView schoolLogo = (NetworkImageView) convertView
                .findViewById(R.id.schoolLogo);
        TextView schoolName = (TextView) convertView.findViewById(R.id.schoolName);
        TextView schoolLocation = (TextView) convertView.findViewById(R.id.schoolLocation);
        OurSchool m = ourSchoolsList.get(position);

        schoolLogo.setImageUrl(m.getSchoolLogo(), imageLoader);

        schoolName.setText("Name: " +m.getSchoolName());

        schoolLocation.setText("Address: " +String.valueOf(m.getSchoolLocation()));


        return convertView;
    }

    public static android.widget.Filter getFilter() {
        return null;
    }
}