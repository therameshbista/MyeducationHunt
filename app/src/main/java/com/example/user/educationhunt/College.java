package com.example.user.educationhunt;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.user.educationhunt.adapter.CollegeListAdapter;
import com.example.user.educationhunt.pojos.OurCollege;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class College extends AppCompatActivity {

    AQuery aq;
    ListView lstCollegeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Colleges");

        aq = new AQuery(this);

        aq.ajax("http://myeducationhunt.com/public/schools",


                JSONArray.class, new AjaxCallback<JSONArray>() {
                    @Override
                    public void callback(String url, JSONArray object,
                                         AjaxStatus status) {
                        // TODO Auto-generated method stub
                        super.callback(url, object, status);

                        System.out.println("REsponse:" + object.toString());
                        Outputdisplay(parseCollegeInfo(object));

                    }
                });
    }

    private ArrayList<OurCollege>parseCollegeInfo(JSONArray arr){
        ArrayList<OurCollege> list=new  ArrayList<OurCollege>();
        try{
            for (int i = 0; i < arr.length(); i++) {

                JSONObject obj = arr.getJSONObject(i);

                OurCollege info=new OurCollege();
                info.id=obj.getString("id");
                info.name= obj.getString("name");
                info.collegeLogo=(obj.getString("logo"));
                info.createdAt=(obj.getString("created_at"));
                info.updatedAt=(obj.getString("updated_at"));
                info.website=(obj.getString("website"));
                info.email=(obj.getString("email"));
                info.location=(obj.getString("location"));

                list.add(info);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    private void Outputdisplay(final ArrayList<OurCollege>list){


        CollegeListAdapter adapter=new CollegeListAdapter(this,0, list);
        lstCollegeInfo.setAdapter(adapter);
        lstCollegeInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                Intent intent=new Intent(College.this,CollegeDetail.class);
                System.out.println("BankId:"+list.get(position).id);
                intent.putExtra("id", list.get(position).id);
                intent.putExtra("name", list.get(position).name);
                intent.putExtra("createdAt", list.get(position).createdAt);
                intent.putExtra("logo", list.get(position).collegeLogo);
                intent.putExtra("location", list.get(position).location);
                intent.putExtra("email", list.get(position).email);
                intent.putExtra("updatedAt", list.get(position).updatedAt);
                intent.putExtra("website", list.get(position).website);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
