package Snowboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kyle on 4/8/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SnowboardData";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_SNOWBOARD_DATA = "saveddata";
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_ACCEL_DATA = "accelData";
    public static final String COLUMN_MAG_ACCEL = "magAccel";
    public static final String COLUMN_GYRO_DATA = "gyroData";
    public static final String COLUMN_TEMP_DATA= "tempData";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SNOWBOARD_DATA + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ACCEL_DATA + " TEXT, " +
                    COLUMN_MAG_ACCEL + " TEXT, " +
                    COLUMN_GYRO_DATA + " TEXT, " +
                    COLUMN_TEMP_DATA + " TEXT, " +
                    ")";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_SNOWBOARD_DATA);
        sqLiteDatabase.execSQL(TABLE_CREATE);

    }
}
