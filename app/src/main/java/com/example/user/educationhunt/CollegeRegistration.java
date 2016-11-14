package com.example.user.educationhunt;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.example.user.educationhunt.adapter.NothingSelectedSpinnerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CollegeRegistration extends AppCompatActivity implements View.OnClickListener{

    ImageView collegeLogoUpload;
    private EditText collegeEstDate,collegeAdmissionStartDate,collegeAdmissionEndDate;
    private DatePickerDialog datePickerDialog,collegeAdmissionStartDatePicker,collegeAdmissionEndDatePicker;
    private SimpleDateFormat dateFormatter;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_registration);

        collegeLogoUpload= (ImageView) findViewById(R.id.register_college_logo);
        collegeLogoUpload.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Register Your College");

        findViewsById();

        setDateTimeField();

        //date Formatter
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        // Spinner for district selection
        Spinner spinner_district = (Spinner) findViewById(R.id.register_college_district);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_district, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_district.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.spinner_district,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for country selection
        Spinner spinner_country = (Spinner) findViewById(R.id.register_college_country);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.array_country, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_country.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter1,
                        R.layout.spinner_country,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for country selection
        Spinner spinner_affiliation = (Spinner) findViewById(R.id.register_college_affiliation);
        ArrayAdapter<CharSequence> adapter23 = ArrayAdapter.createFromResource(this, R.array.array_affiliation, android.R.layout.simple_spinner_item);
        adapter23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_affiliation.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter23,
                        R.layout.spinner_affiliation,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for institution selection
        Spinner spinner_institution = (Spinner) findViewById(R.id.register_college_institution);
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
        Spinner spinner_college_level = (Spinner) findViewById(R.id.register_college_level);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter3,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
                        Spinner spinner_college_duration = (Spinner) findViewById(R.id.register_college_duration);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter4,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level1 = (Spinner) findViewById(R.id.register_college_level1);
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level1.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter5,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration1 = (Spinner) findViewById(R.id.register_college_duration1);
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration1.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter6,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level2 = (Spinner) findViewById(R.id.register_college_level2);
        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level2.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter7,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration2 = (Spinner) findViewById(R.id.register_college_duration2);
        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration2.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter8,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level3 = (Spinner) findViewById(R.id.register_college_level3);
        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level3.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter9,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration3 = (Spinner) findViewById(R.id.register_college_duration3);
        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration3.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter10,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level4 = (Spinner) findViewById(R.id.register_college_level4);
        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level4.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter11,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration4 = (Spinner) findViewById(R.id.register_college_duration4);
        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration4.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter12,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level5 = (Spinner) findViewById(R.id.register_college_level5);
        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level5.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter13,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration5 = (Spinner) findViewById(R.id.register_college_duration5);
        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration5.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter14,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level6 = (Spinner) findViewById(R.id.register_college_level6);
        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level6.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter15,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration6 = (Spinner) findViewById(R.id.register_college_duration6);
        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration6.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter16,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level7 = (Spinner) findViewById(R.id.register_college_level7);
        ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level7.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter17,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration7 = (Spinner) findViewById(R.id.register_college_duration7);
        ArrayAdapter<CharSequence> adapter18 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration7.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter18,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level8 = (Spinner) findViewById(R.id.register_college_level8);
        ArrayAdapter<CharSequence> adapter19 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level8.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter19,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration8 = (Spinner) findViewById(R.id.register_college_duration8);
        ArrayAdapter<CharSequence> adapter20 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration8.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter20,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_level9 = (Spinner) findViewById(R.id.register_college_level9);
        ArrayAdapter<CharSequence> adapter21 = ArrayAdapter.createFromResource(this, R.array.array_college_level, android.R.layout.simple_spinner_item);
        adapter21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_level9.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter21,
                        R.layout.spinner_level,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        // Spinner for college_level selection
        Spinner spinner_college_duration9 = (Spinner) findViewById(R.id.register_college_duration9);
        ArrayAdapter<CharSequence> adapter22 = ArrayAdapter.createFromResource(this, R.array.array_college_duration, android.R.layout.simple_spinner_item);
        adapter22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_college_duration9.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter22,
                        R.layout.spinner_duration,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

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

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        collegeAdmissionStartDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                collegeAdmissionStartDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        collegeAdmissionEndDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                collegeAdmissionEndDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
                Intent i=new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.register_college_logo);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }
}
