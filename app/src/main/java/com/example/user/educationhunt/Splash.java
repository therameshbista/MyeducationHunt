package com.example.user.educationhunt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.user.educationhunt.fragment.Search;

import java.util.Timer;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), EduHunt.class);
                startActivity(i);

                finish();
            }
        }, 3000);
    }

//    public void showProgress(View view){
//        progress=new ProgressDialog(this);
//        progress.setMessage("Loading...");
//        progress.show();
//    }

}
