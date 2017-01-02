package com.example.user.educationhunt.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.Collegefees;
import com.example.user.educationhunt.pojos.FeeClass;

import java.util.List;

/**
 * Created by user on 12/19/2016.
 */
public class CollegeFeeAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    private List<Collegefees> ourFeeList;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();


    public CollegeFeeAdapter(Activity activity, List<Collegefees> ourFeeList) {
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
            convertView = inflater.inflate(R.layout.item_college_grade, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        TextView collegeLevel = (TextView) convertView.findViewById(R.id.collegeLevel);
        TextView collegeProgramName = (TextView) convertView.findViewById(R.id.collegeProgramName);
        TextView collegeProgramDuration = (TextView) convertView.findViewById(R.id.collegeProgramDuration);
        TextView collegeProgramFee = (TextView) convertView.findViewById(R.id.collegeProgramFee);
        Collegefees m = ourFeeList.get(position);

        collegeLevel.setText( m.getLevel());
        collegeProgramDuration.setText( m.getDuration());
        collegeProgramFee.setText( m.getFee());
        collegeProgramName.setText(m.getProgramName());


        return convertView;
    }
}
