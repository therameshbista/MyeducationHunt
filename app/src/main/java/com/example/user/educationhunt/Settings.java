package com.example.user.educationhunt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment implements View.OnClickListener{

    TextView terms,privacy,info,help;
    ImageView toggleSound,toggleLocation;

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
        toggleSound= (ImageView) view.findViewById(R.id.toggleSound);
        toggleLocation= (ImageView) view.findViewById(R.id.toggleLocation);

        terms.setOnClickListener(this);
        privacy.setOnClickListener(this);
        info.setOnClickListener(this);
        help.setOnClickListener(this);
        toggleSound.setOnClickListener(this);
        toggleLocation.setOnClickListener(this);

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
