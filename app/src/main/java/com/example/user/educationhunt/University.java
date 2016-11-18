package com.example.user.educationhunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.user.educationhunt.adapter.CustomListAdapter;
import com.example.user.educationhunt.adapter.UniversityListAdapter;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.OurSchool;
import com.example.user.educationhunt.pojos.OurUniversity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class University extends AppCompatActivity implements SearchView.OnQueryTextListener{

    // Log tag
    private static final String TAG = University.class.getSimpleName();

    private static final String url = "http://myeducationhunt.com/public/schools";

    private ProgressDialog pDialog;
    private List<OurUniversity> ourUniversityListItems = new ArrayList<OurUniversity>();
    private ListView listViewUniversity;
    private UniversityListAdapter adapterUniversity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Universities");

        listViewUniversity = (ListView) findViewById(R.id.list_university);
        adapterUniversity = new UniversityListAdapter(this, ourUniversityListItems);
        listViewUniversity.setAdapter(adapterUniversity);

        listViewUniversity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OurSchool ourSchool=new OurSchool();
                Intent i=new Intent(University.this,UniversityDetails.class);

                i.putExtra("id", ourUniversityListItems.get(position).universityId);
                i.putExtra("name", ourUniversityListItems.get(position).universityName);
                i.putExtra("location", ourUniversityListItems.get(position).universityLocation);
                i.putExtra("logo", ourUniversityListItems.get(position).universityLogo);
                i.putExtra("email", ourUniversityListItems.get(position).universityEmail);
                i.putExtra("website", ourUniversityListItems.get(position).universityWebsite);
                i.putExtra("created_at", ourUniversityListItems.get(position).universitycreatedAt);
                i.putExtra("updated_at", ourUniversityListItems.get(position).universityupdatedAt);

                startActivity(i);

            }
        });

        pDialog = new ProgressDialog(this);
// Showing progress dialog before making http request
        pDialog.setMessage("Loading…");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest universityRequest = new JsonArrayRequest(url,
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

                                ourUniversity.universityId=obj.getInt("id");
                                ourUniversity.universityName=obj.getString("name");
                                ourUniversity.universityLocation=obj.getString("location");
                                ourUniversity.universityLogo=obj.getString("logo");
                                ourUniversity.universityEmail=obj.getString("email");
                                ourUniversity.universityWebsite=obj.getString("website");
                                ourUniversity.universitycreatedAt=obj.getString("created_at");
                                ourUniversity.universityupdatedAt=obj.getString("updated_at");

                                // adding schools to ourSchool list
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
        AppController.getInstance().addToRequestQueue(universityRequest);
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
