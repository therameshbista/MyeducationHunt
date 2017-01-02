package com.example.user.educationhunt;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.educationhunt.adapter.NothingSelectedSpinnerAdapter;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CollegeRegistration extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    ImageView collegeLogoUpload;
    private EditText collegeEstDate, collegeAdmissionStartDate, collegeAdmissionEndDate;
    private DatePickerDialog datePickerDialog, collegeAdmissionStartDatePicker, collegeAdmissionEndDatePicker;
    private SimpleDateFormat dateFormatter;
    EditText collegeName, collegeAddress, collegePhone, collegeEmail, collegeWebsite, collegeAffiliation, collegeProgram, collegeFee, collegeProgram1, collegeFee1, collegeProgram2, collegeFee2,
            collegeProgram3, collegeFee3, collegeProgram4, collegeFee4, collegeProgram5, collegeFee5, collegeProgram6, collegeFee6, collegeProgram7, collegeFee7, collegeProgram8, collegeFee8, collegeProgram9, collegeFee9;
    LinearLayout addCollegeProgram, addCollegeProgram1, addCollegeProgram2, addCollegeProgram3, addCollegeProgram4, addCollegeProgram5, addCollegeProgram6, addCollegeProgram7, addCollegeProgram8, addCollegeProgram9;
    TextView addMoreColleges;
    Spinner spinner_district, spinner_country, spinner_institution, spinner_college_level, spinner_college_duration, spinner_college_level1, spinner_college_duration1,
            spinner_college_level2, spinner_college_duration2, spinner_college_level3, spinner_college_duration3, spinner_college_level4, spinner_college_duration4, spinner_college_level5,
            spinner_college_duration5, spinner_college_level6, spinner_college_duration6, spinner_college_level7, spinner_college_duration7, spinner_college_level8, spinner_college_duration8,
            spinner_college_level9, spinner_college_duration9;

    Button sendCollegeData, cancelCollegeData;

    String level1,level2,level3,level4,level5,level6,level7,level8,level9,program1,program2,program3,program4,program5,program6,program7,program8,program9,duration1,duration2,duration3,duration4,
    duration5,duration6,duration7,duration8,duration9,fee1,fee2,fee3,fee4,fee5,fee6,fee7,fee8,fee9;
    private static int RESULT_LOAD_IMAGE;

    private static final String SERVER_ADDRESS="http://www.myeducationhunt.com/api/v1/colleges";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_registration);

        sendCollegeData = (Button) findViewById(R.id.send_college_data);
        cancelCollegeData = (Button) findViewById(R.id.cancel_college_data);
        sendCollegeData.setOnClickListener(this);
        cancelCollegeData.setOnClickListener(this);

        collegeName = (EditText) findViewById(R.id.register_college_name);
        collegeAddress = (EditText) findViewById(R.id.register_college_address);
        collegePhone = (EditText) findViewById(R.id.register_college_phone);
        collegeEmail = (EditText) findViewById(R.id.register_college_email);
        collegeWebsite = (EditText) findViewById(R.id.register_college_website);
        collegeAffiliation = (EditText) findViewById(R.id.register_college_edittext_affilation);

        collegeProgram = (EditText) findViewById(R.id.college_program_name);
        collegeProgram1 = (EditText) findViewById(R.id.college_program_name1);
        collegeProgram2 = (EditText) findViewById(R.id.college_program_name2);
        collegeProgram3 = (EditText) findViewById(R.id.college_program_name3);
        collegeProgram4 = (EditText) findViewById(R.id.college_program_name4);
        collegeProgram5 = (EditText) findViewById(R.id.college_program_name5);
        collegeProgram6 = (EditText) findViewById(R.id.college_program_name6);
        collegeProgram7 = (EditText) findViewById(R.id.college_program_name7);
        collegeProgram8 = (EditText) findViewById(R.id.college_program_name8);
        collegeProgram9 = (EditText) findViewById(R.id.college_program_name9);

        collegeFee = (EditText) findViewById(R.id.college_program_fee);
        collegeFee1 = (EditText) findViewById(R.id.college_annual_fee1);
        collegeFee2 = (EditText) findViewById(R.id.college_annual_fee2);
        collegeFee3 = (EditText) findViewById(R.id.college_annual_fee3);
        collegeFee4 = (EditText) findViewById(R.id.college_annual_fee4);
        collegeFee5 = (EditText) findViewById(R.id.college_annual_fee5);
        collegeFee6 = (EditText) findViewById(R.id.college_annual_fee6);
        collegeFee7 = (EditText) findViewById(R.id.college_annual_fee7);
        collegeFee8 = (EditText) findViewById(R.id.college_annual_fee8);
        collegeFee9 = (EditText) findViewById(R.id.college_annual_fee9);

        addCollegeProgram = (LinearLayout) findViewById(R.id.addCollegeProgram);
        addCollegeProgram1 = (LinearLayout) findViewById(R.id.addCollegeProgram1);
        addCollegeProgram2 = (LinearLayout) findViewById(R.id.addCollegeProgram2);
        addCollegeProgram3 = (LinearLayout) findViewById(R.id.addCollegeProgram3);
        addCollegeProgram4 = (LinearLayout) findViewById(R.id.addCollegeProgram4);
        addCollegeProgram5 = (LinearLayout) findViewById(R.id.addCollegeProgram5);
        addCollegeProgram6 = (LinearLayout) findViewById(R.id.addCollegeProgram6);
        addCollegeProgram7 = (LinearLayout) findViewById(R.id.addCollegeProgram7);
        addCollegeProgram8 = (LinearLayout) findViewById(R.id.addCollegeProgram8);
        addCollegeProgram9 = (LinearLayout) findViewById(R.id.addCollegeProgram9);

        addMoreColleges = (TextView) findViewById(R.id.add_more_college_programs);
        addMoreColleges.setOnClickListener(this);

        collegeLogoUpload = (ImageView) findViewById(R.id.register_college_logo);
        collegeLogoUpload.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register your College");

        findViewsById();

        setDateTimeField();

        //date Formatter
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        // Spinner for district selection
        spinner_district = (Spinner) findViewById(R.id.register_college_district);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_district, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_district.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.spinner_district,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for country selection
        spinner_country = (Spinner) findViewById(R.id.register_college_country);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_country.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter1,
                        R.layout.spinner_country,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for institution selection
        spinner_institution = (Spinner) findViewById(R.id.register_college_institution);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array_institution, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_institution.setPrompt("Day!");

        spinner_institution.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter2,
                        R.layout.spinner_institution_type,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level = (Spinner) findViewById(R.id.register_college_level);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter3,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration = (Spinner) findViewById(R.id.register_college_duration);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter4,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level1 = (Spinner) findViewById(R.id.register_college_level1);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level1.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter5,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration1 = (Spinner) findViewById(R.id.register_college_duration1);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration1.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter6,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level2 = (Spinner) findViewById(R.id.register_college_level2);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level2.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter7,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration2 = (Spinner) findViewById(R.id.register_college_duration2);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration2.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter8,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level3 = (Spinner) findViewById(R.id.register_college_level3);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level3.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter9,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration3 = (Spinner) findViewById(R.id.register_college_duration3);
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration3.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter10,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level4 = (Spinner) findViewById(R.id.register_college_level4);
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level4.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter11,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration4 = (Spinner) findViewById(R.id.register_college_duration4);
        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration4.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter12,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level5 = (Spinner) findViewById(R.id.register_college_level5);
        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level5.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter13,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration5 = (Spinner) findViewById(R.id.register_college_duration5);
        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration5.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter14,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level6 = (Spinner) findViewById(R.id.register_college_level6);
        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level6.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter15,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration6 = (Spinner) findViewById(R.id.register_college_duration6);
        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration6.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter16,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level7 = (Spinner) findViewById(R.id.register_college_level7);
        ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level7.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter17,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration7 = (Spinner) findViewById(R.id.register_college_duration7);
        ArrayAdapter<CharSequence> adapter18 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration7.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter18,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level8 = (Spinner) findViewById(R.id.register_college_level8);
        ArrayAdapter<CharSequence> adapter19 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level8.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter19,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration8 = (Spinner) findViewById(R.id.register_college_duration8);
        ArrayAdapter<CharSequence> adapter20 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration8.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter20,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_level9 = (Spinner) findViewById(R.id.register_college_level9);
        ArrayAdapter<CharSequence> adapter21 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level9.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter21,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_college_duration9 = (Spinner) findViewById(R.id.register_college_duration9);
        ArrayAdapter<CharSequence> adapter22 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration9.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter22,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

    }

    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //focus the view
                collegeName.requestFocus();
            }
        });
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

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void findViewsById() {
        collegeEstDate = (EditText) findViewById(R.id.register_college_estdate);
        collegeEstDate.setInputType(InputType.TYPE_NULL);
        collegeEstDate.requestFocus();

        collegeAdmissionStartDate = (EditText) findViewById(R.id.college_admission_startDate);
        collegeAdmissionStartDate.setInputType(InputType.TYPE_NULL);
        collegeAdmissionStartDate.requestFocus();

        collegeAdmissionEndDate = (EditText) findViewById(R.id.college_admission_end_date);
        collegeAdmissionEndDate.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        collegeEstDate.setOnClickListener(this);
        collegeAdmissionStartDate.setOnClickListener(this);
        collegeAdmissionEndDate.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                collegeEstDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        collegeAdmissionStartDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                collegeAdmissionStartDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        collegeAdmissionEndDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                collegeAdmissionEndDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_college_data:
                if (isConnected()) {
                    String name = collegeName.getText().toString();
                    String address = collegeAddress.getText().toString();
                    String phone = collegePhone.getText().toString();
                    String email = collegeEmail.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    String website = collegeWebsite.getText().toString();
                    String estbdate = collegeEstDate.getText().toString();
                    String affiliation = collegeAffiliation.getText().toString();
                    String admissionOpen = collegeAdmissionStartDate.getText().toString();
                    String admissionEnd = collegeAdmissionEndDate.getText().toString();

                    String district = null;
                    if (spinner_district != null && spinner_district.getSelectedItem() != null) {
                        district = (String) spinner_district.getSelectedItem();
                    }

                    String country = null;
                    if (spinner_country != null && spinner_country.getSelectedItem() != null) {
                        country = (String) spinner_country.getSelectedItem();
                    }

                    String institution = null;
                    if (spinner_institution != null && spinner_institution.getSelectedItem() != null) {
                        institution = (String) spinner_institution.getSelectedItem();
                    }

                    String fee = collegeFee.getText().toString();
                    String program = collegeProgram.getText().toString();
                    String level = null;
                    if (spinner_college_level != null && spinner_college_level.getSelectedItem() != null) {
                        level = (String) spinner_college_level.getSelectedItem();
                    }
                    String duration = null;
                    if (spinner_college_duration != null && spinner_college_duration.getSelectedItem() != null) {
                        duration = (String) spinner_college_duration.getSelectedItem();
                    }

                    if (addCollegeProgram1.getVisibility() == View.VISIBLE) {
                        fee1 = collegeFee1.getText().toString();
                        program1 = collegeProgram1.getText().toString();
                        level1 = null;
                        if (spinner_college_level1 != null && spinner_college_level1.getSelectedItem() != null) {
                            level1 = (String) spinner_college_level1.getSelectedItem();
                        }
                        duration1 = null;
                        if (spinner_college_duration1 != null && spinner_college_duration1.getSelectedItem() != null) {
                            duration1 = (String) spinner_college_duration1.getSelectedItem();
                        }
                    }

                    if (addCollegeProgram2.getVisibility() == View.VISIBLE) {
                        fee2 = collegeFee2.getText().toString();
                        program2 = collegeProgram2.getText().toString();
                        level2 = null;
                        if (spinner_college_level2 != null && spinner_college_level2.getSelectedItem() != null) {
                            level2 = (String) spinner_college_level2.getSelectedItem();
                        }
                        duration2 = null;
                        if (spinner_college_duration2 != null && spinner_college_duration2.getSelectedItem() != null) {
                            duration2 = (String) spinner_college_duration2.getSelectedItem();
                        }
                    }
                    if (addCollegeProgram3.getVisibility() == View.VISIBLE) {
                        fee3 = collegeFee3.getText().toString();
                        program3 = collegeProgram3.getText().toString();
                        level3 = null;
                        if (spinner_college_level3 != null && spinner_college_level3.getSelectedItem() != null) {
                            level3 = (String) spinner_college_level3.getSelectedItem();
                        }
                        duration3 = null;
                        if (spinner_college_duration3 != null && spinner_college_duration3.getSelectedItem() != null) {
                            duration3 = (String) spinner_college_duration3.getSelectedItem();
                        }
                    }

                    if (addCollegeProgram4.getVisibility() == View.VISIBLE) {
                        fee4 = collegeFee4.getText().toString();
                        program4 = collegeProgram4.getText().toString();
                        level4 = null;
                        if (spinner_college_level4 != null && spinner_college_level4.getSelectedItem() != null) {
                            level4 = (String) spinner_college_level4.getSelectedItem();
                        }
                        duration4 = null;
                        if (spinner_college_duration4 != null && spinner_college_duration4.getSelectedItem() != null) {
                            duration4 = (String) spinner_college_duration4.getSelectedItem();
                        }
                    }
                    if (addCollegeProgram5.getVisibility() == View.VISIBLE) {
                        fee5 = collegeFee5.getText().toString();
                        program5 = collegeProgram5.getText().toString();
                        level5 = null;
                        if (spinner_college_level5 != null && spinner_college_level5.getSelectedItem() != null) {
                            level5 = (String) spinner_college_level5.getSelectedItem();
                        }
                        duration5 = null;
                        if (spinner_college_duration5 != null && spinner_college_duration5.getSelectedItem() != null) {
                            duration5 = (String) spinner_college_duration5.getSelectedItem();
                        }
                    }

                    if (addCollegeProgram6.getVisibility() == View.VISIBLE) {
                        fee6 = collegeFee6.getText().toString();
                        program6 = collegeProgram6.getText().toString();
                        level6 = null;
                        if (spinner_college_level6 != null && spinner_college_level6.getSelectedItem() != null) {
                            level6 = (String) spinner_college_level6.getSelectedItem();
                        }
                        duration6 = null;
                        if (spinner_college_duration6 != null && spinner_college_duration6.getSelectedItem() != null) {
                            duration6 = (String) spinner_college_duration6.getSelectedItem();
                        }
                    }

                    if (addCollegeProgram7.getVisibility() == View.VISIBLE) {
                        fee7 = collegeFee7.getText().toString();
                        program7 = collegeProgram7.getText().toString();
                        level7 = null;
                        if (spinner_college_level7 != null && spinner_college_level7.getSelectedItem() != null) {
                            level7 = (String) spinner_college_level7.getSelectedItem();
                        }
                        duration7 = null;
                        if (spinner_college_duration7 != null && spinner_college_duration7.getSelectedItem() != null) {
                            duration7 = (String) spinner_college_duration7.getSelectedItem();
                        }
                    }

                    if (addCollegeProgram8.getVisibility() == View.VISIBLE) {
                        fee8 = collegeFee8.getText().toString();
                        program8 = collegeProgram8.getText().toString();
                        level8 = null;
                        if (spinner_college_level8 != null && spinner_college_level8.getSelectedItem() != null) {
                            level8 = (String) spinner_college_level8.getSelectedItem();
                        }
                        duration8 = null;
                        if (spinner_college_duration8 != null && spinner_college_duration8.getSelectedItem() != null) {
                            duration8 = (String) spinner_college_duration8.getSelectedItem();
                        }
                    }

                    if (addCollegeProgram9.getVisibility() == View.VISIBLE) {
                        fee9 = collegeFee9.getText().toString();
                        program9 = collegeProgram9.getText().toString();
                        level9 = null;
                        if (spinner_college_level9 != null && spinner_college_level9.getSelectedItem() != null) {
                            level9 = (String) spinner_college_level9.getSelectedItem();
                        }
                        duration9 = null;
                        if (spinner_college_duration9 != null && spinner_college_duration9.getSelectedItem() != null) {
                            duration9 = (String) spinner_college_duration9.getSelectedItem();
                        }
                    }


                    if ((name.matches("")) || (address.matches("")) || (phone.matches("")) || (email.matches("")) || (website.matches("")) || (estbdate.matches("")) || (admissionOpen.matches(""))
                            || (admissionEnd.matches("")) || (district.matches("")) || (country.matches("")) || (institution.matches("")) || (fee.matches("")) || (level.matches("")) ||
                            (collegeLogoUpload.getDrawable() == null) || (affiliation.matches("")) || (program.matches("")) || (duration.matches(""))
                            ) {

                        Toast.makeText(this, "Please fill up all the fields", Toast.LENGTH_LONG).show();

                    } else {
                        if (email.matches(emailPattern)&&((Patterns.WEB_URL.matcher(website)).matches())){
                            Bitmap image=((BitmapDrawable) collegeLogoUpload.getDrawable()).getBitmap();
                            new UploadImage(image,name,address,district,country,phone,email,website,affiliation,institution,estbdate,
                                    level,program,duration,fee,level1,program1,duration1,fee1,
                                    level2,program2,duration2,fee2,level3,program3,duration3,fee3,
                                    level4,program4,duration4,fee4,level5,program5,duration5,fee5,
                                    level6,program6,duration6,fee6,level7,program7,duration7,fee7,
                                    level8,program8,duration8,fee8,level9,program9,duration9,fee9,
                                    admissionOpen,admissionEnd).execute();
                    } else
                        Toast.makeText(CollegeRegistration.this, "Invalid website or email address", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cancel_college_data:
                this.finish();
                break;
            case R.id.register_college_estdate:
                datePickerDialog.show();
                break;
            case R.id.college_admission_startDate:
                collegeAdmissionStartDatePicker.show();
                break;
            case R.id.college_admission_end_date:
                collegeAdmissionEndDatePicker.show();
                break;
            case R.id.register_college_logo:
                Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
                break;
            case R.id.add_more_college_programs:
                if (addMoreColleges.getVisibility() == View.VISIBLE) {
                    if (addCollegeProgram9.getVisibility() == View.GONE) {
                        if (addCollegeProgram8.getVisibility() == View.GONE) {
                            if (addCollegeProgram7.getVisibility() == View.GONE) {
                                if (addCollegeProgram6.getVisibility() == View.GONE) {
                                    if (addCollegeProgram5.getVisibility() == View.GONE) {
                                        if (addCollegeProgram4.getVisibility() == View.GONE) {
                                            if (addCollegeProgram3.getVisibility() == View.GONE) {
                                                if (addCollegeProgram2.getVisibility() == View.GONE) {
                                                    if (addCollegeProgram1.getVisibility() == View.GONE) {
                                                        addCollegeProgram1.setVisibility(View.VISIBLE);
                                                    } else
                                                        addCollegeProgram2.setVisibility(View.VISIBLE);
                                                } else
                                                    addCollegeProgram3.setVisibility(View.VISIBLE);
                                            } else
                                                addCollegeProgram4.setVisibility(View.VISIBLE);
                                        } else
                                            addCollegeProgram5.setVisibility(View.VISIBLE);
                                    } else
                                        addCollegeProgram6.setVisibility(View.VISIBLE);
                                } else
                                    addCollegeProgram7.setVisibility(View.VISIBLE);
                            } else
                                addCollegeProgram8.setVisibility(View.VISIBLE);
                        } else
                            addCollegeProgram9.setVisibility(View.VISIBLE);
                    } else
                        addMoreColleges.setVisibility(View.GONE);
                }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK &&data!=null){
            Uri selectedImage=data.getData();
            collegeLogoUpload.setImageURI(selectedImage);
        }
    }

    private class UploadImage extends AsyncTask<Void,Void, Void> {

        Bitmap image;
        String name,address,district,country,phone,email,website,affiliation,type,estdate,level1,program1,duration1,fee1,level2,program2,duration2,fee2,level3,program3,duration3,fee3,level4,program4,duration4,fee4,
                level5,program5,duration5,fee5,level6,program6,duration6,fee6,level7,program7,duration7,fee7,level8,program8,duration8,fee8,level9,program9,duration9,fee9,level10,program10,duration10,fee10,
        admissionOpen,admissionClose;
        public UploadImage(Bitmap image,String name,String address,String district,String country,String phone,String email,String website,String affiliation,String type,String estdate,
                           String level1,String program1,String duration1,String fee1,
                           String level2,String program2,String duration2,String fee2,
                           String level3,String program3,String duration3,String fee3,
                           String level4,String program4,String duration4,String fee4,
                           String level5,String program5,String duration5,String fee5,
                           String level6,String program6,String duration6,String fee6,
                           String level7,String program7,String duration7,String fee7,
                           String level8,String program8,String duration8,String fee8,
                           String level9,String program9,String duration9,String fee9,
                           String level10,String program10,String duration10,String fee10,
                           String admissionOpen,String admissionClose)
        {
            this.image=image;
            this.name=name;
            this.address=address;
            this.district=district;
            this.country=country;
            this.phone=phone;
            this.email=email;
            this.website=website;
            this.affiliation=affiliation;
            this.type=type;
            this.estdate=estdate;

            this.level1=level1;
            this.program1=program1;
            this.duration1=duration1;
            this.fee1=fee1;

            this.level2=level2;
            this.program2=program2;
            this.duration2=duration2;
            this.fee2=fee2;

            this.level3=level3;
            this.program3=program3;
            this.duration3=duration3;
            this.fee3=fee3;

            this.level4=level4;
            this.program4=program4;
            this.duration4=duration4;
            this.fee4=fee4;

            this.level5=level5;
            this.program5=program5;
            this.duration5=duration5;
            this.fee5=fee5;

            this.level6=level6;
            this.program6=program6;
            this.duration6=duration6;
            this.fee6=fee6;

            this.level7=level7;
            this.program7=program7;
            this.duration7=duration7;
            this.fee7=fee7;

            this.level8=level8;
            this.program8=program8;
            this.duration8=duration8;
            this.fee8=fee8;

            this.level9=level9;
            this.program9=program9;
            this.duration9=duration9;
            this.fee9=fee9;

            this.level10=level10;
            this.program10=program10;
            this.duration10=duration10;
            this.fee10=fee10;

            this.admissionOpen=admissionOpen;
            this.admissionClose=admissionClose;

        }
        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            String encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

            ArrayList<NameValuePair> dataToSend=new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image",encodedImage));
            dataToSend.add(new BasicNameValuePair("name",name));
            dataToSend.add(new BasicNameValuePair("address",address));
            dataToSend.add(new BasicNameValuePair("district",district));
            dataToSend.add(new BasicNameValuePair("country",country));
            dataToSend.add(new BasicNameValuePair("phone",phone));
            dataToSend.add(new BasicNameValuePair("email",email));
            dataToSend.add(new BasicNameValuePair("website",website));
            dataToSend.add(new BasicNameValuePair("affiliation",affiliation));
            dataToSend.add(new BasicNameValuePair("institution_type",type));
            dataToSend.add(new BasicNameValuePair("establishment_date",estdate));

            dataToSend.add(new BasicNameValuePair("register_college_level1",level1));
            dataToSend.add(new BasicNameValuePair("register_college_program_name1",program1));
            dataToSend.add(new BasicNameValuePair("register_college_duration1",duration1));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee1",fee1));

            dataToSend.add(new BasicNameValuePair("register_college_level2",level2));
            dataToSend.add(new BasicNameValuePair("register_college_program_name2",program2));
            dataToSend.add(new BasicNameValuePair("register_college_duration2",duration2));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee2",fee2));

            dataToSend.add(new BasicNameValuePair("register_college_level3",level3));
            dataToSend.add(new BasicNameValuePair("register_college_program_name3",program3));
            dataToSend.add(new BasicNameValuePair("register_college_duration3",duration3));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee3",fee3));

            dataToSend.add(new BasicNameValuePair("register_college_level4",level4));
            dataToSend.add(new BasicNameValuePair("register_college_program_name4",program4));
            dataToSend.add(new BasicNameValuePair("register_college_duration4",duration4));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee4",fee4));

            dataToSend.add(new BasicNameValuePair("register_college_level5",level5));
            dataToSend.add(new BasicNameValuePair("register_college_program_name5",program5));
            dataToSend.add(new BasicNameValuePair("register_college_duration5",duration5));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee5",fee5));

            dataToSend.add(new BasicNameValuePair("register_college_level6",level6));
            dataToSend.add(new BasicNameValuePair("register_college_program_name6",program6));
            dataToSend.add(new BasicNameValuePair("register_college_duration6",duration6));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee6",fee6));

            dataToSend.add(new BasicNameValuePair("register_college_level7",level7));
            dataToSend.add(new BasicNameValuePair("register_college_program_name7",program7));
            dataToSend.add(new BasicNameValuePair("register_college_duration7",duration7));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee7",fee7));

            dataToSend.add(new BasicNameValuePair("register_college_level8",level8));
            dataToSend.add(new BasicNameValuePair("register_college_program_name8",program8));
            dataToSend.add(new BasicNameValuePair("register_college_duration8",duration8));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee8",fee8));

            dataToSend.add(new BasicNameValuePair("register_college_level9",level9));
            dataToSend.add(new BasicNameValuePair("register_college_program_name9",program9));
            dataToSend.add(new BasicNameValuePair("register_college_duration9",duration9));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee9",fee9));

            dataToSend.add(new BasicNameValuePair("register_college_level10",level10));
            dataToSend.add(new BasicNameValuePair("register_college_program_name10",program10));
            dataToSend.add(new BasicNameValuePair("register_college_duration10",duration10));
            dataToSend.add(new BasicNameValuePair("register_college_program_fee10",fee10));

            dataToSend.add(new BasicNameValuePair("admission_open_from",admissionOpen));
            dataToSend.add(new BasicNameValuePair("admission_open_to",admissionClose));

            HttpParams httpRequestParams=getHttpRequestParams();

            HttpClient client=new DefaultHttpClient(httpRequestParams);
            HttpPost post=new HttpPost(SERVER_ADDRESS);

            try{

                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Successfully Submitted",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"We will get back to you within 3 working days",Toast.LENGTH_SHORT).show();
        }
    }

    private HttpParams getHttpRequestParams(){

        HttpParams httpRequestParams=new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000*30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000*30);
        return httpRequestParams;
    }
}
