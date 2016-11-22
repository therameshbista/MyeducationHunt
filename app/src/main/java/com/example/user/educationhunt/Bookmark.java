package com.example.user.educationhunt;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.educationhunt.R;
import com.example.user.educationhunt.adapter.BookmarkAdapter;
import com.example.user.educationhunt.adapter.CustomListAdapter;
import com.example.user.educationhunt.database.DatabaseHelper;
import com.example.user.educationhunt.pojos.Bookmarkitem;
import com.example.user.educationhunt.pojos.OurSchool;

import java.util.ArrayList;
import java.util.List;

public class Bookmark extends AppCompatActivity {
    private List<Bookmarkitem> ourBookmarkListItems = new ArrayList<Bookmarkitem>();
    private ListView listView;
    private BookmarkAdapter adapter;
    DatabaseHelper dbhelper;
    ArrayList<Bookmarkitem>bookmarklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        dbhelper=new DatabaseHelper(this);
        bookmarklist=dbhelper.getBookmarkist();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Your Bookmark");

        listView = (ListView) findViewById(R.id.list_bookmarked);
        adapter = new BookmarkAdapter(this, ourBookmarkListItems);
        listView.setAdapter(adapter);

        Bookmarkitem bookmark=new Bookmarkitem();
        bookmark.name=getIntent().getExtras().getString("name");
        bookmark.address=getIntent().getExtras().getString("location");
        bookmark.logo=getIntent().getExtras().getString("logo");
        ourBookmarkListItems.add(bookmark);
        dbhelper.insertBookmarkData(bookmark);

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
