package com.example.user.educationhunt;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
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
import com.example.user.educationhunt.adapter.CustomListAdapter;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.OurSchool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class School extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private static final String TAG = School.class.getSimpleName();

    private static final String url = "http://myeducationhunt.com/public/schools";

    private ProgressDialog pDialog;
    private List<OurSchool> ourSchoolsListItems = new ArrayList<OurSchool>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Schools");

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, ourSchoolsListItems);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                OurSchool ourSchool = new OurSchool();
                Intent i = new Intent(School.this, SchoolDetails.class);
                i.putExtra("school", ourSchoolsListItems.get(position));
                startActivity(i);

            }
        });


        pDialog = new ProgressDialog(this);
// Showing progress dialog before making http request
        pDialog.setMessage("Loading…");
        pDialog.show();

// Creating volley request obj
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
                                ourSchool.schoolLocation = obj.getString("location");
                                ourSchool.schoolLogo = obj.getString("logo");
                                ourSchool.schoolEmail = obj.getString("email");
                                ourSchool.schoolWebsite = obj.getString("website");
                                ourSchool.createdAt = obj.getString("created_at");
                                ourSchool.updatedAt = obj.getString("updated_at");

                                // adding schools to ourSchool list
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
        // this is your adapter that will be filtered
        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
        } else {
            listView.setFilterText(newText.toString());
        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // TODO Auto-generated method stub
        return false;
    }
}
