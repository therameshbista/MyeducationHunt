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

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SchoolRegistration extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    ImageView schoolLogoUpload;
    private EditText schoolEstDate, schoolAdmissionStartDate, schoolAdmissionEndDate;
    private DatePickerDialog datePickerDialogSchool, schoolAdmissionStartDatePicker, schoolAdmissionEndDatePicker;
    private SimpleDateFormat dateFormatterSchool;
    Spinner spinner_district, spinner_country, spinner_institution, spinner_school_level, spinner_school_level1, spinner_school_level2, spinner_school_level3, spinner_school_level4,
            spinner_school_level5, spinner_school_level6, spinner_school_level7, spinner_school_level8, spinner_school_level9;

    LinearLayout addSchoolProgram, addSchoolProgram1, addSchoolProgram2, addSchoolProgram3, addSchoolProgram4, addSchoolProgram5, addSchoolProgram6, addSchoolProgram7, addSchoolProgram8, addSchoolProgram9;
    EditText registerSchoolName, registerSchoolAddress, registerSchoolPhone, registerSchoolEmail, registerSchoolWebsite, registerSchoolFee,
            registerSchoolFee1, registerSchoolFee2, registerSchoolFee3, registerSchoolFee4, registerSchoolFee5, registerSchoolFee6, registerSchoolFee7, registerSchoolFee8, registerSchoolFee9;

    TextView addMoreSchoolProgram;

    String fee, fee1, fee2, fee3, fee4, fee5, fee6, fee7, fee8, fee9, district, country, institution, level, level1, level2, level3, level4, level5, level6, level7, level8, level9;

    Button sendSchoolForm, cancelSchoolForm;

    private static int RESULT_LOAD_IMAGE;

    private static final String SERVER_ADDRESS="http://www.myeducationhunt.com/api/v1/schools";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_registration);

        sendSchoolForm = (Button) findViewById(R.id.send_school_registration_form);
        cancelSchoolForm = (Button) findViewById(R.id.cancel_school_registration_form);
        sendSchoolForm.setOnClickListener(this);
        cancelSchoolForm.setOnClickListener(this);


        addSchoolProgram = (LinearLayout) findViewById(R.id.addSchoolProgram);
        addSchoolProgram1 = (LinearLayout) findViewById(R.id.addSchoolProgram1);
        addSchoolProgram2 = (LinearLayout) findViewById(R.id.addSchoolProgram2);
        addSchoolProgram3 = (LinearLayout) findViewById(R.id.addSchoolProgram3);
        addSchoolProgram4 = (LinearLayout) findViewById(R.id.addSchoolProgram4);
        addSchoolProgram5 = (LinearLayout) findViewById(R.id.addSchoolProgram5);
        addSchoolProgram6 = (LinearLayout) findViewById(R.id.addSchoolProgram6);
        addSchoolProgram7 = (LinearLayout) findViewById(R.id.addSchoolProgram7);
        addSchoolProgram8 = (LinearLayout) findViewById(R.id.addSchoolProgram8);
        addSchoolProgram9 = (LinearLayout) findViewById(R.id.addSchoolProgram9);

        registerSchoolName = (EditText) findViewById(R.id.register_school_name);
        registerSchoolAddress = (EditText) findViewById(R.id.register_school_address);
        registerSchoolPhone = (EditText) findViewById(R.id.register_school_phone);
        registerSchoolEmail = (EditText) findViewById(R.id.register_school_email);
        registerSchoolWebsite = (EditText) findViewById(R.id.register_school_website);
        registerSchoolFee = (EditText) findViewById(R.id.register_school_annualFee);
        registerSchoolFee1 = (EditText) findViewById(R.id.register_school_annualFee1);
        registerSchoolFee2 = (EditText) findViewById(R.id.register_school_annualFee2);
        registerSchoolFee3 = (EditText) findViewById(R.id.register_school_annualFee3);
        registerSchoolFee4 = (EditText) findViewById(R.id.register_school_annualFee4);
        registerSchoolFee5 = (EditText) findViewById(R.id.register_school_annualFee5);
        registerSchoolFee6 = (EditText) findViewById(R.id.register_school_annualFee6);
        registerSchoolFee7 = (EditText) findViewById(R.id.register_school_annualFee7);
        registerSchoolFee8 = (EditText) findViewById(R.id.register_school_annualFee8);
        registerSchoolFee9 = (EditText) findViewById(R.id.register_school_annualFee9);

        schoolLogoUpload = (ImageView) findViewById(R.id.register_school_logo);
        schoolLogoUpload.setOnClickListener(this);

        addMoreSchoolProgram = (TextView) findViewById(R.id.add_more_school_programs);
        addMoreSchoolProgram.setOnClickListener(this);

        dateFormatterSchool = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        schoolEstDate = (EditText) findViewById(R.id.register_school_estdate);
        schoolEstDate.setInputType(InputType.TYPE_NULL);
        schoolEstDate.requestFocus();

        schoolAdmissionStartDate = (EditText) findViewById(R.id.school_admission_startDate);
        schoolAdmissionStartDate.setInputType(InputType.TYPE_NULL);
        schoolAdmissionStartDate.requestFocus();

        schoolAdmissionEndDate = (EditText) findViewById(R.id.school_admission_end_date);
        schoolAdmissionEndDate.setInputType(InputType.TYPE_NULL);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register your School");

        setDateTimeField();
        setSpinnerSchoolObjects();

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //focus the view
                registerSchoolName.requestFocus();
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
    public boolean isConnected() {
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
        switch (v.getId()) {
            case R.id.send_school_registration_form:
                if (isConnected()) {
                    String name = registerSchoolName.getText().toString();
                    String address = registerSchoolAddress.getText().toString();
                    String phone = registerSchoolPhone.getText().toString();
                    String email = registerSchoolEmail.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    String website = registerSchoolWebsite.getText().toString();
                    String estbdate = schoolEstDate.getText().toString();
                    String admissionOpen = schoolAdmissionStartDate.getText().toString();
                    String admissionEnd = schoolAdmissionEndDate.getText().toString();
                    district = null;
                    if (spinner_district != null && spinner_district.getSelectedItem() != null) {
                        district = (String) spinner_district.getSelectedItem();
                    }

                    country = null;
                    if (spinner_country != null && spinner_country.getSelectedItem() != null) {
                        country = (String) spinner_country.getSelectedItem();
                    }

                    institution = null;
                    if (spinner_institution != null && spinner_institution.getSelectedItem() != null) {
                        institution = (String) spinner_institution.getSelectedItem();
                    }

                    fee = registerSchoolFee.getText().toString();
                    level = null;
                    if (spinner_school_level != null && spinner_school_level.getSelectedItem() != null) {
                        level = (String) spinner_school_level.getSelectedItem();
                    }

                    if (addSchoolProgram1.getVisibility() == View.VISIBLE) {
                        fee1 = registerSchoolFee1.getText().toString();
                        level1 = null;
                        if (spinner_school_level1 != null && spinner_school_level1.getSelectedItem() != null) {
                            level1 = (String) spinner_school_level1.getSelectedItem();
                        }
                    }

                    if (addSchoolProgram2.getVisibility() == View.VISIBLE) {
                        fee2 = registerSchoolFee2.getText().toString();
                        level2 = null;
                        if (spinner_school_level2 != null && spinner_school_level2.getSelectedItem() != null) {
                            level2 = (String) spinner_school_level2.getSelectedItem();
                        }
                    }

                    if (addSchoolProgram3.getVisibility() == View.VISIBLE) {
                        fee3 = registerSchoolFee3.getText().toString();
                        level3 = null;
                        if (spinner_school_level3 != null && spinner_school_level3.getSelectedItem() != null) {
                            level3 = (String) spinner_school_level3.getSelectedItem();
                        }
                    }

                    if (addSchoolProgram4.getVisibility() == View.VISIBLE) {
                        fee4 = registerSchoolFee4.getText().toString();
                        level4 = null;
                        if (spinner_school_level4 != null && spinner_school_level4.getSelectedItem() != null) {
                            level4 = (String) spinner_school_level4.getSelectedItem();
                        }
                    }

                    if (addSchoolProgram5.getVisibility() == View.VISIBLE) {
                        fee5 = registerSchoolFee5.getText().toString();
                        level5 = null;
                        if (spinner_school_level5 != null && spinner_school_level5.getSelectedItem() != null) {
                            level5 = (String) spinner_school_level5.getSelectedItem();
                        }
                    }

                    if (addSchoolProgram6.getVisibility() == View.VISIBLE) {
                        fee6 = registerSchoolFee6.getText().toString();
                        level6 = null;
                        if (spinner_school_level6 != null && spinner_school_level6.getSelectedItem() != null) {
                            level6 = (String) spinner_school_level6.getSelectedItem();
                        }
                    }

                    if (addSchoolProgram7.getVisibility() == View.VISIBLE) {
                        fee7 = registerSchoolFee7.getText().toString();
                        level7 = null;
                        if (spinner_school_level7 != null && spinner_school_level7.getSelectedItem() != null) {
                            level7 = (String) spinner_school_level7.getSelectedItem();
                        }
                    }

                    if (addSchoolProgram8.getVisibility() == View.VISIBLE) {
                        fee8 = registerSchoolFee8.getText().toString();
                        level8 = null;
                        if (spinner_school_level8 != null && spinner_school_level8.getSelectedItem() != null) {
                            level8 = (String) spinner_school_level8.getSelectedItem();
                        }
                    }

                    if (addSchoolProgram9.getVisibility() == View.VISIBLE) {
                        fee9 = registerSchoolFee9.getText().toString();
                        level9 = null;
                        if (spinner_school_level9 != null && spinner_school_level9.getSelectedItem() != null) {
                            level9 = (String) spinner_school_level9.getSelectedItem();
                        }
                    }

                    if ((name.matches("")) || (address.matches("")) || (phone.matches("")) || (email.matches("")) || (website.matches("")) || (estbdate.matches("")) || (admissionOpen.matches(""))
                            || (admissionEnd.matches("")) || (district.matches("")) || (country.matches("")) || (institution.matches("")) || (fee.matches("")) || (level.matches("")) ||
                            (schoolLogoUpload.getDrawable() == null)
                            ) {

                        Toast.makeText(this, "Please fill up all the fields", Toast.LENGTH_LONG).show();

                    } else {
                        if ((email.matches(emailPattern)) && ((Patterns.WEB_URL.matcher(website)).matches())) {
                            Bitmap image=((BitmapDrawable) schoolLogoUpload.getDrawable()).getBitmap();
                            new UploadImage(image,name,address,phone,email,website,district,country,institution,estbdate,fee,level,fee1,level1,fee2,level2,fee3,level3,fee4,level4,fee5,level5,fee6,level6,fee7,level7,
                                    fee8,level8,fee9,level9,admissionOpen,
                                    admissionEnd).execute();

                        } else
                            Toast.makeText(getApplicationContext(), "Invalid website and email address", Toast.LENGTH_LONG).show();
                    }
                } else {

                    Toast.makeText(this, "Please check your internet connection", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cancel_school_registration_form:
                this.finish();
                break;
            case R.id.register_school_logo:
                Intent galleryIntent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
                break;
            case R.id.register_school_estdate:
                datePickerDialogSchool.show();
                break;
            case R.id.school_admission_startDate:
                schoolAdmissionStartDatePicker.show();
                break;
            case R.id.school_admission_end_date:
                schoolAdmissionEndDatePicker.show();
                break;
            case R.id.add_more_school_programs:
                if (addMoreSchoolProgram.getVisibility() == View.VISIBLE) {
                    if (addSchoolProgram9.getVisibility() == View.GONE) {
                        if (addSchoolProgram8.getVisibility() == View.GONE) {
                            if (addSchoolProgram7.getVisibility() == View.GONE) {
                                if (addSchoolProgram6.getVisibility() == View.GONE) {
                                    if (addSchoolProgram5.getVisibility() == View.GONE) {
                                        if (addSchoolProgram4.getVisibility() == View.GONE) {
                                            if (addSchoolProgram3.getVisibility() == View.GONE) {
                                                if (addSchoolProgram2.getVisibility() == View.GONE) {
                                                    if (addSchoolProgram1.getVisibility() == View.GONE) {
                                                        addSchoolProgram1.setVisibility(View.VISIBLE);
                                                    } else
                                                        addSchoolProgram2.setVisibility(View.VISIBLE);
                                                } else
                                                    addSchoolProgram3.setVisibility(View.VISIBLE);
                                            } else
                                                addSchoolProgram4.setVisibility(View.VISIBLE);
                                        } else
                                            addSchoolProgram5.setVisibility(View.VISIBLE);
                                    } else
                                        addSchoolProgram6.setVisibility(View.VISIBLE);
                                } else
                                    addSchoolProgram7.setVisibility(View.VISIBLE);
                            } else
                                addSchoolProgram8.setVisibility(View.VISIBLE);
                        } else
                            addSchoolProgram9.setVisibility(View.VISIBLE);
                    } else
                        addMoreSchoolProgram.setVisibility(View.GONE);
                }
                break;
            case R.id.register_school_email:
        }
    }

    //Setting datepicker dialog in edittext fields
    private void setDateTimeField() {
        schoolEstDate.setOnClickListener(this);
        schoolAdmissionStartDate.setOnClickListener(this);
        schoolAdmissionEndDate.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        datePickerDialogSchool = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                schoolEstDate.setText(dateFormatterSchool.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        schoolAdmissionStartDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                schoolAdmissionStartDate.setText(dateFormatterSchool.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        schoolAdmissionEndDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                schoolAdmissionEndDate.setText(dateFormatterSchool.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    //Handling spinner Objects
    private void setSpinnerSchoolObjects() {

        spinner_school_level = (Spinner) findViewById(R.id.register_school_level);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level1 = (Spinner) findViewById(R.id.register_school_level1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level1.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter1,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level2 = (Spinner) findViewById(R.id.register_school_level2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level2.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter2,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level3 = (Spinner) findViewById(R.id.register_school_level3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level3.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter3,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level4 = (Spinner) findViewById(R.id.register_school_level4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level4.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter4,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level5 = (Spinner) findViewById(R.id.register_school_level5);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level5.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter5,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level6 = (Spinner) findViewById(R.id.register_school_level6);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level6.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter6,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level7 = (Spinner) findViewById(R.id.register_school_level7);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level7.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter7,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level8 = (Spinner) findViewById(R.id.register_school_level8);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level8.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter8,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_school_level9 = (Spinner) findViewById(R.id.register_school_level9);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_school_level9.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter9,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spinner_district = (Spinner) findViewById(R.id.register_school_district);
        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this, R.array.array_district, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_district.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter12,
                        R.layout.spinner_district,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for country selection
        spinner_country = (Spinner) findViewById(R.id.register_school_country);
        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_country.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter13,
                        R.layout.spinner_country,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for institution selection
        spinner_institution = (Spinner) findViewById(R.id.register_school_institution);
        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this, R.array.array_institution, android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_institution.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter14,
                        R.layout.spinner_institution_type,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_LOAD_IMAGE && resultCode==RESULT_OK &&data!=null){
            Uri selectedImage=data.getData();
            schoolLogoUpload.setImageURI(selectedImage);
        }
    }


    private class UploadImage extends AsyncTask<Void,Void, Void> {

        Bitmap image;
        String name,address,phone,email,website,district,country,type,estdate,fee1, level1,fee2,level2,fee3,level3,fee4,level4,fee5,level5,fee6,level6,fee7,level7,fee8,level8,fee9,level9,fee10,level10,
                admissinFrm,admissionTo;
        public UploadImage(Bitmap image,String name,String address,String phone,String email,String website,String district,String country,String type,String estdate,String fee1,String level1,
                           String fee2,String level2,String fee3,String level3,String fee4,String level4,String fee5,String level5,String fee6,String level6,String fee7,String level7,String fee8,String level8,
                           String fee9,String level9,String fee10,String level10,String admissinFrm,String admissionTo){
            this.image=image;
            this.name=name;
            this.address=address;
            this.phone=phone;
            this.email=email;
            this.website=website;
            this.district=district;
            this.country=country;
            this.type=type;
            this.estdate=estdate;
            this.fee1=fee1;
            this.level1=level1;
            this.fee2=fee2;
            this.level2=level2;
            this.fee3=fee3;
            this.level3=level3;
            this.fee4=fee4;
            this.level4=level4;
            this.fee5=fee5;
            this.level5=level5;
            this.fee6=fee6;
            this.level6=level6;
            this.fee7=fee7;
            this.level7=level7;
            this.fee8=fee8;
            this.level8=level8;
            this.fee9=fee9;
            this.level9=level9;
            this.fee10=fee10;
            this.level10=level10;
            this.admissinFrm=admissinFrm;
            this.admissionTo=admissionTo;

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
            dataToSend.add(new BasicNameValuePair("phone",phone));
            dataToSend.add(new BasicNameValuePair("email",email));
            dataToSend.add(new BasicNameValuePair("website",website));
            dataToSend.add(new BasicNameValuePair("district",district));
            dataToSend.add(new BasicNameValuePair("country",country));
            dataToSend.add(new BasicNameValuePair("institution_type",type));
            dataToSend.add(new BasicNameValuePair("establishment_date",estdate));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee1",fee1));
            dataToSend.add(new BasicNameValuePair("register_school_level1",level1));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee2",fee2));
            dataToSend.add(new BasicNameValuePair("register_school_level2",level2));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee3",fee3));
            dataToSend.add(new BasicNameValuePair("register_school_level3",level3));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee4",fee4));
            dataToSend.add(new BasicNameValuePair("register_school_level4",level4));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee5",fee5));
            dataToSend.add(new BasicNameValuePair("register_school_level5",level5));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee6",fee6));
            dataToSend.add(new BasicNameValuePair("register_school_level6",level6));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee7",fee7));
            dataToSend.add(new BasicNameValuePair("register_school_level7",level7));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee8",fee8));
            dataToSend.add(new BasicNameValuePair("register_school_level8",level8));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee9",fee9));
            dataToSend.add(new BasicNameValuePair("register_school_level9",level9));

            dataToSend.add(new BasicNameValuePair("register_school_annual_fee10",fee10));
            dataToSend.add(new BasicNameValuePair("register_school_level10",level10));

            dataToSend.add(new BasicNameValuePair("admission_open_from",admissinFrm));
            dataToSend.add(new BasicNameValuePair("admission_open_to",admissionTo));

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
            Toast.makeText(getApplicationContext()," Successfully submitted",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext()," We will get back to you within 3 working days",Toast.LENGTH_LONG).show();
        }
    }

    private HttpParams getHttpRequestParams(){

        HttpParams httpRequestParams=new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000*30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000*30);
        return httpRequestParams;
    }
}