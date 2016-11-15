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
import com.example.user.educationhunt.School;
import com.example.user.educationhunt.SchoolDetails;
import com.example.user.educationhunt.pojos.OurSchool;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.junit.runner.Describable;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment implements OnMapReadyCallback{

    MapView mapView;
    GoogleMap map;
    public TextView aboutSchoolName,aboutSchoolLocation,aboutSchoolNumber,aboutSchoolEmail,aboutSchoolWebsite,aboutSchoolCategory;


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

        String Item = getActivity().getIntent().getExtras().getString("name");
        aboutSchoolName.setText(Item);

        String Item1 = getActivity().getIntent().getExtras().getString("location");
        aboutSchoolLocation.setText(Item1);

        String Item2 = getActivity().getIntent().getExtras().getString("email");
        aboutSchoolEmail.setText(Item2);

        String Item3= getActivity().getIntent().getExtras().getString("website");
        aboutSchoolWebsite.setText(Item3);

        String Item4 = getActivity().getIntent().getExtras().getString("created_at");
        aboutSchoolNumber.setText(Item4);

        String Item5 = getActivity().getIntent().getExtras().getString("updated_at");
        aboutSchoolCategory.setText(Item5);



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