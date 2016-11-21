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
import com.example.user.educationhunt.pojos.Bookmarkitem;

import java.util.List;

/**
 * Created by user on 11/21/2016.
 */
public class BookmarkAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Bookmarkitem> ourBookmarkList;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public BookmarkAdapter(Activity activity, List<Bookmarkitem> ourBookmarkList) {
        this.activity = activity;
        this.ourBookmarkList = ourBookmarkList;
    }


    @Override
    public int getCount() {
        return ourBookmarkList.size();
    }

    @Override
    public Object getItem(int location) {
        return ourBookmarkList.get(location);
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
            convertView = inflater.inflate(R.layout.bookmark_list, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView bookmarkLogo = (NetworkImageView) convertView
                .findViewById(R.id.bookmarklogo);
        TextView bookmarkTitle = (TextView) convertView.findViewById(R.id.bookmarkTitle);
        TextView bookmarkAddress = (TextView) convertView.findViewById(R.id.bookmarkAddress);
        Bookmarkitem m = ourBookmarkList.get(position);

        bookmarkLogo.setImageUrl(m.getLogo(), imageLoader);

        bookmarkTitle.setText("Name: " +m.getName());

        bookmarkAddress.setText("Address: " +String.valueOf(m.getAddress()));


        return convertView;
    }
}