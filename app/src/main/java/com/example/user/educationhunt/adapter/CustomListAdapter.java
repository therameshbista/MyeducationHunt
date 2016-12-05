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
import com.example.user.educationhunt.pojos.OurSchool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/3/2016.
 */
public class CustomListAdapter extends BaseAdapter implements Filterable {

    private Activity activity;
    private LayoutInflater inflater;

    private List<OurSchool> ourSchoolsList;
    private List<OurSchool> ourSchoolsList_original;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private ItemFilter itemFilter = new ItemFilter();


    public CustomListAdapter(Activity activity, List<OurSchool> ourSchoolsList) {
        this.activity = activity;
        this.ourSchoolsList = ourSchoolsList;
        this.ourSchoolsList_original = ourSchoolsList;
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

        schoolName.setText("Name: " + m.getSchoolName());

        schoolLocation.setText("Address: " + String.valueOf(m.getSchoolLocation()));


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
            final List<OurSchool> ourSchools = ourSchoolsList_original;

            int count = ourSchools.size();

            List<OurSchool> filteredSchoolList = new ArrayList<>();

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = ourSchools.get(i).getSchoolName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    filteredSchoolList.add(ourSchools.get(i));
                }
            }
            filterResults.values = filteredSchoolList;
            filterResults.count = filteredSchoolList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ourSchoolsList = (List<OurSchool>) filterResults.values;
            notifyDataSetChanged();
        }
    }
}