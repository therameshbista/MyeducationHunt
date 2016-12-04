package com.example.user.educationhunt.fragment;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class Admission extends Fragment {


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
        TextView admissionOpen = (TextView) view.findViewById(R.id.admission_form_open);

        TextView admissionClosed = (TextView) view.findViewById(R.id.admission_form_closed);

        TextView admissionContactNumber = (TextView) view.findViewById(R.id.school_admission_contact_number);

        TextView admissionContactEmail = (TextView) view.findViewById(R.id.school_admission_contact_email);


        if (getActivity().getIntent().getSerializableExtra("school") != null) {
            ourSchool = (OurSchool) getActivity().getIntent().getSerializableExtra("school");
            admissionOpen.setText(ourSchool.getCreatedAt());
            admissionOpen.setText(ourSchool.getCreatedAt());
            admissionContactNumber.setText("Contact:" + ourSchool.getCreatedAt());
            admissionContactEmail.setText("Email:" + ourSchool.getSchoolEmail());
        } else if (getActivity().getIntent().getSerializableExtra("college") != null) {
            ourCollege = (OurCollege) getActivity().getIntent().getSerializableExtra("college");
            admissionOpen.setText(ourCollege.getCreatedAtCollege());
            admissionClosed.setText(ourCollege.getCreatedAtCollege());
            admissionContactNumber.setText("Contact:" + ourCollege.getCreatedAtCollege());
            admissionContactEmail.setText("Email:" + ourCollege.getEmailCollege());
        } else {
            ourUniversity = (OurUniversity) getActivity().getIntent().getSerializableExtra("university");
            admissionOpen.setText(ourUniversity.getUniversitycreatedAt());
            admissionClosed.setText(ourUniversity.getUniversitycreatedAt());
            admissionContactNumber.setText("Contact:" + ourUniversity.getUniversitycreatedAt());
            admissionContactEmail.setText("Email:" + ourUniversity.getUniversityEmail());
        }


        return view;


    }

}
