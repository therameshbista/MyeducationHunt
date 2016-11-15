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


        TextView admissionOpen= (TextView) view.findViewById(R.id.admission_form_open);
        String Item = getActivity().getIntent().getExtras().getString("created_at");
        admissionOpen.setText(Item);

        TextView admissionClosed= (TextView) view.findViewById(R.id.admission_form_closed);
        String Item1 = getActivity().getIntent().getExtras().getString("created_at");
        admissionClosed.setText(Item1);

        TextView admissionContactNumber= (TextView) view.findViewById(R.id.school_admission_contact_number);
        String Item2 = getActivity().getIntent().getExtras().getString("created_at");
        admissionContactNumber.setText("Contact:"+Item2);

        TextView admissionContactEmail= (TextView) view.findViewById(R.id.school_admission_contact_email);
        String Item3 = getActivity().getIntent().getExtras().getString("email");
        admissionContactEmail.setText("Email:"+Item3);

        return view;


    }

}
