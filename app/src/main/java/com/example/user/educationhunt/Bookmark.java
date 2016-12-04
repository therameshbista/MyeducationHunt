package com.example.user.educationhunt;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.educationhunt.adapter.BookmarkAdapter;
import com.example.user.educationhunt.database.DatabaseHelper;
import com.example.user.educationhunt.pojos.Bookmarkitem;

import java.util.ArrayList;
import java.util.List;

public class Bookmark extends AppCompatActivity {
    private List<Bookmarkitem> ourBookmarkListItems = new ArrayList<Bookmarkitem>();
    private ListView listView;
    private BookmarkAdapter adapter;
    DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        dbhelper = new DatabaseHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Your Bookmark");

        List<Bookmarkitem> bookmarkedSchools = dbhelper.getAllSchoolBookmark();


        listView = (ListView) findViewById(R.id.list_bookmarked);
        if (bookmarkedSchools.size() != 0) {
            adapter = new BookmarkAdapter(this, bookmarkedSchools);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "You have no bookmark yet.", Toast.LENGTH_SHORT).show();
        }

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
