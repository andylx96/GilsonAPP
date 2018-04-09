package Snowboard;

/**
 * Created by Kyle on 4/9/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DataBaseHelper.COLUMN_ID,
            DataBaseHelper.COLUMN_ACCEL_DATA,
            DataBaseHelper.COLUMN_MAG_ACCEL,
            DataBaseHelper.COLUMN_GYRO_DATA,
            DataBaseHelper.COLUMN_TEMP_DATA,

    };



    public void open(){
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        dbhandler.close();

    }

    public DatabaseModel addDatabaseModel(DatabaseModel DatabaseModel){
        ContentValues values  = new ContentValues();
        values.put(DataBaseHelper.COLUMN_ACCEL_DATA,DatabaseModel.getAccelData());
        values.put(DataBaseHelper.COLUMN_MAG_ACCEL,DatabaseModel.getMagAccel());
        values.put(DataBaseHelper.COLUMN_GYRO_DATA, DatabaseModel.getGyroData());
        values.put(DataBaseHelper.COLUMN_TEMP_DATA, DatabaseModel.getTempData());
        long insertid = database.insert(DataBaseHelper.TABLE_SNOWBOARD_DATA,null,values);
        DatabaseModel.setId(insertid);
        return DatabaseModel;

    }

    public DatabaseModel getDatabaseModel(long id) {

        Cursor cursor = database.query(DataBaseHelper.TABLE_SNOWBOARD_DATA,allColumns,DataBaseHelper.COLUMN_ID + "=?",new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        DatabaseModel x = new DatabaseModel(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

        return x;
    }

    public List<DatabaseModel> getDatabaseModel() {

        Cursor cursor = database.query(DataBaseHelper.TABLE_SNOWBOARD_DATA,allColumns,null,null,null, null, null);

        List<DatabaseModel> databaseModels = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                DatabaseModel y = new DatabaseModel();
                y.setId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.COLUMN_ID)));
                y.setAccelData(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_ACCEL_DATA)));
                y.setMagAccel(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_MAG_ACCEL)));
                y.setGyroData(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_GYRO_DATA)));
                y.setTempDataData(cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_TEMP_DATA)));

                databaseModels.add(y);
            }
        }

        return databaseModels;
    }

    public int updateDatabaseModel(DatabaseModel DatabaseModel) {

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COLUMN_ACCEL_DATA, DatabaseModel.getAccelData());
        values.put(DataBaseHelper.COLUMN_MAG_ACCEL, DatabaseModel.getMagAccel());
        values.put(DataBaseHelper.COLUMN_GYRO_DATA, DatabaseModel.getGyroData());
        values.put(DataBaseHelper.COLUMN_TEMP_DATA, DatabaseModel.getTempData());



        return database.update(DataBaseHelper.TABLE_SNOWBOARD_DATA, values,
                DataBaseHelper.COLUMN_ID + "=?",new String[] { String.valueOf(DatabaseModel.getId())});
    }


    public void removeDatabaseModel(DatabaseModel DatabaseModel) {

        database.delete(DataBaseHelper.TABLE_SNOWBOARD_DATA, DataBaseHelper.COLUMN_ID + "=" + DatabaseModel.getId(), null);
    }


}
