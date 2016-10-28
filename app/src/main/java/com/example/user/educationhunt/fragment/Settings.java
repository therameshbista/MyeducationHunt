package com.example.user.educationhunt.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.user.educationhunt.Help;
import com.example.user.educationhunt.Info;
import com.example.user.educationhunt.Privacy;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.Terms;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment implements View.OnClickListener{

    TextView terms,privacy,info,help;
    ToggleButton toggleSound,toggleLocation;
    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_settings, container, false);

        terms=(TextView) view.findViewById(R.id.terms);
        privacy=(TextView) view.findViewById(R.id.privacy);
        info=(TextView) view.findViewById(R.id.info);
        help=(TextView) view.findViewById(R.id.help);
        toggleLocation= (ToggleButton) view.findViewById(R.id.toggleLocation);
        toggleSound= (ToggleButton) view.findViewById(R.id.toggleLocation);


        terms.setOnClickListener(this);
        privacy.setOnClickListener(this);
        info.setOnClickListener(this);
        help.setOnClickListener(this);
        toggleSound.setOnClickListener(this);
        toggleLocation.setOnClickListener(this);

        toggleSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Sound  is disabled
                    toggleSound.setSoundEffectsEnabled(false);
                } else {
                    // Sound is enabled
                    toggleSound.setSoundEffectsEnabled(true);
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.terms:
                startActivity(new Intent(getContext(),Terms.class));
                break;
            case R.id.privacy:
                startActivity(new Intent(getContext(),Privacy.class));
                break;
            case R.id.info:
                startActivity(new Intent(getContext(),Info.class));
                break;
            case R.id.help:
                startActivity(new Intent(getContext(),Help.class));
                break;
            case R.id.toggleSound:
                break;
            case R.id.toggleLocation:
                break;


        }
    }
}
