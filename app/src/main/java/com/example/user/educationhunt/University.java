package com.example.user.educationhunt;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
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
import com.example.user.educationhunt.adapter.UniversityListAdapter;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.Collegefees;
import com.example.user.educationhunt.pojos.OurSchool;
import com.example.user.educationhunt.pojos.OurUniversity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class University extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Log tag
    private Toolbar toolbar;
    private static final String TAG = University.class.getSimpleName();

    private static final String url = "http://www.myeducationhunt.com/api/v1/universities";

    private ProgressDialog pDialog;
    private List<OurUniversity> ourUniversityListItems = new ArrayList<OurUniversity>();
    private ListView listViewUniversity;
    private UniversityListAdapter adapterUniversity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Universities");

        if(isConnected()) {

        listViewUniversity = (ListView) findViewById(R.id.list_university);
        adapterUniversity = new UniversityListAdapter(this, ourUniversityListItems);
        listViewUniversity.setAdapter(adapterUniversity);

        listViewUniversity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(University.this, UniversityDetails.class);

                i.putExtra("university", ourUniversityListItems.get(position));
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
                                OurUniversity ourUniversity = new OurUniversity();

                                ourUniversity.universityId = obj.getInt("id");
                                ourUniversity.universityName = obj.getString("name");
                                ourUniversity.universityAddress = obj.getString("address");
                                ourUniversity.universityLogo = obj.getString("logo");
                                ourUniversity.universityDistrict=obj.getString("district");
                                ourUniversity.universityCountry=obj.getString("country");
                                ourUniversity.universityPhone=obj.getString("phone");
                                ourUniversity.universityEmail=obj.getString("email");
                                ourUniversity.universityWebsite=obj.getString("website");
                                ourUniversity.universityType=obj.getString("institution_type");
                                ourUniversity.universityestDate=obj.getString("establishment_date");
                                ourUniversity.admissionOpendate=obj.getString("admission_open_from");
                                ourUniversity.admissionEnddate=obj.getString("admission_open_to");
                                ourUniversity.universityLatitude=obj.getDouble("latitude");
                                ourUniversity.universityLongitude=obj.getDouble("longitude");
                                // adding schools to ourSchool list

                                JSONArray fees = obj.getJSONArray("fees");
                                List<Collegefees> listFeeClass = new ArrayList<Collegefees>();
                                for (int j = 0; j < fees.length(); j++) {
                                    Collegefees feeCollege = new Collegefees();
                                    JSONObject obj1 = fees.getJSONObject(j);
                                    feeCollege.setLevel(obj1.getString("level"));
                                    feeCollege.setFee(obj1.getString("fee"));
                                    feeCollege.setDuration(obj1.getString("duration"));
                                    feeCollege.setProgramName(obj1.getString("program_name"));

                                    listFeeClass.add(feeCollege);
                                }
                                ourUniversity.setFeesList(listFeeClass);
                                ourUniversityListItems.add(ourUniversity);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapterUniversity.notifyDataSetChanged();
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
        else{

            Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_LONG).show();
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
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
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
            case R.id.universitySearch:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_university, menu);

        MenuItem searchItem = menu.findItem(R.id.universitySearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listViewUniversity.clearTextFilter();
        } else {
            adapterUniversity.getFilter().filter(newText.toString());
        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        return false;
    }
}
