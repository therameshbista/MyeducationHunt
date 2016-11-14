package com.example.user.educationhunt.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.OurSchool;
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
public class About extends Fragment implements OnMapReadyCallback{

    MapView mapView;
    GoogleMap map;
    TextView aboutSchoolName,aboutSchoolLocation,aboutSchoolNumber,aboutSchoolEmail,aboutSchoolWebsite,aboutSchoolCategory;


    public About() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        aboutSchoolName= (TextView) v.findViewById(R.id.about_school_name);
        aboutSchoolLocation= (TextView) v.findViewById(R.id.about_school_location);
        aboutSchoolNumber= (TextView) v.findViewById(R.id.about_school_number);
        aboutSchoolCategory= (TextView) v.findViewById(R.id.about_school_category);
        aboutSchoolEmail= (TextView) v.findViewById(R.id.about_school_email);
        aboutSchoolWebsite= (TextView) v.findViewById(R.id.about_school_website);

//        String name=getArguments().getString("name");
//        String location=getArguments().getString("location");
//        String email=getArguments().getString("email");
//        String website=getArguments().getString("website");
//        String created=getArguments().getString("created");
//        String updated=getArguments().getString("updated");

//        aboutSchoolName.setText(name);
//        aboutSchoolName.setText(location);
//        aboutSchoolName.setText(email);
//        aboutSchoolName.setText(website);
//        aboutSchoolName.setText(created);
//        aboutSchoolName.setText(updated);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(27.6933, 85.3211), 15);
        map.animateCamera(cameraUpdate);
        onMapReady(map);

        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
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