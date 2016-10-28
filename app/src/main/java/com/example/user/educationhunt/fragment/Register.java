package com.example.user.educationhunt.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.educationhunt.CollegeRegistration;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.SchoolRegistration;
import com.example.user.educationhunt.UniversityRegistration;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment implements View.OnClickListener {
    Button regSchool,regCollege,regUniversity;

    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register,container, false);

        regSchool= (Button) view.findViewById(R.id.btn_school);
        regCollege= (Button) view.findViewById(R.id.btn_college);
        regUniversity= (Button) view.findViewById(R.id.btn_university);

        regSchool.setOnClickListener(this);
        regCollege.setOnClickListener(this);
        regUniversity.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_school:
                startActivity(new Intent(getContext(), SchoolRegistration.class));
                break;
            case R.id.btn_college:
                startActivity(new Intent(getContext(), CollegeRegistration.class));
                break;
            case R.id.btn_university:
                startActivity(new Intent(getContext(), UniversityRegistration.class));
                break;

        }



    }
}
