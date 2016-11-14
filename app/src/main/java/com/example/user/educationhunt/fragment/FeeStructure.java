package com.example.user.educationhunt.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.educationhunt.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeeStructure extends Fragment {


    public FeeStructure() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fee_structure, container, false);
    }

}
