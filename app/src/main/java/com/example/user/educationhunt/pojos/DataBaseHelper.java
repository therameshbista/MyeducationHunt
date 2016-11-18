package com.example.user.educationhunt.pojos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 11/16/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    static final String Dbase = "BankListInfo";
    static final int version = 1;

    String createTableBank = "Create table if not exists `Bank` ("
            + "`name`	TEXT," + "`email`	TEXT," + "`location`	TEXT,"
            + "`updated_at`	TEXT,"
            + "`created_at`	TEXT," + "`email`	TEXT,"
            + "`website`	TEXT," + "`logo`	TEXT);";

    public DataBaseHelper(Context context) {
        super(context, Dbase, null, version);
        // TODO Auto-generated constructor stub
        getWritableDatabase().execSQL(createTableBank);
    }

    public void CreateTable() {
    }

    public void insertSchoolInfo(OurSchool ourSchool) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", ourSchool.schoolName);
        content.put("location", ourSchool.schoolLocation);
        content.put("email", ourSchool.schoolEmail);
        content.put("created_at", ourSchool.createdAt);
        content.put("updated_at", ourSchool.updatedAt);
        content.put("website", ourSchool.schoolWebsite);
        content.put("logo", ourSchool.schoolLogo);

        db.insert("OurSchool", null, content);
    }

    public ArrayList<OurSchool> getSchoolList() {
        String sql = "select * from OurSchool ";
        ArrayList<OurSchool> schoolsList = new ArrayList<OurSchool>();

        Cursor c = getWritableDatabase().rawQuery(sql, null);
        while (c.moveToNext()) {
            OurSchool info = new OurSchool();
            info.schoolName = c.getString(c.getColumnIndex("name"));
            info.schoolLocation = c.getString(c.getColumnIndex("location"));
            info.schoolEmail = c.getString(c.getColumnIndex("email"));
            info.createdAt = c.getString(c.getColumnIndex("created_at"));
            info.updatedAt = c.getString(c.getColumnIndex("updated_at"));
            info.schoolWebsite = c.getString(c.getColumnIndex("website"));
            info.schoolLogo = c.getString(c.getColumnIndex("logo"));
            schoolsList.add(info);
        }
        c.close();
        return schoolsList;
    }
    public  OurSchool  getSchoolInfo(String SchoolId) {
        String sql = "select * from Bank  where id='"+SchoolId+"'";

        Cursor c = getWritableDatabase().rawQuery(sql, null);
        while (c.moveToNext()) {
            OurSchool info = new OurSchool();
            info.schoolName = c.getString(c.getColumnIndex("name"));
            info.schoolLocation = c.getString(c.getColumnIndex("location"));
            info.schoolEmail = c.getString(c.getColumnIndex("email"));
            info.createdAt = c.getString(c.getColumnIndex("created_at"));
            info.updatedAt = c.getString(c.getColumnIndex("updated_at"));
            info.schoolWebsite = c.getString(c.getColumnIndex("website"));
            info.schoolLogo = c.getString(c.getColumnIndex("logo"));
//			banklist.add(info);
        }
        c.close();
        OurSchool info = null;
        return info;
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
