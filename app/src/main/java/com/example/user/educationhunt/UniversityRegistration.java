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

public class UniversityRegistration extends AppCompatActivity implements View.OnClickListener{

    ImageView universityLogoUpload;
    private EditText universityEstDate,universityAdmissionStartDate,universityAdmissionEndDate;
    private DatePickerDialog datePickerDialogUniversity,universityAdmissionStartDatePicker,universityAdmissionEndDatePicker;
    private SimpleDateFormat dateFormatterUniversity;

    private static int RESULT_LOAD_IMAGE;
    private Toolbar toolbar;

    private static final String SERVER_ADDRESS="http://www.myeducationhunt.com/api/v1/universities";

    EditText uniName,uniAddress,uniPhone,uniEmail,uniWebsite,uniProgramName,uniProgramFee,uniProgramName1,uniProgramFee1,uniProgramName2,uniProgramFee2,uniProgramName3,uniProgramFee3,uniProgramName4,uniProgramFee4
            ,uniProgramName5,uniProgramFee5,uniProgramName6,uniProgramFee6,uniProgramName7,uniProgramFee7,uniProgramName8,uniProgramFee8,uniProgramName9,uniProgramFee9;

    TextView addMoreUni;

    Spinner spinner_district,spinner_country,spinner_institution,spinner_university_level,spinner_university_duration,spinner_university_level1,spinner_university_duration1,
            spinner_university_level2,spinner_university_duration2,spinner_university_level3,spinner_university_duration3,spinner_university_level4,spinner_university_duration4,
            spinner_university_level5,spinner_university_duration5,spinner_university_level6,spinner_university_duration6,spinner_university_level7,spinner_university_duration7,
            spinner_university_level8,spinner_university_duration8,spinner_university_level9,spinner_university_duration9;
    Button sendUnivDetails,cancelUnivDetails;
    String level1,level2,level3,level4,level5,level6,level7,level8,level9,program1,program2,program3,program4,program5,program6,program7,program8,program9,duration1,duration2,duration3,duration4,
            duration5,duration6,duration7,duration8,duration9,fee1,fee2,fee3,fee4,fee5,fee6,fee7,fee8,fee9;
    LinearLayout addUniversityProgram,addUniversityProgram1,addUniversityProgram2,addUniversityProgram3,addUniversityProgram4,addUniversityProgram5,addUniversityProgram6,addUniversityProgram7,addUniversityProgram8,addUniversityProgram9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_registration);

        sendUnivDetails= (Button) findViewById(R.id.send_university_detail);
        cancelUnivDetails= (Button) findViewById(R.id.cancel_university_detail);
        sendUnivDetails.setOnClickListener(this);
        cancelUnivDetails.setOnClickListener(this);

        uniName= (EditText) findViewById(R.id.register_university_name);
        uniAddress= (EditText) findViewById(R.id.register_university_address);
        uniPhone= (EditText) findViewById(R.id.register_university_phone);
        uniEmail= (EditText) findViewById(R.id.register_university_email);
        uniWebsite= (EditText) findViewById(R.id.register_university_website);

        uniProgramName= (EditText) findViewById(R.id.universityProgramName);
        uniProgramName1= (EditText) findViewById(R.id.universityProgramName1);
        uniProgramName2= (EditText) findViewById(R.id.universityProgramName2);
        uniProgramName3= (EditText) findViewById(R.id.universityProgramName3);
        uniProgramName4= (EditText) findViewById(R.id.universityProgramName4);
        uniProgramName5= (EditText) findViewById(R.id.universityProgramName5);
        uniProgramName6= (EditText) findViewById(R.id.universityProgramName6);
        uniProgramName7= (EditText) findViewById(R.id.universityProgramName7);
        uniProgramName8= (EditText) findViewById(R.id.universityProgramName8);
        uniProgramName9= (EditText) findViewById(R.id.universityProgramName9);


        uniProgramFee= (EditText) findViewById(R.id.universityProgramFee);
        uniProgramFee1= (EditText) findViewById(R.id.universityProgramFee1);
        uniProgramFee2= (EditText) findViewById(R.id.universityProgramFee2);
        uniProgramFee3= (EditText) findViewById(R.id.universityProgramFee3);
        uniProgramFee4= (EditText) findViewById(R.id.universityProgramFee4);
        uniProgramFee5= (EditText) findViewById(R.id.universityProgramFee5);
        uniProgramFee6= (EditText) findViewById(R.id.universityProgramFee6);
        uniProgramFee7= (EditText) findViewById(R.id.universityProgramFee7);
        uniProgramFee8= (EditText) findViewById(R.id.universityProgramFee8);
        uniProgramFee9= (EditText) findViewById(R.id.universityProgramFee9);

        addUniversityProgram= (LinearLayout) findViewById(R.id.addUniversityProgram);
        addUniversityProgram1= (LinearLayout) findViewById(R.id.addUniversityProgram1);
        addUniversityProgram2= (LinearLayout) findViewById(R.id.addUniversityProgram2);
        addUniversityProgram3= (LinearLayout) findViewById(R.id.addUniversityProgram3);
        addUniversityProgram4= (LinearLayout) findViewById(R.id.addUniversityProgram4);
        addUniversityProgram5= (LinearLayout) findViewById(R.id.addUniversityProgram5);
        addUniversityProgram6= (LinearLayout) findViewById(R.id.addUniversityProgram6);
        addUniversityProgram7= (LinearLayout) findViewById(R.id.addUniversityProgram7);
        addUniversityProgram8= (LinearLayout) findViewById(R.id.addUniversityProgram8);
        addUniversityProgram9= (LinearLayout) findViewById(R.id.addUniversityProgram9);

        addMoreUni= (TextView) findViewById(R.id.add_more_university_programs);
        addMoreUni.setOnClickListener(this);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register Your University");

        universityLogoUpload= (ImageView) findViewById(R.id.register_university_logo);
        universityLogoUpload.setOnClickListener(this);

        dateFormatterUniversity = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        universityEstDate = (EditText) findViewById(R.id.register_university_estdate);
        universityEstDate.setInputType(InputType.TYPE_NULL);
        universityEstDate.requestFocus();

        universityAdmissionStartDate = (EditText) findViewById(R.id.university_admission_startDate);
        universityAdmissionStartDate.setInputType(InputType.TYPE_NULL);
        universityAdmissionStartDate.requestFocus();

        universityAdmissionEndDate = (EditText) findViewById(R.id.university_admission_end_date);
        universityAdmissionEndDate.setInputType(InputType.TYPE_NULL);

        setDateTimeField();
        setSpinnerUniversityObjects();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //focus the view
                uniName.requestFocus();
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
            case R.id.send_university_detail:
                if(isConnected()) {
                    String name = uniName.getText().toString();
                    String address = uniAddress.getText().toString();
                    String phone = uniPhone.getText().toString();
                    String email = uniEmail.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    String website = uniWebsite.getText().toString();
                    String estbdate = universityEstDate.getText().toString();
                    String admissionOpen = universityAdmissionStartDate.getText().toString();
                    String admissionEnd = universityAdmissionEndDate.getText().toString();
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

                    String fee = uniProgramFee.getText().toString();
                    String program = uniProgramName.getText().toString();
                    String level = null;
                    if (spinner_university_level != null && spinner_university_level.getSelectedItem() != null) {
                        level = (String) spinner_university_level.getSelectedItem();
                    }
                    String duration = null;
                    if (spinner_university_duration != null && spinner_university_duration.getSelectedItem() != null) {
                        duration = (String) spinner_university_duration.getSelectedItem();
                    }

                    if (addUniversityProgram1.getVisibility() == View.VISIBLE) {
                        fee1 = uniProgramFee1.getText().toString();
                        program1 = uniProgramName1.getText().toString();
                        level1 = null;
                        if (spinner_university_level1 != null && spinner_university_level1.getSelectedItem() != null) {
                            level1 = (String) spinner_university_level1.getSelectedItem();
                        }
                        duration1 = null;
                        if (spinner_university_duration1 != null && spinner_university_duration1.getSelectedItem() != null) {
                            duration1 = (String) spinner_university_duration1.getSelectedItem();
                        }
                    }

                    if (addUniversityProgram2.getVisibility() == View.VISIBLE) {
                        fee2 = uniProgramFee2.getText().toString();
                        program2 = uniProgramName2.getText().toString();
                        level2 = null;
                        if (spinner_university_level2 != null && spinner_university_level2.getSelectedItem() != null) {
                            level2 = (String) spinner_university_level2.getSelectedItem();
                        }
                        duration2 = null;
                        if (spinner_university_duration2 != null && spinner_university_duration2.getSelectedItem() != null) {
                            duration2 = (String) spinner_university_duration2.getSelectedItem();
                        }
                    }

                    if (addUniversityProgram3.getVisibility() == View.VISIBLE) {
                        fee3 = uniProgramFee3.getText().toString();
                        program3 = uniProgramName3.getText().toString();
                        level3 = null;
                        if (spinner_university_level3 != null && spinner_university_level3.getSelectedItem() != null) {
                            level3 = (String) spinner_university_level3.getSelectedItem();
                        }
                        duration3 = null;
                        if (spinner_university_duration3 != null && spinner_university_duration3.getSelectedItem() != null) {
                            duration3 = (String) spinner_university_duration3.getSelectedItem();
                        }
                    }

                    if (addUniversityProgram4.getVisibility() == View.VISIBLE) {
                        fee4 = uniProgramFee4.getText().toString();
                        program4 = uniProgramName4.getText().toString();
                        level4 = null;
                        if (spinner_university_level4 != null && spinner_university_level4.getSelectedItem() != null) {
                            level4 = (String) spinner_university_level4.getSelectedItem();
                        }
                        duration4 = null;
                        if (spinner_university_duration4 != null && spinner_university_duration4.getSelectedItem() != null) {
                            duration4 = (String) spinner_university_duration4.getSelectedItem();
                        }
                    }

                    if (addUniversityProgram5.getVisibility() == View.VISIBLE) {
                        fee5 = uniProgramFee5.getText().toString();
                        program5 = uniProgramName5.getText().toString();
                        level5 = null;
                        if (spinner_university_level5 != null && spinner_university_level5.getSelectedItem() != null) {
                            level5 = (String) spinner_university_level5.getSelectedItem();
                        }
                        duration5 = null;
                        if (spinner_university_duration5 != null && spinner_university_duration5.getSelectedItem() != null) {
                            duration5 = (String) spinner_university_duration5.getSelectedItem();
                        }
                    }

                    if (addUniversityProgram6.getVisibility() == View.VISIBLE) {
                        String fee6 = uniProgramFee6.getText().toString();
                        String program6 = uniProgramName6.getText().toString();
                        String level6 = null;
                        if (spinner_university_level6 != null && spinner_university_level6.getSelectedItem() != null) {
                            level6 = (String) spinner_university_level6.getSelectedItem();
                        }
                        String duration6 = null;
                        if (spinner_university_duration6 != null && spinner_university_duration6.getSelectedItem() != null) {
                            duration6 = (String) spinner_university_duration6.getSelectedItem();
                        }
                    }

                    if (addUniversityProgram7.getVisibility() == View.VISIBLE) {
                        fee7 = uniProgramFee7.getText().toString();
                        program7 = uniProgramName7.getText().toString();
                        level7 = null;
                        if (spinner_university_level7 != null && spinner_university_level7.getSelectedItem() != null) {
                            level7 = (String) spinner_university_level7.getSelectedItem();
                        }
                        duration7 = null;
                        if (spinner_university_duration7 != null && spinner_university_duration7.getSelectedItem() != null) {
                            duration7 = (String) spinner_university_duration7.getSelectedItem();
                        }
                    }

                    if (addUniversityProgram8.getVisibility() == View.VISIBLE) {
                        fee8 = uniProgramFee8.getText().toString();
                        program8 = uniProgramName8.getText().toString();
                        level8 = null;
                        if (spinner_university_level8 != null && spinner_university_level8.getSelectedItem() != null) {
                            level8 = (String) spinner_university_level8.getSelectedItem();
                        }
                        duration8 = null;
                        if (spinner_university_duration8 != null && spinner_university_duration8.getSelectedItem() != null) {
                            duration8 = (String) spinner_university_duration8.getSelectedItem();
                        }
                    }

                    if (addUniversityProgram9.getVisibility() == View.VISIBLE) {
                        fee9 = uniProgramFee9.getText().toString();
                        program9 = uniProgramName9.getText().toString();
                        level9 = null;
                        if (spinner_university_level9 != null && spinner_university_level9.getSelectedItem() != null) {
                            level9 = (String) spinner_university_level9.getSelectedItem();
                        }
                        duration9 = null;
                        if (spinner_university_duration9 != null && spinner_university_duration9.getSelectedItem() != null) {
                            duration9 = (String) spinner_university_duration9.getSelectedItem();
                        }
                    }

                    if ((name.matches("")) || (address.matches("")) || (phone.matches("")) || (email.matches("")) || (website.matches("")) || (estbdate.matches("")) || (admissionOpen.matches(""))
                            || (admissionEnd.matches("")) || (district.matches("")) || (country.matches("")) || (institution.matches("")) || (fee.matches("")) || (level.matches("")) ||
                            (universityLogoUpload.getDrawable() == null) || (program.matches("")) || (duration.matches(""))
                            ) {

                        Toast.makeText(this, "Please fill up all the fields", Toast.LENGTH_LONG).show();

                    } else {
                        if ((email.matches(emailPattern)) &&((Patterns.WEB_URL.matcher(website)).matches())){
                            Bitmap image=((BitmapDrawable) universityLogoUpload.getDrawable()).getBitmap();
                            new UploadImage(image,name,address,district,country,phone,email,website,institution,estbdate,
                                    level,program,duration,fee,level1,program1,duration1,fee1,
                                    level2,program2,duration2,fee2,level3,program3,duration3,fee3,
                                    level4,program4,duration4,fee4,level5,program5,duration5,fee5,
                                    level6,program6,duration6,fee6,level7,program7,duration7,fee7,
                                    level8,program8,duration8,fee8,level9,program9,duration9,fee9,
                                    admissionOpen,admissionEnd).execute();
                    } else Toast.makeText(getApplicationContext(),"Invalid website or email address",Toast.LENGTH_LONG).show();
                    }
                }
                else{

                    Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.cancel_university_detail:
                this.finish();
                break;
            case R.id.register_university_logo:
                Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
                break;
            case R.id.register_university_estdate:
                datePickerDialogUniversity.show();
                break;
            case R.id.university_admission_startDate:
                universityAdmissionStartDatePicker.show();
                break;
            case R.id.university_admission_end_date:
                universityAdmissionEndDatePicker.show();
                break;
            case R.id.add_more_university_programs:
                if (addMoreUni.getVisibility()==View.VISIBLE) {
                    if (addUniversityProgram9.getVisibility() == View.GONE) {
                        if (addUniversityProgram8.getVisibility() == View.GONE) {
                            if (addUniversityProgram7.getVisibility() == View.GONE) {
                                if (addUniversityProgram6.getVisibility() == View.GONE) {
                                    if (addUniversityProgram5.getVisibility() == View.GONE) {
                                        if (addUniversityProgram4.getVisibility() == View.GONE) {
                                            if (addUniversityProgram3.getVisibility() == View.GONE) {
                                                if (addUniversityProgram2.getVisibility() == View.GONE) {
                                                    if (addUniversityProgram1.getVisibility() == View.GONE) {
                                                        addUniversityProgram1.setVisibility(View.VISIBLE);
                                                    } else
                                                        addUniversityProgram2.setVisibility(View.VISIBLE);
                                                } else
                                                    addUniversityProgram3.setVisibility(View.VISIBLE);
                                            } else
                                                addUniversityProgram4.setVisibility(View.VISIBLE);
                                        } else
                                            addUniversityProgram5.setVisibility(View.VISIBLE);
                                    } else
                                        addUniversityProgram6.setVisibility(View.VISIBLE);
                                } else
                                    addUniversityProgram7.setVisibility(View.VISIBLE);
                            } else
                                addUniversityProgram8.setVisibility(View.VISIBLE);
                        } else
                            addUniversityProgram9.setVisibility(View.VISIBLE);
                    }else
                        addMoreUni.setVisibility(View.GONE);
                }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK &&data!=null){
            Uri selectedImage=data.getData();
            universityLogoUpload.setImageURI(selectedImage);
        }
    }




    private void setDateTimeField() {
        universityEstDate.setOnClickListener(this);
        universityAdmissionStartDate.setOnClickListener(this);
        universityAdmissionEndDate.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        datePickerDialogUniversity = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                universityEstDate.setText(dateFormatterUniversity.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        universityAdmissionStartDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                universityAdmissionStartDate.setText(dateFormatterUniversity.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        universityAdmissionEndDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                universityAdmissionEndDate.setText(dateFormatterUniversity.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setSpinnerUniversityObjects(){

        // Spinner for district selection
        spinner_district = (Spinner) findViewById(R.id.register_university_district);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_district, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_district.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.spinner_district,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for country selection
        spinner_country = (Spinner) findViewById(R.id.register_university_country);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_country.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter1,
                        R.layout.spinner_country,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for institution selection
        spinner_institution = (Spinner) findViewById(R.id.register_university_institution);
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
        spinner_university_level = (Spinner) findViewById(R.id.register_university_level);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter3,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration = (Spinner) findViewById(R.id.register_university_duration);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter4,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level1 = (Spinner) findViewById(R.id.register_university_level1);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level1.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter5,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration1 = (Spinner) findViewById(R.id.register_university_duration1);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration1.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter6,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level2= (Spinner) findViewById(R.id.register_university_level2);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level2.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter7,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration2 = (Spinner) findViewById(R.id.register_university_duration2);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration2.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter8,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level3 = (Spinner) findViewById(R.id.register_university_level3);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level3.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter9,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration3 = (Spinner) findViewById(R.id.register_university_duration3);
        ArrayAdapter<CharSequence> adapter10= ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration3.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter10,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level4 = (Spinner) findViewById(R.id.register_university_level4);
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level4.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter11,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration4 = (Spinner) findViewById(R.id.register_university_duration4);
        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration4.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter12,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level5 = (Spinner) findViewById(R.id.register_university_level5);
        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level5.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter13,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration5 = (Spinner) findViewById(R.id.register_university_duration5);
        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration5.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter14,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level6= (Spinner) findViewById(R.id.register_university_level6);
        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level6.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter15,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration6 = (Spinner) findViewById(R.id.register_university_duration6);
        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration6.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter16,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level7 = (Spinner) findViewById(R.id.register_university_level7);
        ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level7.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter17,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration7 = (Spinner) findViewById(R.id.register_university_duration7);
        ArrayAdapter<CharSequence> adapter18 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration7.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter18,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level8 = (Spinner) findViewById(R.id.register_university_level8);
        ArrayAdapter<CharSequence> adapter19 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level8.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter19,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration8= (Spinner) findViewById(R.id.register_university_duration8);
        ArrayAdapter<CharSequence> adapter20 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration8.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter20,
                        R.layout.spinner_duration,
                        // R.layout
                        this));
        // Spinner for college_level selection
        spinner_university_level9 = (Spinner) findViewById(R.id.register_university_level9);
        ArrayAdapter<CharSequence> adapter21 = ArrayAdapter.createFromResource(this, R.array.array_university_level, android.R.layout.simple_spinner_item);
        adapter21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_level9.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter21,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        spinner_university_duration9 = (Spinner) findViewById(R.id.register_university_duration9);
        ArrayAdapter<CharSequence> adapter22 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_university_duration9.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter22,
                        R.layout.spinner_duration,
                        // R.layout
                        this));


    }

    private class UploadImage extends AsyncTask<Void,Void, Void> {

        Bitmap image;
        String name,address,district,country,phone,email,website,type,estdate,level1,program1,duration1,fee1,level2,program2,duration2,fee2,level3,program3,duration3,fee3,level4,program4,duration4,fee4,
                level5,program5,duration5,fee5,level6,program6,duration6,fee6,level7,program7,duration7,fee7,level8,program8,duration8,fee8,level9,program9,duration9,fee9,level10,program10,duration10,fee10,
                admissionOpen,admissionClose;
        public UploadImage(Bitmap image,String name,String address,String district,String country,String phone,String email,String website,String type,String estdate,
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
            dataToSend.add(new BasicNameValuePair("institution_type",type));
            dataToSend.add(new BasicNameValuePair("establishment_date",estdate));

            dataToSend.add(new BasicNameValuePair("register_university_level1",level1));
            dataToSend.add(new BasicNameValuePair("register_university_program_name1",program1));
            dataToSend.add(new BasicNameValuePair("register_university_duration1",duration1));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee1",fee1));

            dataToSend.add(new BasicNameValuePair("register_university_level2",level2));
            dataToSend.add(new BasicNameValuePair("register_university_program_name2",program2));
            dataToSend.add(new BasicNameValuePair("register_university_duration2",duration2));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee2",fee2));

            dataToSend.add(new BasicNameValuePair("register_university_level3",level3));
            dataToSend.add(new BasicNameValuePair("register_university_program_name3",program3));
            dataToSend.add(new BasicNameValuePair("register_university_duration3",duration3));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee3",fee3));

            dataToSend.add(new BasicNameValuePair("register_university_level4",level4));
            dataToSend.add(new BasicNameValuePair("register_university_program_name4",program4));
            dataToSend.add(new BasicNameValuePair("register_university_duration4",duration4));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee4",fee4));

            dataToSend.add(new BasicNameValuePair("register_university_level5",level5));
            dataToSend.add(new BasicNameValuePair("register_university_program_name5",program5));
            dataToSend.add(new BasicNameValuePair("register_university_duration5",duration5));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee5",fee5));

            dataToSend.add(new BasicNameValuePair("register_university_level6",level6));
            dataToSend.add(new BasicNameValuePair("register_university_program_name6",program6));
            dataToSend.add(new BasicNameValuePair("register_university_duration6",duration6));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee6",fee6));

            dataToSend.add(new BasicNameValuePair("register_university_level7",level7));
            dataToSend.add(new BasicNameValuePair("register_university_program_name7",program7));
            dataToSend.add(new BasicNameValuePair("register_university_duration7",duration7));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee7",fee7));

            dataToSend.add(new BasicNameValuePair("register_university_level8",level8));
            dataToSend.add(new BasicNameValuePair("register_university_program_name8",program8));
            dataToSend.add(new BasicNameValuePair("register_university_duration8",duration8));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee8",fee8));

            dataToSend.add(new BasicNameValuePair("register_university_level9",level9));
            dataToSend.add(new BasicNameValuePair("register_university_program_name9",program9));
            dataToSend.add(new BasicNameValuePair("register_university_duration9",duration9));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee9",fee9));

            dataToSend.add(new BasicNameValuePair("register_university_level10",level10));
            dataToSend.add(new BasicNameValuePair("register_university_program_name10",program10));
            dataToSend.add(new BasicNameValuePair("register_university_duration10",duration10));
            dataToSend.add(new BasicNameValuePair("register_university_program_fee10",fee10));

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
            Toast.makeText(getApplicationContext(),"Successfully submitted",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"We will get back to you within 3 working days",Toast.LENGTH_LONG).show();
        }
    }

    private HttpParams getHttpRequestParams(){

        HttpParams httpRequestParams=new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000*30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000*30);
        return httpRequestParams;
    }
}
