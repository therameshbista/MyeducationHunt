package com.example.user.educationhunt.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.educationhunt.EduHunt;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.Website;
import com.example.user.educationhunt.pojos.Bookmarkitem;
import com.example.user.educationhunt.pojos.OurCollege;
import com.example.user.educationhunt.pojos.OurSchool;
import com.example.user.educationhunt.pojos.OurUniversity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    LinearLayout collegeAffiliation;
    ImageView phoneCalls,sendEmails,weblink;

    public TextView aboutName, aboutLocation, aboutNumber, aboutEmail, aboutWebsite, aboutCategory, aboutAffiliation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        collegeAffiliation = (LinearLayout) v.findViewById(R.id.collegeAffiliation);

        aboutName = (TextView) v.findViewById(R.id.about_school_name);
        aboutLocation = (TextView) v.findViewById(R.id.about_school_location);
        aboutNumber = (TextView) v.findViewById(R.id.about_school_number);
        aboutCategory = (TextView) v.findViewById(R.id.about_school_category);
        aboutAffiliation = (TextView) v.findViewById(R.id.about_college_affiliation);
        aboutEmail = (TextView) v.findViewById(R.id.about_school_email);
        aboutWebsite = (TextView) v.findViewById(R.id.about_school_website);
        phoneCalls = (ImageView) v.findViewById(R.id.phonecalls);
        sendEmails= (ImageView) v.findViewById(R.id.sendEmails);
        weblink= (ImageView) v.findViewById(R.id.weblink);

        mMapView = (MapView) v.findViewById(R.id.mapview);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;


                OurSchool ourSchool = null;
                OurCollege ourCollege = null;
                OurUniversity ourUniversity = null;
                Bookmarkitem bookmarkitem = null;
                if (getActivity().getIntent().getSerializableExtra("school") != null) {
                    ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
                    aboutName.setText(ourSchool.getSchoolName());
                    aboutLocation.setText(ourSchool.getSchoolAddress() + "," + ourSchool.getSchoolDistrict() + "," + ourSchool.getSchoolCountry());
                    aboutNumber.setText(ourSchool.getSchoolPhone());
                    phoneCalls.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            callPhoneNumber();
                        }
                    });
                    aboutCategory.setText(ourSchool.getSchoolType());
                    aboutEmail.setText(ourSchool.getSchoolEmail());
                    sendEmails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendEmail();
                        }
                    });
                    aboutWebsite.setText(ourSchool.getSchoolWebsite());
                    weblink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setWeblink();
                        }
                    });
                    LatLng schoollatlng = new LatLng(ourSchool.getLatitude(), ourSchool.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(schoollatlng).title(ourSchool.getSchoolName()));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(schoollatlng).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
                    ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
                    collegeAffiliation.setVisibility(View.VISIBLE);
                    aboutName.setText(ourCollege.getCollegeName());
                    aboutLocation.setText(ourCollege.getCollegeAddress() + "," + ourCollege.getCollegeDistrict() + "," + ourCollege.getCollegeCountry());
                    aboutNumber.setText(ourCollege.getCollegePhone());
                    phoneCalls.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            callPhoneNumber();
                        }
                    });
                    aboutCategory.setText(ourCollege.getCollegeType());
                    aboutAffiliation.setText(ourCollege.getCollegeAffiliation());
                    aboutEmail.setText(ourCollege.getCollegeEmail());
                    sendEmails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           sendEmail();
                        }
                    });
                    aboutWebsite.setText(ourCollege.getCollegeWebsite());
                    weblink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setWeblink();
                        }
                    });
                    LatLng collegelatlng = new LatLng(ourCollege.getLatitude(), ourCollege.getLongtitude());
                    googleMap.addMarker(new MarkerOptions().position(collegelatlng).title(ourCollege.getCollegeName()));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(collegelatlng).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                } else if (getActivity().getIntent().getSerializableExtra("university") != null) {
                    ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
                    aboutName.setText(ourUniversity.getUniversityName());
                    aboutLocation.setText(ourUniversity.getUniversityAddress() + "," + ourUniversity.getUniversityDistrict() + "," + ourUniversity.getUniverstiyCountry());
                    aboutNumber.setText(ourUniversity.getUniversityPhone());
                    phoneCalls.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View arg0) {
                            callPhoneNumber();
                        }
                    });
                    aboutCategory.setText(ourUniversity.getUniversityType());
                    aboutEmail.setText(ourUniversity.getUniversityEmail());
                    sendEmails.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sendEmail();
                        }
                    });
                    aboutWebsite.setText(ourUniversity.getUniversityWebsite());
                    weblink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setWeblink();
                        }
                    });
                    LatLng unilatlng = new LatLng(ourUniversity.getUniversityLatitude(), ourUniversity.getUniversityLongitude());
                    googleMap.addMarker(new MarkerOptions().position(unilatlng).title(ourUniversity.getUniversityName()));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(unilatlng).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                } else {
                    bookmarkitem = (Bookmarkitem) getActivity().getIntent().getSerializableExtra("bookmark");
                    aboutName.setText(bookmarkitem.getName());
                    aboutLocation.setText(bookmarkitem.getAddress());

                }
            }
        });


        return v;
    }

    public void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT > 22) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 101);

                    return;
                }
                OurSchool ourSchool = null;
                OurCollege ourCollege = null;
                OurUniversity ourUniversity = null;
                if (getActivity().getIntent().getSerializableExtra("school") != null) {
                    ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + ourSchool.getSchoolPhone()));
                    startActivity(callIntent);
                } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
                    ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + ourCollege.getCollegePhone()));
                    startActivity(callIntent);
                } else
                    ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + ourUniversity.getUniversityPhone()));
                startActivity(callIntent);


            } else {
                OurSchool ourSchool = null;
                OurCollege ourCollege = null;
                OurUniversity ourUniversity = null;
                if (getActivity().getIntent().getSerializableExtra("school") != null) {
                    ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + ourSchool.getSchoolPhone()));
                    startActivity(callIntent);
                } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
                    ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + ourSchool.getSchoolPhone()));
                    startActivity(callIntent);
                } else
                    ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + ourSchool.getSchoolPhone()));
                startActivity(callIntent);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhoneNumber();
            } else {
                Log.e("Permission not Granted", "");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void sendEmail() {
        OurSchool ourSchool = null;
        OurCollege ourCollege = null;
        OurUniversity ourUniversity = null;
        if (getActivity().getIntent().getSerializableExtra("school") != null) {
            ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", ourSchool.getSchoolEmail(), null));
            try {
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
            ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", ourCollege.getCollegeEmail(), null));
            try {
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        } else {
            ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", ourUniversity.getUniversityEmail(), null));
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
        try {

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setWeblink(){

        OurSchool ourSchool = null;
        OurCollege ourCollege = null;
        OurUniversity ourUniversity = null;
        if (getActivity().getIntent().getSerializableExtra("school") != null) {
            ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://"+ourSchool.getSchoolWebsite()));
            startActivity(intent);
        } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
            ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://"+ourCollege.getCollegeWebsite()));
            startActivity(intent);
        } else {
            ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse("http://"+ourUniversity.getUniversityWebsite()));
            startActivity(intent);
        }
        try {

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

    }
}