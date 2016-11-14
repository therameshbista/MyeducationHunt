package com.example.user.educationhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Feedback extends AppCompatActivity {

    EditText feedbackName,feedbackEmail,feedbackComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setTitle("Feedback");

        feedbackName= (EditText) findViewById(R.id.feedback_name);
        feedbackEmail= (EditText) findViewById(R.id.feedback_name);
        feedbackComment= (EditText) findViewById(R.id.feedback_name);
    }
}
