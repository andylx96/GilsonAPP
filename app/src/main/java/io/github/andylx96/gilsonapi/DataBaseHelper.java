package io.github.andylx96.gilsonapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyle on 4/8/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Snowboard4.db";
    public static final String TABLE_NAME = "snowboard_data_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "AccelData";
    public static final String COL_3 = "MagAccel";
    public static final String COL_4 = "GyroData";
    public static final String COL_5 = "TempData";
    public static final String COL_6 = "SpeedData";

    public static final String COL_7 = "DateData";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,AccelData TEXT,MagAccel TEXT,GyroData TEXT,TempData TEXT,SpeedData TEXT,DateData TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String AccelData, String MagAccel, String GyroData, String TempData, String SpeedData, String DateData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,AccelData);
        contentValues.put(COL_3,MagAccel);
        contentValues.put(COL_4,GyroData);
        contentValues.put(COL_5,TempData);
        contentValues.put(COL_6,SpeedData);
        contentValues.put(COL_7,DateData);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String AccelData, String MagAccel, String GyroData, String TempData, String SpeedData, String DateData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,AccelData);
        contentValues.put(COL_3,MagAccel);
        contentValues.put(COL_4,GyroData);
        contentValues.put(COL_5,TempData);
        contentValues.put(COL_6, SpeedData);
        contentValues.put(COL_7,DateData);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public void deleteData () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,null,null);

//        db.execSQL("VACUUM");
    }
    public List<String> getAllCategory() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<String> List = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                List.add(cursor.getString(6));
            } while (cursor.moveToNext());
        }
        return List;
    }


}
