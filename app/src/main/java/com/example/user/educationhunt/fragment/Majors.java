package com.example.user.educationhunt.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.educationhunt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.user.educationhunt.R.layout.school_programs_offered;

/**
 * A simple {@link Fragment} subclass.
 */
public class Majors extends Fragment  {

    ArrayAdapter adapter;
    ArrayList<String> items;
//    private static final String url = "http://myeducationhunt.com/public/schools";
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_majors, container, false);

        ListView listView=(ListView)view.findViewById(R.id.list_college_majors);
        items=new ArrayList<String>();
        adapter=new ArrayAdapter(getActivity(), R.layout.college_item_layout,R.id.txt,items);
        listView.setAdapter(adapter);
        items.add(getActivity().getIntent().getExtras().getString("name"));
        return view;
    }

//    public void onStart(){
//        super.onStart();
//        // Create request queue
//        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
//        //  Create json array request
//        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,new Response.Listener<JSONArray>(){
//            public void onResponse(JSONArray jsonArray){
//                // Successfully download json
//                // So parse it and populate the listview
//                for(int i=0;i<jsonArray.length();i++){
//                    try {
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        items.add(jsonObject.getString("name"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                adapter.notifyDataSetChanged();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.e("Error", "Unable to parse json array");
//            }
//        });
//        // add json array request to the request queue
//        requestQueue.add(jsonArrayRequest);
//    }

}
