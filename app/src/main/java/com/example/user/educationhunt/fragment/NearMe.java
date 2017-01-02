package com.example.user.educationhunt.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user.educationhunt.R;

import com.example.user.educationhunt.SchoolDetails;
import com.example.user.educationhunt.adapter.CustomListAdapter;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.FeeClass;
import com.example.user.educationhunt.pojos.GpsLocation;
import com.example.user.educationhunt.pojos.MyCollege;
import com.example.user.educationhunt.pojos.MySchool;
import com.example.user.educationhunt.pojos.MyUniversity;
import com.example.user.educationhunt.pojos.OurSchool;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearMe extends Fragment {
    MapView mMapView;
    private GoogleMap googleMap;
    GpsLocation gpsLocation;
    double longitude, latitude;
    private ProgressDialog pDialog;
    ArrayList<MySchool> al_school = new ArrayList<MySchool>();
    ArrayList<MyCollege> al_college = new ArrayList<MyCollege>();
    ArrayList<MyUniversity> al_university = new ArrayList<MyUniversity>();

    private static final String TAG = NearMe.class.getSimpleName();

    private static final String urlSchool = "http://www.myeducationhunt.com/api/v1/schools";
    private static final String urlCollege = "http://www.myeducationhunt.com/api/v1/colleges";
    private static final String urlUniversity = "http://www.myeducationhunt.com/api/v1/universities";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_near_me, container, false);


        if (isConnected()) {
            mMapView = (MapView) v.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);

            mMapView.onResume();

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            gpsLocation = new GpsLocation(getContext());


            if (gpsLocation.canGetLocation()) {
                longitude = gpsLocation.getLongitude();
                latitude = gpsLocation.getLatitude();

                Toast.makeText(getContext(), "latitude:" + latitude + "Longitude:" + longitude, Toast.LENGTH_LONG).show();
            }


            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Loading…");
            pDialog.show();


            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;

                    LatLng schoollatlng = new LatLng(latitude, longitude);
                    googleMap.addMarker(new MarkerOptions().position(schoollatlng).title("MyLocation"));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(schoollatlng).zoom(11).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    drawSchoolMarker();
                    drawCollegeMarker();
                    drawUniversityMarker();
                }
            });

        } else {

            Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
        }

        return v;
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void drawSchoolMarker() {

        JsonArrayRequest schoolRequest = new JsonArrayRequest(urlSchool,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                MySchool school = new MySchool();

                                school.setId("" + obj.getInt("id"));
                                school.setName("" + obj.getString("name"));
                                school.setLatitude(Double.parseDouble("" + obj.getDouble("latitude")));
                                school.setLongitude(Double.parseDouble("" + obj.getDouble("longitude")));


                                al_school.add(school);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //iterate from arraylist
                        for (MySchool school : al_school) {

                            View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
                            TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
                            numTxt.setText(school.getName());
                            LatLng latlng = new LatLng(school.getLatitude(), school.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getContext(), marker))).title(school.getName()));
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(schoolRequest);
    }

    private void drawCollegeMarker() {
        JsonArrayRequest collegeRequest = new JsonArrayRequest(urlCollege,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                MyCollege college = new MyCollege();

                                college.setId("" + obj.getInt("id"));
                                college.setName("" + obj.getString("name"));
                                college.setLatitude(Double.parseDouble("" + obj.getDouble("latitude")));
                                college.setLongitude(Double.parseDouble("" + obj.getDouble("longitude")));


                                al_college.add(college);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //iterate from arraylist
                        for (MyCollege college : al_college) {

                            View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_college, null);
                            TextView numTxt = (TextView) marker.findViewById(R.id.txt_college);
                            numTxt.setText(college.getName());
                            LatLng latlng = new LatLng(college.getLatitude(), college.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getContext(), marker))).title(college.getName()));
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(collegeRequest);
    }

    private void drawUniversityMarker() {
        JsonArrayRequest uniRequest = new JsonArrayRequest(urlUniversity,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                MyUniversity university = new MyUniversity();

                                university.setId("" + obj.getInt("id"));
                                university.setName("" + obj.getString("name"));
                                university.setLatitude(Double.parseDouble("" + obj.getDouble("latitude")));
                                university.setLongitude(Double.parseDouble("" + obj.getDouble("longitude")));


                                al_university.add(university);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        //iterate from arraylist
                        for (MyUniversity university : al_university) {

                            View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_university, null);
                            TextView numTxt = (TextView) marker.findViewById(R.id.txt_university);
                            numTxt.setText(university.getName());
                            LatLng latlng = new LatLng(university.getLatitude(), university.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getContext(), marker))).title(university.getName()));
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(uniRequest);
    }

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}