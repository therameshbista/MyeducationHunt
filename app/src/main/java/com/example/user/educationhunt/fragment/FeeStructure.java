package com.example.user.educationhunt.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.educationhunt.R;
import com.example.user.educationhunt.adapter.CollegeFeeAdapter;
import com.example.user.educationhunt.adapter.CustomListAdapter;
import com.example.user.educationhunt.adapter.FeeAdapter;
import com.example.user.educationhunt.pojos.Collegefees;
import com.example.user.educationhunt.pojos.FeeClass;
import com.example.user.educationhunt.pojos.OurCollege;
import com.example.user.educationhunt.pojos.OurSchool;
import com.example.user.educationhunt.pojos.OurUniversity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeeStructure extends Fragment {

    private ListView listView;
    TextView programName;


    public FeeStructure() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fee_structure, container, false);


        OurSchool ourSchool = null;
        OurCollege ourCollege = null;
        OurUniversity ourUniversity = null;

        programName= (TextView) view.findViewById(R.id.column_programName);


        if (getActivity().getIntent().getSerializableExtra("school") != null) {
            ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
            List<FeeClass> ourSchoolsFeeLists = ourSchool.getFeesList();

            programName.setVisibility(View.GONE);

            listView = (ListView) view.findViewById(R.id.list_fee_details);
            FeeAdapter adapter = new FeeAdapter(getActivity(), ourSchoolsFeeLists);
            listView.setAdapter(adapter);

        } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
            ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");

            List<Collegefees> ourCollegeFeeList=ourCollege.getFeesList();
            listView= (ListView) view.findViewById(R.id.list_fee_details);
            CollegeFeeAdapter adapter=new CollegeFeeAdapter(getActivity(),ourCollegeFeeList);
            listView.setAdapter(adapter);

        } else {
            ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");

            List<Collegefees> ourUniversityFeeList=ourUniversity.getFeesList();
            listView= (ListView) view.findViewById(R.id.list_fee_details);
            CollegeFeeAdapter adapter=new CollegeFeeAdapter(getActivity(),ourUniversityFeeList);
            listView.setAdapter(adapter);

        }
        return view;
    }

}
