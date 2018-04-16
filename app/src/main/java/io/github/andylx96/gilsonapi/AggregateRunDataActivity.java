package io.github.andylx96.gilsonapi;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;


public class AggregateRunDataActivity extends AppCompatActivity{



    Button showRuns;
    Button deleteRuns;
    DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregatedata);

        showRuns = findViewById(R.id.ShowRunsButton);
        deleteRuns = findViewById(R.id.DeleteRunsButton);

        myDb = new DataBaseHelper(this);


        showRuns.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Accelerometer Data :"+ res.getString(1)+"\n");
                            buffer.append("Magnitude of Acceleration :"+ res.getString(2)+"\n");
                            buffer.append("Gyroscope Data :"+ res.getString(3)+"\n");
                            buffer.append("Temperature Data :"+ res.getString(4)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );

        deleteRuns.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        myDb.deleteData();

                    }
                }
        );



        }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();



    }


}
