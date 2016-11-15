package com.example.user.educationhunt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.educationhunt.pojos.OurSchool;

public class Feedback extends AppCompatActivity {

    EditText feedbackName,feedbackEmail,feedbackComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        getSupportActionBar().setTitle("Feedback");


        feedbackName= (EditText) findViewById(R.id.feedback_name);
        feedbackEmail= (EditText) findViewById(R.id.feedback_email);
        feedbackComment= (EditText) findViewById(R.id.feedback_comment);

        Button btnFeedback= (Button) findViewById(R.id.btn_feedback_send);

        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedbackComment= (EditText) findViewById(R.id.feedback_comment);
                feedbackName= (EditText) findViewById(R.id.feedback_name);
                feedbackEmail= (EditText) findViewById(R.id.feedback_email);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "meramesh111@outlook.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, feedbackComment.getText()+"\n"+ "\nBest Regards,\n"+feedbackName.getText()
                        );

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Feedback.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
                feedbackName.getText().clear();
                feedbackEmail.getText().clear();
                feedbackComment.getText().clear();


            }
        });
    }
}
