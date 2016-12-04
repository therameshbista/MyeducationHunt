package com.example.user.educationhunt.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.OurSchool;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeeStructure extends Fragment {

    LinearLayout grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8, grade9, grade10;
    TextView grade1Fee, grade2Fee, grade3Fee, grade4Fee, grade5Fee, grade6Fee, grade7Fee, grade8Fee, grade9Fee, grade10Fee;


    public FeeStructure() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fee_structure, container, false);

        grade1 = (LinearLayout) view.findViewById(R.id.grade1);
        grade2 = (LinearLayout) view.findViewById(R.id.grade2);
        grade3 = (LinearLayout) view.findViewById(R.id.grade3);
        grade4 = (LinearLayout) view.findViewById(R.id.grade4);
        grade5 = (LinearLayout) view.findViewById(R.id.grade5);
        grade6 = (LinearLayout) view.findViewById(R.id.grade6);
        grade7 = (LinearLayout) view.findViewById(R.id.grade7);
        grade8 = (LinearLayout) view.findViewById(R.id.grade8);
        grade9 = (LinearLayout) view.findViewById(R.id.grade9);
        grade10 = (LinearLayout) view.findViewById(R.id.grade10);

        grade1Fee = (TextView) view.findViewById(R.id.grade1_fee);
        grade2Fee = (TextView) view.findViewById(R.id.grade2_fee);
        grade3Fee = (TextView) view.findViewById(R.id.grade3_fee);
        grade4Fee = (TextView) view.findViewById(R.id.grade4_fee);
        grade5Fee = (TextView) view.findViewById(R.id.grade5_fee);
        grade6Fee = (TextView) view.findViewById(R.id.grade6_fee);
        grade7Fee = (TextView) view.findViewById(R.id.grade7_fee);
        grade8Fee = (TextView) view.findViewById(R.id.grade8_fee);
        grade9Fee = (TextView) view.findViewById(R.id.grade9_fee);
        grade10Fee = (TextView) view.findViewById(R.id.grade10_fee);

        OurSchool ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");

        grade1Fee.setText(ourSchool.getUpdatedAt());
        grade2Fee.setText(ourSchool.getSchoolLocation());
        grade3Fee.setText(ourSchool.getSchoolLocation());
        grade4Fee.setText(ourSchool.getSchoolLocation());
        grade5Fee.setText(ourSchool.getSchoolLocation());
        grade6Fee.setText(ourSchool.getSchoolLocation());
        grade7Fee.setText(ourSchool.getSchoolLocation());
        grade8Fee.setText(ourSchool.getSchoolLocation());
        grade9Fee.setText(ourSchool.getSchoolLocation());
        grade10Fee.setText(ourSchool.getSchoolLocation());

        return view;
    }

}
