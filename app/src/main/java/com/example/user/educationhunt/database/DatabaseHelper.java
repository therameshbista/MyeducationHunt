package com.example.user.educationhunt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.MenuItem;

import com.example.user.educationhunt.listner.DatabaseUpdatedListener;
import com.example.user.educationhunt.pojos.Bookmarkitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/22/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseUpdatedListener databaseUpdatedListener;
    static final String DATABASE_NAME = "BookmarkDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME_BOOKMARK = "bookmark";

    //    Contact table columns name
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String LOGO = "Logo";
    private static final String LOCATION = "Location";
//    private static final String SCHOOL_CREATED_AT = "createdAt";
//    private static final String SCHOOL_UPDATED_AT = "updatedAt";
//    private static final String SCHOOL_EMAIL = "schoolEmail";
//    private static final String SCHOOL_WEBSITE = "schoolWebsite";

    String createTableBookmark = "Create table if not exists `Bookmark` ("
            + "`name`	TEXT," + "`location`	TEXT," + "`logo`	TEXT);";

    String CREATE_SCHOOL_BOOKMARK_TABLE = "CREATE TABLE " + TABLE_NAME_BOOKMARK + "("
            + ID + " INTEGER PRIMARY KEY, "
            + NAME + " TEXT, "
            + LOGO + " TEXT, "
            + LOCATION + " TEXT " + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public Bookmarkitem getBookmarkData(String bookmarkName) {
        String sql = "select * from Bookmark  where id='" + bookmarkName + "'";

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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL(CREATE_SCHOOL_BOOKMARK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int arg1, int arg2) {
        // TODO Auto-generated method stub
        //Drop older version if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BOOKMARK);
        //create table again
        onCreate(sqLiteDatabase);
    }

    public void addSchoolBookmark(Bookmarkitem bookmarkitem, MenuItem menuItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, bookmarkitem.getBookmarkID());
        values.put(NAME, bookmarkitem.getName());
        values.put(LOGO, bookmarkitem.getLogo());
        values.put(LOCATION, bookmarkitem.getAddress());

        //inserting row
        if (db.insert(TABLE_NAME_BOOKMARK, null, values) != -1) {
            databaseUpdatedListener.setDatabaseSuccess(bookmarkitem.getName(), menuItem);
        } else {
            databaseUpdatedListener.setDatabaseError("Failed to insert");
        }

        db.close();
    }

    //    getting all bookmarked school
    public List<Bookmarkitem> getAllSchoolBookmark() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_BOOKMARK;
        List<Bookmarkitem> bookmarkitems = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all list and adding to list
        if (cursor.moveToFirst()) {
            do {
                Bookmarkitem bookmarkitem = new Bookmarkitem();
                bookmarkitem.setBookmarkID(Integer.parseInt(cursor.getString(0)));
                bookmarkitem.setName(cursor.getString(1));
                bookmarkitem.setLogo(cursor.getString(2));
                bookmarkitem.setAddress(cursor.getString(3));
                bookmarkitems.add(bookmarkitem);
            } while (cursor.moveToNext());
        }
        return bookmarkitems;


    }

}

