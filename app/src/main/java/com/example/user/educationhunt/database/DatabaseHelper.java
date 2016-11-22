package com.example.user.educationhunt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.educationhunt.pojos.Bookmarkitem;

import java.util.ArrayList;

/**
 * Created by user on 11/22/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    static final String Dbase = "BookmarkDatabase";
    static final int version = 1;

    String createTableBookmark = "Create table if not exists `Bookmark` ("
            + "`name`	TEXT," + "`location`	TEXT," + "`logo`	TEXT);";

    public DatabaseHelper(Context context) {
        super(context, Dbase, null, version);
        // TODO Auto-generated constructor stub
        getWritableDatabase().execSQL(createTableBookmark);
    }

    public void CreateTable() {
    }

    public void insertBookmarkData(Bookmarkitem bookmarkitem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", bookmarkitem.name);
        content.put("location", bookmarkitem.address);
        content.put("logo", bookmarkitem.logo);

        db.insert("Bookmark", null, content);
    }

    public ArrayList<Bookmarkitem> getBookmarkist() {
        String sql = "select * from Bookmark ";
        ArrayList<Bookmarkitem> bookmarklist = new ArrayList<Bookmarkitem>();

        Cursor c = getWritableDatabase().rawQuery(sql, null);
        while (c.moveToNext()) {
            Bookmarkitem info = new Bookmarkitem();
            info.name = c.getString(c.getColumnIndex("name"));
            info.address = c.getString(c.getColumnIndex("location"));
            info.logo = c.getString(c.getColumnIndex("logo"));
            bookmarklist.add(info);
        }
        c.close();
        return bookmarklist;
    }
    public  Bookmarkitem  getBookmarkData(String bookmarkName) {
        String sql = "select * from Bookmark  where id='"+bookmarkName+"'";

        Cursor c = getWritableDatabase().rawQuery(sql, null);
        while (c.moveToNext()) {
            Bookmarkitem info = new Bookmarkitem();
            info.name = c.getString(c.getColumnIndex("name"));
            info.address = c.getString(c.getColumnIndex("location"));
            info.logo = c.getString(c.getColumnIndex("logo"));
        }
        c.close();
        Bookmarkitem info = null;
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

