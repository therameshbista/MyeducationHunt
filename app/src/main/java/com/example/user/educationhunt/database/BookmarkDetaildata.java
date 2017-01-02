package com.example.user.educationhunt.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.view.MenuItem;

import com.example.user.educationhunt.listner.DatabaseUpdatedListener;
import com.example.user.educationhunt.pojos.BookmarkDetailItem;
import com.example.user.educationhunt.pojos.Bookmarkitem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 12/29/2016.
 */
public class BookmarkDetaildata extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "DetailDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Detail";

    //    Contact table columns name
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String ADDRESS = "address";
    private static final String PHONE = "Phone";
    private static final String TYPE = "Type";
    private static final String EMAIL = "Email";
    private static final String WEBSITE = "Website";
    private static final String DISTRICT = "District";
    private static final String COUNTRY = "Country";
    private static final String ADMISSION_OPEN = "admission_open";
    private static final String ADMISSION_CLOSED = "admission_closed";

    String createTableDetail = "Create table if not exists `Bookmark` ("
            + "`name`	TEXT," + "`addresss`	TEXT,"
            + "`phone`	TEXT," + "`type`	TEXT,"
            + "`email`	TEXT," + "`website`	TEXT,"
            + "`district`	TEXT," + "`country`	TEXT,"
            + "`admission_open`	TEXT," + "`admission_closed` TEXT);";

    String CREATE_DETAIL_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY, "
            + NAME + " TEXT, "
            + ADDRESS + " TEXT, "
            + PHONE + " TEXT, "
            + TYPE + " TEXT, "
            + EMAIL + " TEXT, "
            + WEBSITE + " TEXT, "
            + DISTRICT + " TEXT, "
            + COUNTRY + " TEXT, "
            + ADMISSION_OPEN + " TEXT, "
            + ADMISSION_CLOSED + " TEXT " + ")";

    public BookmarkDetaildata(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertData(BookmarkDetailItem bookmarkDetailItem) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", bookmarkDetailItem.name);
        content.put("address", bookmarkDetailItem.address);
        content.put("phone", bookmarkDetailItem.phone);
        content.put("type", bookmarkDetailItem.type);
        content.put("email", bookmarkDetailItem.email);
        content.put("website", bookmarkDetailItem.website);
        content.put("district", bookmarkDetailItem.district);
        content.put("country", bookmarkDetailItem.country);
        content.put("admission_open", bookmarkDetailItem.admissionOpen);
        content.put("admission_closed", bookmarkDetailItem.admissionClosed);

        db.insert("Detail", null, content);
    }

    public ArrayList<BookmarkDetailItem> getData() {
        String sql = "select * from Bookmark ";
        ArrayList<BookmarkDetailItem> mydatalist = new ArrayList<BookmarkDetailItem>();

        Cursor c = getWritableDatabase().rawQuery(sql, null);
        while (c.moveToNext()) {
            BookmarkDetailItem info = new BookmarkDetailItem();
            info.name = c.getString(c.getColumnIndex("name"));
            info.address = c.getString(c.getColumnIndex("address"));
            info.phone = c.getString(c.getColumnIndex("phone"));
            info.type = c.getString(c.getColumnIndex("type"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.website = c.getString(c.getColumnIndex("website"));
            info.district = c.getString(c.getColumnIndex("district"));
            info.country = c.getString(c.getColumnIndex("country"));
            info.admissionOpen = c.getString(c.getColumnIndex("admission_open"));
            info.admissionClosed = c.getString(c.getColumnIndex("admission_closed"));
            mydatalist.add(info);
        }
        c.close();
        return mydatalist;
    }

    public BookmarkDetailItem getBookmarkData(String bookmarkName) {
        String sql = "select * from Bookmark  where id='" + bookmarkName + "'";

        Cursor c = getWritableDatabase().rawQuery(sql, null);
        while (c.moveToNext()) {
            BookmarkDetailItem info = new BookmarkDetailItem();
            info.name = c.getString(c.getColumnIndex("name"));
            info.address = c.getString(c.getColumnIndex("address"));
            info.phone = c.getString(c.getColumnIndex("phone"));
            info.type = c.getString(c.getColumnIndex("type"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.website = c.getString(c.getColumnIndex("website"));
            info.district = c.getString(c.getColumnIndex("district"));
            info.country = c.getString(c.getColumnIndex("country"));
            info.admissionOpen = c.getString(c.getColumnIndex("admission_open"));
            info.admissionOpen = c.getString(c.getColumnIndex("admission_closed"));
        }
        c.close();
        BookmarkDetailItem info = null;
        return info;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL(CREATE_DETAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int arg1, int arg2) {
        // TODO Auto-generated method stub
        //Drop older version if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //create table again
        onCreate(sqLiteDatabase);
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where id='" + id + "'");
    }

}
