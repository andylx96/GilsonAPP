package io.github.andylx96.gilsonapi;

import android.database.Cursor;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class ViewAllRunData extends AppCompatActivity {
    DataBaseHelper myDb;

    private ListView lv;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_run_data);

//ViewBasicRunDataActivity.class.get
        myDb = new DataBaseHelper(this);
        lv = (ListView)findViewById(R.id.listView);

//        boolean isInserted = myDb.insertData("10,20,30",
//        "10,20,30",
//                "10,20,30",
//                "10",
//                "10"); //FIX ME
//
//        if(isInserted == true)
//            Toast.makeText(this,"Data Inserted",Toast.LENGTH_LONG).show();
//        else
//            Toast.makeText(this,"Data not Inserted",Toast.LENGTH_LONG).show();


        List<String> all = myDb.getAllCategory();
        if(all.size()>0) // check if list contains items.
        {

            arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, all);
            lv.setAdapter(arrayAdapter);
        }
        else
        {
            Toast.makeText(this,"No items to display",Toast.LENGTH_LONG).show();
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cursor res = myDb.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    if(res.getString(6).trim().equalsIgnoreCase(all.get(i).toString().trim())) {
                        buffer.append("Accelerometer Data :" + res.getString(1) + "\n");
                        buffer.append("Magnitude of Acceleration :" + res.getString(2) + "\n");
                        buffer.append("Gyroscope Data :" + res.getString(3) + "\n");
                        buffer.append("Temperature Data :" + res.getString(4) + "\n");
                    buffer.append("Speed Data : " + res.getString(5) + "\n");
                    buffer.append("Date : " + res.getString(6) + "\n");
                    buffer.append("Res is : " + res.getCount() + "\n\n");
                    buffer.append("I is: " + i + "\n\n");
                    buffer.append("ID is: " + res.getString(0) + "\n\n");


                    }

//                    showMessage("Data","Res: " + res.getCount() + "\n i: " + i);
                }

                // Show all data
                showMessage("Data",buffer.toString());
        }
        });







        }



    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }










}
