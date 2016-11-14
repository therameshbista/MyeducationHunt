package com.example.user.educationhunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.user.educationhunt.fragment.About;
import com.example.user.educationhunt.pojos.AppController;
import com.example.user.educationhunt.pojos.OurSchool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class School extends AppCompatActivity{

    // Log tag
    private static final String TAG = School.class.getSimpleName();

    // Billionaires json url
    private static final String url = "http://myeducationhunt.com/public/schools";

    private ProgressDialog pDialog;
    private List<OurSchool> ourSchoolsListItems = new ArrayList<OurSchool>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Schools");


        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, ourSchoolsListItems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(School.this,SchoolDetails.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("id", ourSchoolsListItems.get(position));
                intent.putExtras(bundle);
                bundle.putInt("id",ourSchoolsListItems.get(position).schoolId);
                bundle.putString("name",ourSchoolsListItems.get(position).schoolName);
                bundle.putString("location",ourSchoolsListItems.get(position).schoolLocation);
                bundle.putString("email",ourSchoolsListItems.get(position).schoolEmail);
                bundle.putString("created_at",ourSchoolsListItems.get(position).createdAt);
                bundle.putString("updated_at",ourSchoolsListItems.get(position).updatedAt);
                bundle.putString("website",ourSchoolsListItems.get(position).schoolWebsite);

                About about=new About();
                about.setArguments(bundle);
                startActivity(intent);
            }
        });

        pDialog = new ProgressDialog(this);
// Showing progress dialog before making http request
        pDialog.setMessage("Loading…");
        pDialog.show();

// Creating volley request obj
        JsonArrayRequest billionaireReq = new JsonArrayRequest(url,
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
                                ourSchool.setSchoolName(obj.getString("name"));
                                ourSchool.setSchoolLogo(obj.getString("logo"));
                                ourSchool.setSchoolLocation(obj.getString("location"));
                                ourSchool.setSchoolEmail(obj.getString("email"));
                                ourSchool.setSchoolWebsite(obj.getString("website"));
                                ourSchool.setCreatedAt(obj.getString("created_at"));
                                ourSchool.setUpdatedAt(obj.getString("updated_at"));
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
        AppController.getInstance().addToRequestQueue(billionaireReq);
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
        return true;
    }
}
