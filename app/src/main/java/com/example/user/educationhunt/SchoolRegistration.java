package com.example.user.educationhunt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.educationhunt.adapter.NothingSelectedSpinnerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SchoolRegistration extends AppCompatActivity implements View.OnClickListener {

    ImageView schoolLogoUpload;
    private static int RESULT_LOAD_IMAGE = 1;
    private EditText schoolEstDate, schoolAdmissionStartDate, schoolAdmissionEndDate;
    private DatePickerDialog datePickerDialogSchool, schoolAdmissionStartDatePicker, schoolAdmissionEndDatePicker;
    private SimpleDateFormat dateFormatterSchool;

    LinearLayout addSchoolProgram, addSchoolProgram1, addSchoolProgram2, addSchoolProgram3, addSchoolProgram4, addSchoolProgram5, addSchoolProgram6, addSchoolProgram7, addSchoolProgram8, addSchoolProgram9;
    EditText registerSchoolName, registerSchoolAddress, registerSchoolPhone, registerSchoolEmail, registerSchoolWebsite, registerSchoolFee, registerSchoolUrl,
            registerSchoolFee1, registerSchoolFee2, registerSchoolFee3, registerSchoolFee4, registerSchoolFee5, registerSchoolFee6, registerSchoolFee7, registerSchoolFee8, registerSchoolFee9;

    TextView addMoreSchoolProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_registration);


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
        registerSchoolUrl = (EditText) findViewById(R.id.register_school_map_Url);

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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Register you School");

        setDateTimeField();
        validateTextField();
        setSpinnerSchoolObjects();
    }

    private void validateTextField(){
        if( registerSchoolName.getText().toString().trim().equals(""))
        {
            registerSchoolName.setError( "First name is required!" );

            registerSchoolName.setHint("please enter SchoolName");
        }
        else {
            //do sth
        }

        if( registerSchoolAddress.getText().toString().trim().equals(""))
        {
            registerSchoolAddress.setError( "School Address is required!" );

            registerSchoolAddress.setHint("please enter School Address");
        }
        else {
            //do sth
        }

        if( registerSchoolPhone.getText().toString().trim().equals(""))
        {
            registerSchoolPhone.setError( "Phone no.is required!" );

            registerSchoolPhone.setHint("please enter phone number");
        }
        else {
            //do sth
        }

        if( registerSchoolEmail.getText().toString().trim().equals(""))
        {
            registerSchoolEmail.setError( "Email is required!" );

            registerSchoolEmail.setHint("please enter valid email");
        }
        else {
            //do sth
        }

        if( registerSchoolWebsite.getText().toString().trim().equals(""))
        {
            registerSchoolWebsite.setError( "School website is required!" );

            registerSchoolWebsite.setHint("please enter School website");
        }
        else {
            //do sth
        }

        if( registerSchoolUrl.getText().toString().trim().equals(""))
        {
            registerSchoolUrl.setError( "School Url required!" );

            registerSchoolUrl.setHint("please enter google map url");
        }
        else {
            //do sth
        }
    }
    private boolean validateEmail() {
        String email = registerSchoolEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            registerSchoolEmail.setError(getString(R.string.err_msg_email));
            requestFocus(registerSchoolEmail);
            return false;
        } else {
            registerSchoolEmail.setError(email);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
            case R.id.register_school_logo:
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
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
                if (addMoreSchoolProgram.getVisibility()==View.VISIBLE) {
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
                    }else
                    addMoreSchoolProgram.setVisibility(View.GONE);
                }
                break;
            case R.id.register_school_email:
                validateEmail();
        }
    }
                                                @Override
                                                protected void onActivityResult ( int requestCode,
                                                int resultCode, Intent data){
                                                    super.onActivityResult(requestCode, resultCode, data);
                                                    if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
                                                        Uri selectedImage = data.getData();
                                                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                                                        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                                                        cursor.moveToFirst();
                                                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                                        String picturePath = cursor.getString(columnIndex);
                                                        cursor.close();
                                                        ImageView imageView = (ImageView) findViewById(R.id.register_school_logo);
                                                        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                                    }
                                                }

                                                //Setting datepicker dialog in edittext fields
                                            private void setDateTimeField () {
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
                                            private void setSpinnerSchoolObjects () {

                                                Spinner spinner_school_level = (Spinner) findViewById(R.id.register_school_level);
                                                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level1 = (Spinner) findViewById(R.id.register_school_level1);
                                                ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level1.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter1,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level2 = (Spinner) findViewById(R.id.register_school_level2);
                                                ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level2.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter2,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level3 = (Spinner) findViewById(R.id.register_school_level3);
                                                ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level3.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter3,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level4 = (Spinner) findViewById(R.id.register_school_level4);
                                                ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level4.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter4,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level5 = (Spinner) findViewById(R.id.register_school_level5);
                                                ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level5.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter5,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level6 = (Spinner) findViewById(R.id.register_school_level6);
                                                ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level6.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter6,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level7 = (Spinner) findViewById(R.id.register_school_level7);
                                                ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level7.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter7,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level8 = (Spinner) findViewById(R.id.register_school_level8);
                                                ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level8.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter8,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_school_level9 = (Spinner) findViewById(R.id.register_school_level9);
                                                ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.array_school_level, android.R.layout.simple_spinner_item);
                                                adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_school_level9.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter9,
                                                                R.layout.spinner_level,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                Spinner spinner_district = (Spinner) findViewById(R.id.register_school_district);
                                                ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this, R.array.array_district, android.R.layout.simple_spinner_item);
                                                adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_district.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter12,
                                                                R.layout.spinner_district,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                // Spinner for country selection
                                                Spinner spinner_country = (Spinner) findViewById(R.id.register_school_country);
                                                ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this, R.array.array_country, android.R.layout.simple_spinner_item);
                                                adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner_country.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter13,
                                                                R.layout.spinner_country,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));

                                                // Spinner for institution selection
                                                Spinner spinner_institution = (Spinner) findViewById(R.id.register_school_institution);
                                                ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this, R.array.array_institution, android.R.layout.simple_spinner_item);
                                                adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                spinner_institution.setPrompt("Day!");

                                                spinner_institution.setAdapter(
                                                        new NothingSelectedSpinnerAdapter(
                                                                adapter14,
                                                                R.layout.spinner_institution_type,
                                                                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                                                                this));


                                            }
                                        }
