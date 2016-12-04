package com.example.user.educationhunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.user.educationhunt.adapter.CollegeListAdapter;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.OurCollege;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class College extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Log tag
    private static final String TAG = College.class.getSimpleName();

    private static final String url = "http://myeducationhunt.com/public/schools";

    private ProgressDialog pDialog;
    private List<OurCollege> ourCollegeListItems = new ArrayList<OurCollege>();
    private ListView listViewCollege;
    private CollegeListAdapter adapterCollege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Colleges");

        listViewCollege = (ListView) findViewById(R.id.list_college);
        adapterCollege = new CollegeListAdapter(this, ourCollegeListItems);
        listViewCollege.setAdapter(adapterCollege);

        listViewCollege.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OurCollege ourCollege = new OurCollege();
                Intent i = new Intent(College.this, CollegeDetail.class);

                i.putExtra("college", ourCollegeListItems.get(position));

                startActivity(i);

            }
        });
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading…");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest collegeRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                OurCollege ourCollege = new OurCollege();

                                ourCollege.idCollege = obj.getInt("id");
                                ourCollege.nameCollege = obj.getString("name");
                                ourCollege.locationCollege = obj.getString("location");
                                ourCollege.collegeLogoCollege = obj.getString("logo");
                                ourCollege.emailCollege = obj.getString("email");
                                ourCollege.websiteCollege = obj.getString("website");
                                ourCollege.createdAtCollege = obj.getString("created_at");
                                ourCollege.updatedAtCollege = obj.getString("updated_at");

                                // adding schools to ourSchool list
                                ourCollegeListItems.add(ourCollege);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapterCollege.notifyDataSetChanged();
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
            case R.id.collegeSearch:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_college, menu);

        MenuItem searchItem = menu.findItem(R.id.collegeSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // User pressed the search button
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // User changed the text
        return false;
    }
}
