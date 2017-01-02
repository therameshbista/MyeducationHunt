package com.example.user.educationhunt.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
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
public class CollegeListAdapter extends BaseAdapter implements Filterable {

    private Activity activity;
    private LayoutInflater inflater;

    private List<OurCollege> ourCollegeList;
    private List<OurCollege> ourCollegeList_original;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private ItemFilter itemFilter = new ItemFilter();


    public CollegeListAdapter(Activity activity, List<OurCollege> ourCollegeList) {
        this.activity = activity;
        this.ourCollegeList = ourCollegeList;
        this.ourCollegeList_original = ourCollegeList;
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

        collegeLogo.setImageUrl(m.getCollegeLogo(), imageLoader);

        collegeName.setText("Name: " + m.getCollegeName());

        collegeLocation.setText("Address: " + String.valueOf(m.getCollegeAddress()));


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    private class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String filterString = charSequence.toString().toLowerCase();

            FilterResults filterResults = new FilterResults();
            final List<OurCollege> ourCollege = ourCollegeList_original;

            int count = ourCollege.size();

            List<OurCollege> filteredCollegeList = new ArrayList<>();

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = ourCollege.get(i).getCollegeName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    filteredCollegeList.add(ourCollege.get(i));
                }
            }
            filterResults.values = filteredCollegeList;
            filterResults.count = filteredCollegeList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ourCollegeList = (List<OurCollege>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}