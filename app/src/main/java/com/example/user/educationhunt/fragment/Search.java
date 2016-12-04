package com.example.user.educationhunt.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.educationhunt.Bookmark;
import com.example.user.educationhunt.College;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.School;
import com.example.user.educationhunt.University;
import com.example.user.educationhunt.database.DatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment implements View.OnClickListener{

    LinearLayout school,college,university,bookmark;
    DatabaseHelper db;

    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search, container, false);

        //creating table in database
        db= new DatabaseHelper(getActivity());

        school= (LinearLayout) view.findViewById(R.id.school);
        college= (LinearLayout) view.findViewById(R.id.college);
        university= (LinearLayout) view.findViewById(R.id.university);
        bookmark= (LinearLayout) view.findViewById(R.id.bookmark);

        school.setOnClickListener(this);
        college.setOnClickListener(this);
        university.setOnClickListener(this);
        bookmark.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.school:
                startActivity(new Intent(getContext(),School.class));
                break;
            case R.id.college:
                startActivity(new Intent(getContext(),College.class));
                break;
            case R.id.university:
                startActivity(new Intent(getContext(),University.class));
                break;
            case R.id.bookmark:
                startActivity(new Intent(getContext(),Bookmark.class));
                break;
        }
    }
}
