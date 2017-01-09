package com.example.user.educationhunt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.educationhunt.adapter.BookmarkAdapter;
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
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        dbhelper = new DatabaseHelper(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bookmark");

        final List<Bookmarkitem> bookmarkedSchools = dbhelper.getAllSchoolBookmark();

        listView = (ListView) findViewById(R.id.list_bookmarked);
        if (bookmarkedSchools.size() != 0) {
            adapter = new BookmarkAdapter(this, bookmarkedSchools);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "You have no bookmark yet.", Toast.LENGTH_SHORT).show();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//             Bookmarkitem bookmarkitem= dbhelper.getBoomarkDetailByID(bookmarkedSchools.get(position).getBookmarkID()+"");
                Toast.makeText(Bookmark.this, bookmarkedSchools.get(position).getName() + "", Toast.LENGTH_SHORT).show();
                Bookmarkitem bookmarkitem = bookmarkedSchools.get(position);
                OurSchool ourSchool = new OurSchool();
                ourSchool.schoolId = bookmarkitem.getBookmarkID();
                ourSchool.schoolName = bookmarkitem.getName();
                ourSchool.schoolAddress = bookmarkitem.getAddress();
                ourSchool.schoolLogo = bookmarkitem.getLogo();
                ourSchool.schoolDistrict = bookmarkitem.getCountry();
                ourSchool.schoolCountry = bookmarkitem.getCountry();
                ourSchool.schoolPhone = bookmarkitem.getPhone();
                ourSchool.schoolEmail = bookmarkitem.getEmail();
                ourSchool.schoolWebsite = bookmarkitem.getWebsite();
                ourSchool.schoolType = bookmarkitem.getInstitution_type();
                ourSchool.estDate = bookmarkitem.getEstablishment_date();
                ourSchool.admissinOpenDate = bookmarkitem.getAdmission_open_from();
                ourSchool.admissionEndDate = bookmarkitem.getAdmission_open_to();
                ourSchool.latitude = Double.parseDouble(bookmarkitem.getLatitude());
                ourSchool.longitude = Double.parseDouble(bookmarkitem.getLongitude());

                Intent intent= new Intent(Bookmark.this,SchoolDetails.class);
                intent.putExtra("school",ourSchool);
                startActivity(intent);
            }
        });

    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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
