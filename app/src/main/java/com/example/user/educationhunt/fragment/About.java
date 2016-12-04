package com.example.user.educationhunt.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.OurCollege;
import com.example.user.educationhunt.pojos.OurSchool;
import com.example.user.educationhunt.pojos.OurUniversity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap map;


    public TextView aboutSchoolName, aboutSchoolLocation, aboutSchoolNumber, aboutSchoolEmail, aboutSchoolWebsite, aboutSchoolCategory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(27.6933, 85.3211), 15);
        map.animateCamera(cameraUpdate);

        aboutSchoolName = (TextView) v.findViewById(R.id.about_school_name);
        aboutSchoolLocation = (TextView) v.findViewById(R.id.about_school_location);
        aboutSchoolNumber = (TextView) v.findViewById(R.id.about_school_number);
        aboutSchoolCategory = (TextView) v.findViewById(R.id.about_school_category);
        aboutSchoolEmail = (TextView) v.findViewById(R.id.about_school_email);
        aboutSchoolWebsite = (TextView) v.findViewById(R.id.about_school_website);

        OurSchool ourSchool = null;
        OurCollege ourCollege = null;
        OurUniversity ourUniversity = null;
        if (getActivity().getIntent().getSerializableExtra("school") != null) {
            ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
            aboutSchoolName.setText(ourSchool.getSchoolName());
            aboutSchoolLocation.setText(ourSchool.getSchoolLocation());
            aboutSchoolEmail.setText(ourSchool.getSchoolEmail());
            aboutSchoolWebsite.setText(ourSchool.getSchoolWebsite());
            aboutSchoolNumber.setText(ourSchool.getCreatedAt());
            aboutSchoolCategory.setText(ourSchool.getUpdatedAt());
        } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
            ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
            aboutSchoolName.setText(ourCollege.getNameCollege());
            aboutSchoolLocation.setText(ourCollege.getLocationCollege());
            aboutSchoolEmail.setText(ourCollege.getEmailCollege());
            aboutSchoolWebsite.setText(ourCollege.getWebsiteCollege());
            aboutSchoolNumber.setText(ourCollege.getCreatedAtCollege());
            aboutSchoolCategory.setText(ourCollege.getUpdatedAtCollege());
        } else {
            ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
            aboutSchoolName.setText(ourUniversity.getUniversityName());
            aboutSchoolLocation.setText(ourUniversity.getUniversityLocation());
            aboutSchoolEmail.setText(ourUniversity.getUniversityEmail());
            aboutSchoolWebsite.setText(ourUniversity.getUniversityWebsite());
            aboutSchoolNumber.setText(ourUniversity.getUniversitycreatedAt());
            aboutSchoolCategory.setText(ourUniversity.getUniversityupdatedAt());
        }


        onMapReady(map);

        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(27.6933, 85.3211))
                .title("St. Xavier's College"));
    }
}