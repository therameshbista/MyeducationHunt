package com.example.user.educationhunt.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.OurCollege;
import com.example.user.educationhunt.pojos.OurSchool;
import com.example.user.educationhunt.pojos.OurUniversity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Admission extends Fragment {

    Date date_start_school, date_end_school;
    TextView admissionOpen,admissionClosed,admissionContactNumber,admissionContactEmail,admissionStatus;
    String start,end;
    SimpleDateFormat format;

    public Admission() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admission, container, false);

        TextView textView = (TextView) view.findViewById(R.id.contactSchoolAdmission);
        SpannableString content = new SpannableString("Contact:");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        TextView textView1 = (TextView) view.findViewById(R.id.from);
        SpannableString content1 = new SpannableString("From:");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        textView1.setText(content1);

        TextView textView2 = (TextView) view.findViewById(R.id.to);
        SpannableString content2 = new SpannableString("To:");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        textView2.setText(content2);

        OurSchool ourSchool = null;
        OurCollege ourCollege = null;
        OurUniversity ourUniversity = null;
        admissionOpen = (TextView) view.findViewById(R.id.admission_form_open);

        admissionClosed = (TextView) view.findViewById(R.id.admission_form_closed);

        admissionContactNumber = (TextView) view.findViewById(R.id.school_admission_contact_number);

        admissionStatus = (TextView) view.findViewById(R.id.school_admission_status);

        admissionContactEmail = (TextView) view.findViewById(R.id.school_admission_contact_email);


        if (getActivity().getIntent().getSerializableExtra("school") != null) {
            ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
            admissionOpen.setText(ourSchool.getAdmissinOpenDate());
            admissionClosed.setText(ourSchool.getAdmissionEndDate());

            start = ourSchool.getAdmissinOpenDate();
            end = ourSchool.getAdmissionEndDate();
            format = new SimpleDateFormat("dd-MM-yyyy");

            Date date=new Date();
            try {
                date_start_school = format.parse(start);
                date_end_school = format.parse(end);
                if ((date.after(date_start_school)) && (date.before(date_end_school))){
                    admissionStatus.setText("Admission Open!!");
                    admissionStatus.setTextColor(Color.GREEN);
                } else {
                    admissionStatus.setText("Admission Closed!");
                    admissionStatus.setTextColor(Color.RED);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            admissionContactNumber.setText("Contact:" + ourSchool.getSchoolPhone());
            admissionContactEmail.setText("Email:" + ourSchool.getSchoolEmail());
        } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
            ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
            admissionOpen.setText(ourCollege.getAdmissinOpenDate());
            admissionClosed.setText(ourCollege.getAdmissionEndDate());

            start = ourCollege.getAdmissinOpenDate();
            end = ourCollege.getAdmissionEndDate();
            format = new SimpleDateFormat("dd-MM-yyyy");

            Date date=new Date();
            try {
                date_start_school = format.parse(start);
                date_end_school = format.parse(end);
                if ((date.after(date_start_school)) && (date.before(date_end_school))){
                    admissionStatus.setText("Admission Open!!");
                    admissionStatus.setTextColor(Color.GREEN);
                } else {
                    admissionStatus.setText("Admission Closed!");
                    admissionStatus.setTextColor(Color.RED);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            admissionContactNumber.setText("Contact:" + ourCollege.getCollegePhone());
            admissionContactEmail.setText("Email:" + ourCollege.getCollegeEmail());
        } else {
            ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
            admissionOpen.setText(ourUniversity.getAdmissionOpendate());
            admissionClosed.setText(ourUniversity.getAdmissionEnddate());

            start = ourUniversity.getAdmissionOpendate();
            end = ourUniversity.getAdmissionEnddate();
            format = new SimpleDateFormat("dd-MM-yyyy");

            Date date=new Date();
            try {
                date_start_school = format.parse(start);
                date_end_school = format.parse(end);
                if ((date.after(date_start_school)) && (date.before(date_end_school))){
                    admissionStatus.setText("Admission Open!!");
                    admissionStatus.setTextColor(Color.GREEN);
                } else {
                    admissionStatus.setText("Admission Closed!");
                    admissionStatus.setTextColor(Color.RED);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            admissionContactNumber.setText("Contact:" + ourUniversity.getUniversityPhone());
            admissionContactEmail.setText("Email:" + ourUniversity.getUniversityEmail());
        }
        return view;
    }
}
