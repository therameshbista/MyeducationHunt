package com.example.user.educationhunt;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.educationhunt.fragment.About;
import com.example.user.educationhunt.fragment.Admission;
import com.example.user.educationhunt.fragment.FeeStructure;
import com.example.user.educationhunt.fragment.Majors;
import com.example.user.educationhunt.pojos.Bookmarkitem;
import com.example.user.educationhunt.pojos.OurSchool;

import org.junit.runner.Describable;

import java.util.ArrayList;
import java.util.List;

public class SchoolDetails extends AppCompatActivity{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Boolean isStarFilled=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getExtras().getString("name"));

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav_school, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.star_School:
                if (isStarFilled) {
                    item.setIcon(R.mipmap.starfilled);
                    String Item = getIntent().getExtras().getString("name");
                    String Item1 = getIntent().getExtras().getString("location");
                    String Item2 = getIntent().getExtras().getString("logo");

                    Bookmarkitem bookmark = new Bookmarkitem();
                    Intent i = new Intent(SchoolDetails.this, Bookmark.class);

                    i.putExtra("name", Item);
                    i.putExtra("location",Item1);
                    i.putExtra("logo",Item2);

                    startActivity(i);

                    isStarFilled=false;
                }else{
                    item.setIcon(R.mipmap.star);
                    isStarFilled=true;
                }
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
