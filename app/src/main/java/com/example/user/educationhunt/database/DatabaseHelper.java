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
    private static final String ADDRESS= "Address";
    private static final String LOGO = "Logo";
    private static final String COUNTRY = "country";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String WEBSITE = "website";
    private static final String INSTITUTION_TYPE = "institution_type";
    private static final String ESTABLISHED_DATE = "establishment_date";
    private static final String ADMISSION_OPEN_FROM = "admission_open_from";
    private static final String ADMISSION_OPEN_TO = "admission_open_to";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    String createTableBookmark = "Create table if not exists `Bookmark` ("
            + "`name`	TEXT," + "`location`	TEXT," + "`logo`	TEXT);";

    String CREATE_SCHOOL_BOOKMARK_TABLE = "CREATE TABLE " + TABLE_NAME_BOOKMARK + "("
            + ID + " INTEGER PRIMARY KEY, "
            + NAME + " TEXT, "
            + LOGO + " TEXT, "
            + ADDRESS + " TEXT, "
            + COUNTRY + " TEXT, "
            + PHONE + " TEXT, "
            + EMAIL + " TEXT, "
            + WEBSITE + " TEXT, "
            + INSTITUTION_TYPE + " TEXT, "
            + ESTABLISHED_DATE + " TEXT, "
            + ADMISSION_OPEN_FROM + " TEXT, "
            + ADMISSION_OPEN_TO + " TEXT, "
            + LATITUDE + " TEXT, "
            + LONGITUDE + " TEXT " + ")";

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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BOOKMARK);
        onCreate(sqLiteDatabase);
    }

    public void addSchoolBookmark(Bookmarkitem bookmarkitem, MenuItem menuItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, bookmarkitem.getBookmarkID());
        values.put(NAME, bookmarkitem.getName());
        values.put(LOGO, bookmarkitem.getLogo());
        values.put(ADDRESS, bookmarkitem.getAddress());

        values.put(COUNTRY, bookmarkitem.getCountry());
        values.put(PHONE, bookmarkitem.getPhone());
        values.put(EMAIL, bookmarkitem.getEmail());
        values.put(WEBSITE, bookmarkitem.getWebsite());
        values.put(INSTITUTION_TYPE, bookmarkitem.getInstitution_type());
        values.put(ESTABLISHED_DATE, bookmarkitem.getEstablishment_date());
        values.put(ADMISSION_OPEN_FROM, bookmarkitem.getAdmission_open_from());
        values.put(ADMISSION_OPEN_TO, bookmarkitem.getAdmission_open_to());

        values.put(LATITUDE, bookmarkitem.getLatitude());
        values.put(LONGITUDE, bookmarkitem.getLongitude());
        //inserting row
        if (db.insert(TABLE_NAME_BOOKMARK, null, values) != -1) {
            databaseUpdatedListener.setDatabaseSuccess(bookmarkitem.getName(), menuItem);
        } else {
            databaseUpdatedListener.setDatabaseError("Failed to insert");
        }
        db.close();
    }

    public List<Bookmarkitem> getAllSchoolBookmark() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME_BOOKMARK;
        List<Bookmarkitem> bookmarkitems = new ArrayList<>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Bookmarkitem bookmarkitem = new Bookmarkitem();
                bookmarkitem.setBookmarkID(Integer.parseInt(cursor.getString(0)));
                bookmarkitem.setName(cursor.getString(1));
                bookmarkitem.setLogo(cursor.getString(2));
                bookmarkitem.setAddress(cursor.getString(3));

                bookmarkitem.setCountry(cursor.getString(4));
                bookmarkitem.setPhone(cursor.getString(5));
                bookmarkitem.setEmail(cursor.getString(6));
                bookmarkitem.setWebsite(cursor.getString(7));
                bookmarkitem.setInstitution_type(cursor.getString(8));
                bookmarkitem.setEstablishment_date(cursor.getString(9));
                bookmarkitem.setAdmission_open_from(cursor.getString(10));
                bookmarkitem.setAdmission_open_to(cursor.getString(11));
                bookmarkitem.setLatitude(cursor.getString(12));
                bookmarkitem.setLongitude(cursor.getString(13));

                bookmarkitems.add(bookmarkitem);
            } while (cursor.moveToNext());
        }
        return bookmarkitems;
    }

    public void removeBookmarkItem(int sID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME_BOOKMARK + " WHERE " + ID + "= '" + sID + "'");
        db.close();
    }

    public Bookmarkitem getBoomarkDetailByID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        String empName = "";
        try{
            Bookmarkitem bookmarkitem= new Bookmarkitem();
            cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME_BOOKMARK+ " WHERE "+ID +"=?", new String[] {id + ""});
            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                bookmarkitem.setBookmarkID(Integer.parseInt(cursor.getString(0)));
                bookmarkitem.setName(cursor.getString(1));
                bookmarkitem.setLogo(cursor.getString(2));
                bookmarkitem.setAddress(cursor.getString(3));

                bookmarkitem.setCountry(cursor.getString(4));
                bookmarkitem.setPhone(cursor.getString(5));
                bookmarkitem.setEmail(cursor.getString(6));
                bookmarkitem.setWebsite(cursor.getString(7));
                bookmarkitem.setInstitution_type(cursor.getString(8));
                bookmarkitem.setEstablishment_date(cursor.getString(9));
                bookmarkitem.setAdmission_open_from(cursor.getString(10));
                bookmarkitem.setAdmission_open_to(cursor.getString(11));
                bookmarkitem.setLatitude(cursor.getString(12));
                bookmarkitem.setLongitude(cursor.getString(13));
            }

            return bookmarkitem;
        }finally {

            cursor.close();
        }
    }
}