package com.example.user.educationhunt;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.educationhunt.R;

import java.util.ArrayList;

public class Bookmark extends AppCompatActivity {
    ArrayAdapter adapter;
    ArrayList<String> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Your Bookmark");

//        ListView listView=(ListView)findViewById(R.id.list_bookmarked);
//        items=new ArrayList<String>();
//        adapter=new ArrayAdapter(this, R.layout.college_item_layout,R.id.txt,items);
//        listView.setAdapter(adapter);
//        items.add(this.getIntent().getExtras().getString("bookmarkname"));
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
