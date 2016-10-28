package com.example.user.educationhunt;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Visibility;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Help extends AppCompatActivity implements View.OnClickListener{

    LinearLayout helpStudents,helpInstitutions,forStudents,forInstitutions;
    ImageView studentsToggle,institutionsToggle;
    View divider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Help");

        studentsToggle= (ImageView) findViewById(R.id.students_toggle);
        forStudents= (LinearLayout) findViewById(R.id.for_students);
        forInstitutions= (LinearLayout) findViewById(R.id.for_institutions);

        divider=findViewById(R.id.divider);

        institutionsToggle= (ImageView) findViewById(R.id.institution_toggle);
        helpStudents= (LinearLayout) findViewById(R.id.help_student);
        helpInstitutions= (LinearLayout) findViewById(R.id.help_institution);

        helpStudents.setVisibility(View.GONE);
        helpInstitutions.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);


        forStudents.setOnClickListener(this);
        forInstitutions.setOnClickListener(this);
        studentsToggle.setOnClickListener(this);
        institutionsToggle.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.for_students:
                if (helpStudents.getVisibility()==View.GONE){
                    helpStudents.setVisibility(View.VISIBLE);
                    divider.setVisibility(View.VISIBLE);
                }else
                    helpStudents.setVisibility(View.GONE);
                    divider.setVisibility(View.GONE);
                break;
            case R.id.for_institutions:
                if (helpInstitutions.getVisibility()==View.GONE){
                    helpInstitutions.setVisibility(View.VISIBLE);
                }else helpInstitutions.setVisibility(View.GONE);

                break;

        }
    }
}
