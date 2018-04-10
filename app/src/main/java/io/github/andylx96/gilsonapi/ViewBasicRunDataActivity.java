package io.github.andylx96.gilsonapi;

/**
 * Created by Kyle on 3/31/2018.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

import Snowboard.Snowboard;
import io.github.andylx96.gilsonapi.R;

import Snowboard.*;
public class ViewBasicRunDataActivity extends Activity{

    Snowboard test = new Snowboard();


    double[] accel = test.getAccelerometer();
    //String accelText = accel.toString();
    double v1 = accel[0];
    double v2 = accel[1];
    double v3 = accel[2];
    double calcV = ((v1*v1)+(v2*v2)+(v3*v3));
    /*
    Magnitude of acceleration calculation I found was "square root of (x^2+y^2+z^2)"
    */
    double calcAccel = Math.sqrt(calcV);
    /*
    Not sure what the units are supposed to be for the magniture of acceleration
    */
    String calcAccelText = String.valueOf(calcAccel);
    double[] gyro = test.getGyroscope();
    //String gyroText = gyro.toString();
    double temp = test.getTemp();
    String tempText = String.valueOf(temp);

    Button GetData;
    TextView accelView1;
    TextView magAccelView1;
    TextView gyroView1;
    TextView tempView1;

    DatabaseModel db;
    DatabaseOperations dbo;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbasic);

        GetData = findViewById(R.id.GetBasicRunDataButton);
        accelView1 = findViewById(R.id.accelView);
        magAccelView1 = findViewById(R.id.magAccelView);
        gyroView1 = findViewById(R.id.gyroView);
        tempView1 = findViewById(R.id.tempView);





        GetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                db.setAccelData(Arrays.toString(accel));
                db.setMagAccel(calcAccelText);
                db.setGyroData(Arrays.toString(gyro));
                db.setTempDataData(tempText);

                String dbOp = db.toString(); //takes set data and puts into string to be entered into DB Operations
                dbo.open();
                dbo.addDatabaseModel(db);
                dbo.close();

                accelView1.setText(Arrays.toString(accel));
                magAccelView1.setText(calcAccelText);
                gyroView1.setText(Arrays.toString(gyro));
                tempView1.setText(tempText);


            }
        });
    }


    }









