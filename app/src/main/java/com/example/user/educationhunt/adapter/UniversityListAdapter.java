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
import com.example.user.educationhunt.pojos.OurUniversity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/16/2016.
 */
public class UniversityListAdapter extends BaseAdapter implements Filterable {

    private Activity activity;
    private LayoutInflater inflater;

    private List<OurUniversity> ourUniversityList;
    private List<OurUniversity> ourUniversityList_original;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private ItemFilter itemFilter = new ItemFilter();


    public UniversityListAdapter(Activity activity, List<OurUniversity> ourUniversityList) {
        this.activity = activity;
        this.ourUniversityList = ourUniversityList;
        this.ourUniversityList_original = ourUniversityList;
    }


    @Override
    public int getCount() {
        return ourUniversityList.size();
    }

    @Override
    public Object getItem(int location) {
        return ourUniversityList.get(location);
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
            convertView = inflater.inflate(R.layout.list_university_view, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView universityLogo = (NetworkImageView) convertView
                .findViewById(R.id.universityLogo);
        TextView universityName = (TextView) convertView.findViewById(R.id.universityName);
        TextView universityLocation = (TextView) convertView.findViewById(R.id.universityLocation);
        OurUniversity m = ourUniversityList.get(position);

        universityLogo.setImageUrl(m.getUniversityLogo(), imageLoader);

        universityName.setText("Name: " + m.getUniversityName());

        universityLocation.setText("Address: " + String.valueOf(m.getUniversityAddress()));


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
            final List<OurUniversity> ourUniversity = ourUniversityList_original;

            int count = ourUniversity.size();

            List<OurUniversity> filteredUniversityList = new ArrayList<>();

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = ourUniversity.get(i).getUniversityName();
                if (filterableString.toLowerCase().contains(filterString)) {
                    filteredUniversityList.add(ourUniversity.get(i));
                }
            }
            filterResults.values = filteredUniversityList;
            filterResults.count = filteredUniversityList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ourUniversityList = (List<OurUniversity>) filterResults.values;
            notifyDataSetChanged();
        }
    }

}
