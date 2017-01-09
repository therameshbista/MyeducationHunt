package com.example.user.educationhunt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user.educationhunt.adapter.CustomListAdapter;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.FeeClass;
import com.example.user.educationhunt.pojos.OurSchool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class School extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;

    private static final String TAG = School.class.getSimpleName();

    private static final String url = "http://www.myeducationhunt.com/api/v1/schools";

    private ProgressDialog pDialog;
    private List<OurSchool> ourSchoolsListItems = new ArrayList<OurSchool>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Schools");

        if (isConnected()) {

            listView = (ListView) findViewById(R.id.list);
            adapter = new CustomListAdapter(this, ourSchoolsListItems);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent i = new Intent(School.this, SchoolDetails.class);

                    i.putExtra("school", ourSchoolsListItems.get(position));
                    startActivity(i);

                }
            });


            pDialog = new ProgressDialog(this);
// Showing progress dialog before making http request
            pDialog.setMessage("Loading…");
            pDialog.show();


            JsonArrayRequest schoolRequest = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d(TAG, response.toString());
                            hidePDialog();

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    OurSchool ourSchool = new OurSchool();

                                    ourSchool.schoolId = obj.getInt("id");
                                    ourSchool.schoolName = obj.getString("name");
                                    ourSchool.schoolAddress = obj.getString("address");
                                    ourSchool.schoolLogo = obj.getString("logo");
                                    ourSchool.schoolDistrict = obj.getString("district");
                                    ourSchool.schoolCountry = obj.getString("country");
                                    ourSchool.schoolPhone = obj.getString("phone");
                                    ourSchool.schoolEmail = obj.getString("email");
                                    ourSchool.schoolWebsite = obj.getString("website");
                                    ourSchool.schoolType = obj.getString("institution_type");
                                    ourSchool.estDate = obj.getString("establishment_date");
                                    ourSchool.admissinOpenDate = obj.getString("admission_open_from");
                                    ourSchool.admissionEndDate = obj.getString("admission_open_to");
                                    ourSchool.latitude = obj.getDouble("latitude");
                                    ourSchool.longitude = obj.getDouble("longitude");

                                    JSONArray fees = obj.getJSONArray("fees");
                                    List<FeeClass> listFeeClass = new ArrayList<FeeClass>();
                                    for (int j = 0; j < fees.length(); j++) {
                                        FeeClass feeClass = new FeeClass();
                                        JSONObject obj1 = fees.getJSONObject(j);
                                        feeClass.setGrade(obj1.getString("grade"));
                                        feeClass.setFee(obj1.getString("price"));
                                        listFeeClass.add(feeClass);
                                    }
                                    ourSchool.setFeesList(listFeeClass);
                                    ourSchoolsListItems.add(ourSchool);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            adapter.notifyDataSetChanged();
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

        } else {

            Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_LONG).show();
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.schoolSearch:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.schoolSearch).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
        } else {
            adapter.getFilter().filter(newText.toString());
        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        return false;
    }
}
