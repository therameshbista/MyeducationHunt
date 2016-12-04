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
import com.example.user.educationhunt.fragment.Fee_College;
import com.example.user.educationhunt.fragment.Majors;
import com.example.user.educationhunt.listner.DatabaseUpdatedListener;
import com.example.user.educationhunt.pojos.Bookmarkitem;
import com.example.user.educationhunt.pojos.OurCollege;

import java.util.ArrayList;
import java.util.List;

public class CollegeDetail extends AppCompatActivity implements DatabaseUpdatedListener {
    private Toolbar toolbar;
    private TabLayout tabLayoutCollege;
    private ViewPager viewPagerCollege;
    Boolean isStarFilled = false;
    OurCollege ourCollege;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);

        ourCollege = (OurCollege) getIntent().getSerializableExtra("college");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(ourCollege.getNameCollege());

        db = new DatabaseHelper(this);
        db.databaseUpdatedListener = this;

        viewPagerCollege = (ViewPager) findViewById(R.id.viewpagerCollege);
        setupViewPager(viewPagerCollege);

        tabLayoutCollege = (TabLayout) findViewById(R.id.tabsCollege);
        tabLayoutCollege.setupWithViewPager(viewPagerCollege);
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
//                if (isStarFilled) {
//                    item.setIcon(R.mipmap.starfilled);
//                    isStarFilled=false;
//                }else{
//                    item.setIcon(R.mipmap.star);
//                    isStarFilled=true;
//                }
                Bookmarkitem bookmarkitem = new Bookmarkitem();
                bookmarkitem.setBookmarkID(ourCollege.getIdCollege());
                bookmarkitem.setName(ourCollege.getNameCollege());
                bookmarkitem.setLogo(ourCollege.getCollegeLogoCollege());
                bookmarkitem.setAddress(ourCollege.getLocationCollege());
                db.addSchoolBookmark(bookmarkitem, item); //saving to database
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new About(), "ABOUT US");
        adapter.addFragment(new Admission(), "ADMISSION");
        adapter.addFragment(new Majors(), "MAJORS");
        adapter.addFragment(new Fee_College(), "FEE");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        List<Bookmarkitem> bookmarkitems = db.getAllSchoolBookmark();
        if (bookmarkitems.size() != 0) {
            for (Bookmarkitem bookmarkitem : bookmarkitems) {
                if (bookmarkitem.getBookmarkID() == ourCollege.getIdCollege()) {
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
        // dont activate your bookmark icon
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
