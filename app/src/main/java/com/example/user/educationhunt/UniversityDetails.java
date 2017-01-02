package com.example.user.educationhunt;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.educationhunt.database.DatabaseHelper;
import com.example.user.educationhunt.fragment.About;
import com.example.user.educationhunt.fragment.Admission;
import com.example.user.educationhunt.fragment.FeeStructure;
import com.example.user.educationhunt.listner.DatabaseUpdatedListener;
import com.example.user.educationhunt.pojos.Bookmarkitem;
import com.example.user.educationhunt.pojos.OurUniversity;

import java.util.ArrayList;
import java.util.List;

public class UniversityDetails extends AppCompatActivity implements DatabaseUpdatedListener {

    private Toolbar toolbar;
    private TabLayout tabLayoutUniversity;
    private ViewPager viewPagerUniversity;
    Boolean isStarFilled = false;
    DatabaseHelper db;
    OurUniversity ourUniversity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_details);

        ourUniversity = (OurUniversity) getIntent().getSerializableExtra("university");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(ourUniversity.getUniversityName());

        db = new DatabaseHelper(this);
        db.databaseUpdatedListener = this;

        viewPagerUniversity = (ViewPager) findViewById(R.id.viewpagerUniversity);
        setupViewPager(viewPagerUniversity);

        tabLayoutUniversity = (TabLayout) findViewById(R.id.tabsUniversity);
        tabLayoutUniversity.setupWithViewPager(viewPagerUniversity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_school, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.star_School:

                Bookmarkitem bookmarkitem = new Bookmarkitem();
                bookmarkitem.setBookmarkID(ourUniversity.getUniversityId());
                bookmarkitem.setName(ourUniversity.getUniversityName());
                bookmarkitem.setLogo(ourUniversity.getUniversityLogo());
                bookmarkitem.setAddress(ourUniversity.getUniversityAddress());
                db.addSchoolBookmark(bookmarkitem, item); //saving to database

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new About(), "ABOUT US");
        adapter.addFragment(new Admission(), "ADMISSION");
        adapter.addFragment(new FeeStructure(), "FEE");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        List<Bookmarkitem> bookmarkitems = db.getAllSchoolBookmark();
        if (bookmarkitems.size() != 0) {
            for (Bookmarkitem bookmarkitem : bookmarkitems) {
                if (bookmarkitem.getBookmarkID() == ourUniversity.getUniversityId()) {
                    isStarFilled = true;
                    break;
                }
            }
            if (isStarFilled) {
                menu.getItem(0).setIcon(getResources().getDrawable(R.mipmap.starfilled));
            }
        }
        return true;
    }

    @Override
    public void setDatabaseSuccess(String schoolName, MenuItem item) {
        Toast.makeText(this, schoolName + "successfully added as bookmark", Toast.LENGTH_SHORT).show();
        item.setIcon(R.mipmap.starfilled);
    }

    @Override
    public void setDatabaseError(String failureMessage) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_SHORT).show();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
