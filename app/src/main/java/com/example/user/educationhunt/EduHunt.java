package com.example.user.educationhunt;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.educationhunt.fragment.Favourites;
import com.example.user.educationhunt.fragment.Home;
import com.example.user.educationhunt.fragment.Register;
import com.example.user.educationhunt.fragment.Search;
import com.example.user.educationhunt.fragment.Settings;

public class EduHunt extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_hunt);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

        // Find our drawer view
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);


    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.home:
                fragmentClass = Home.class;
                break;
            case R.id.search:
                fragmentClass = Search.class;
                break;
            case R.id.fav:
                fragmentClass = Favourites.class;
                break;
            case R.id.settings:
                fragmentClass = Settings.class;
                break;
            case R.id.register:
                fragmentClass = Register.class;
                break;
            default:
                fragmentClass = Home.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()){
            case R.id.our_team:
                final Dialog dialog=new Dialog(this);
                dialog.setContentView(R.layout.activity_our_team);
                dialog.getWindow().setLayout(650,1100);
                dialog.show();
                return true;
            case R.id.feedback:
                final Dialog dialog1=new Dialog(this);
                dialog1.setContentView(R.layout.activity_feedback);
                EditText feedbackName= (EditText) dialog1.findViewById(R.id.feedback_name);
                EditText feedbackEmail= (EditText) dialog1.findViewById(R.id.feedback_email);
                EditText feedbackComment= (EditText) dialog1.findViewById(R.id.feedback_comment);
                Button btnFeedback= (Button) dialog1.findViewById(R.id.btn_feedback_send);

               btnFeedback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText feedbackName= (EditText) dialog1.findViewById(R.id.feedback_name);
                        EditText feedbackEmail= (EditText) dialog1.findViewById(R.id.feedback_email);
                        EditText feedbackComment= (EditText) dialog1.findViewById(R.id.feedback_comment);
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                "mailto", "meramesh111@outlook.com", null));
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, feedbackComment.getText()+"\n"+ "\nBest Regards,\n"+ feedbackName.getText()
                        );

                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(EduHunt.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                        feedbackName.getText().clear();
                        feedbackEmail.getText().clear();
                        feedbackComment.getText().clear();
                        dialog1.dismiss();


                    }
                });
                dialog1.getWindow().setLayout(650,1100);
                dialog1.show();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.education_hunt, menu);
        return true;
    }
}
