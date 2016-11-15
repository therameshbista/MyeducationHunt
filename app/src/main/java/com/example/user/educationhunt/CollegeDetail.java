package com.example.user.educationhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;

public class CollegeDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_detail);

        System.out.println("bankId:" + getIntent().getStringExtra("id"));
        TextView id = (TextView) findViewById(R.id.id);
        id.setText(getIntent().getStringExtra("id"));
        TextView name = (TextView) findViewById(R.id.txtName);
        ImageView logo = (ImageView) findViewById(R.id.bankLogo);
        TextView location = (TextView) findViewById(R.id.collegeLocation);
        TextView create = (TextView) findViewById(R.id.createdAt);
        TextView update = (TextView) findViewById(R.id.updatedAt);
        TextView email = (TextView) findViewById(R.id.collegeEmail);
        TextView website = (TextView) findViewById(R.id.collegeWebsite);

        name.setText("name:" + getIntent().getStringExtra("name"));

        email.setText("Email:"+ getIntent().getStringExtra("email"));
        location.setText("Address:" +getIntent().getStringExtra("loacation"));
        create.setText("created_at:" +getIntent().getStringExtra("createdAt"));
        update.setText("updated_at:" + getIntent().getStringExtra("updatedAt"));
        website.setText("website:" + getIntent().getStringExtra("website"));
        AQuery aq = new AQuery(this);
        aq.id(logo).image(getIntent().getStringExtra("logo"));

    }
}
