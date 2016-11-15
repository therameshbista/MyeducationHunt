package com.example.user.educationhunt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.user.educationhunt.R;
import com.example.user.educationhunt.pojos.OurCollege;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/14/2016.
 */
public class CollegeListAdapter extends ArrayAdapter<OurCollege> {


    public CollegeListAdapter(Context context, int resource, ArrayList<OurCollege> list) {
        super(context, resource,list);
        // TODO Auto-generated constructor stub

        this.context = context;


    }

    private Context context;
    List<OurCollege> banks;





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        OurCollege ourCollege=getItem(position);
        //AQuery aQuery=new AQuery(context);
        if(convertView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            convertView=inflater.inflate(R.layout.college_list_item, null,false);
            viewHolder=new ViewHolder();
            viewHolder.txtName=(TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtSN=(TextView) convertView.findViewById(R.id.txtSN);
            viewHolder.imgLogo=(ImageView) convertView.findViewById(R.id.bankLogo);

            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        //viewHolder.imgLogo.setImageResource(R.drawable.ic_launcher);
        AQuery aQuery=new AQuery(context);
        aQuery.id(viewHolder.imgLogo).image(ourCollege.collegeLogo);
        viewHolder.txtSN.setText(String.valueOf(position+1));
        viewHolder.txtName.setText(ourCollege.name);


        return convertView;
    }

    private class ViewHolder{
        ImageView imgLogo;
        TextView txtSN;
        TextView txtName;
    }

}

