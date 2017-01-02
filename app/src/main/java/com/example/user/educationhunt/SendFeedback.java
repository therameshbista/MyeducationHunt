package com.example.user.educationhunt;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.educationhunt.pojos.feedbackData;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SendFeedback extends AppCompatActivity {

    private Toolbar toolbar;
    EditText feedbackName;
    EditText feedbackEmail;
    EditText feedbackComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Feedback");

        Button btnFeedback= (Button) findViewById(R.id.btn_feedback_send);



        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()) {
                    feedbackName = (EditText)findViewById(R.id.feedback_name);
                    feedbackEmail = (EditText)findViewById(R.id.feedback_email);
                    String email=feedbackEmail.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    feedbackComment = (EditText)findViewById(R.id.feedback_comment);

                    String name = feedbackName.getText().toString();

                    String comment = feedbackComment.getText().toString();
                    if ((name.matches("")) || (name.matches("")) || (email.matches("")) || (comment.matches(""))) {

                        Toast.makeText(getApplicationContext(), "Please fill up all the fields", Toast.LENGTH_LONG).show();

                    } else {
                        if (email.matches(emailPattern)) {
                            new HttpAsyncTask().execute("http://myeducationhunt.com/api/v1/post-feedback");
                        } else Toast.makeText(getApplicationContext(),"Invalid Email Address",Toast.LENGTH_LONG).show();
                    }
                }
                else {

                    Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
                }

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

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public static String POST(String url, feedbackData feedbackData){
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(url);

            String json = "";

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("name", feedbackData.getName());
            jsonObject.accumulate("email", feedbackData.getEmail());
            jsonObject.accumulate("comment", feedbackData.getComment());

            json = jsonObject.toString();

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);

            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            feedbackData feedbackData = new feedbackData();
            feedbackData.setName(feedbackName.getText().toString());
            feedbackData.setEmail(feedbackEmail.getText().toString());
            feedbackData.setComment(feedbackComment.getText().toString());

            return POST(urls[0],feedbackData);
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Feedback send successfully", Toast.LENGTH_LONG).show();
        }
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
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
