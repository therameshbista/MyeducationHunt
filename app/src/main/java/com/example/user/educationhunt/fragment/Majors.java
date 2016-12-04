package com.example.user.educationhunt.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.OurCollege;
import com.example.user.educationhunt.pojos.OurUniversity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Majors extends Fragment {

    ArrayAdapter adapter;
    ArrayList<String> items;

    //    private static final String url = "http://myeducationhunt.com/public/schools";
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_majors, container, false);

        ListView listView = (ListView) view.findViewById(R.id.list_college_majors);
        items = new ArrayList<String>();
        adapter = new ArrayAdapter(getActivity(), R.layout.college_item_layout, R.id.txt, items);
        listView.setAdapter(adapter);
        OurCollege ourCollege = null;
        OurUniversity ourUniversity = null;
        if (getActivity().getIntent().getSerializableExtra("college") != null) {
            ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
            items.add(ourCollege.getNameCollege());
        } else {
            ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
            items.add(ourUniversity.getUniversityName());
        }

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
