package io.github.andylx96.gilsonapi;

/**
 * Created by Kyle on 3/31/2018.
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import Snowboard.Snowboard;
import Snowboard.GpsTracker;

public class ViewBasicRunDataActivity extends Activity {

    Snowboard test = new Snowboard();
    GpsTracker gt = new GpsTracker(getBaseContext());

    double[] accel = test.getAccelerometer();
    double v1 = accel[0];
    double v2 = accel[1];
    double v3 = accel[2];
    String accelText = Arrays.toString(accel);
    double calcV = ((v1 * v1) + (v2 * v2) + (v3 * v3));
    /*
    Magnitude of acceleration calculation I found was "square root of (x^2+y^2+z^2)"
    */
    double calcAccel = Math.sqrt(calcV);
    /*
    Not sure what the units are supposed to be for the magniture of acceleration
    */
    String calcAccelText = String.valueOf(calcAccel);
    double[] gyro;
    String gyroText;
    double temp = test.getTemp();
    String tempText = String.valueOf(temp);

    double speed = gt.getSpeed();
    String speedText = String.valueOf(speed);

    Button GetData;
    Button Emergency;
    Button plotButton;
    Button pieButton;
    TextView accelView1;
    TextView magAccelView1;
    TextView gyroView1;
    TextView tempView1;
    TextView speedView1;

    DataBaseHelper myDb;
    Button SaveData;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewbasic);
        myDb = new DataBaseHelper(this);

        GetData = findViewById(R.id.GetBasicRunDataButton);
        accelView1 = findViewById(R.id.accelView);
        magAccelView1 = findViewById(R.id.magAccelView);
        gyroView1 = findViewById(R.id.gyroView);
        tempView1 = findViewById(R.id.tempView);
        speedView1 = findViewById(R.id.speedView);
        Emergency = (Button)findViewById(R.id.emer);
        plotButton = (Button)findViewById(R.id.plotButton);
        pieButton = (Button)findViewById(R.id.pieButton);

        SaveData = findViewById(R.id.SaveDataButton);

        Date currentTime = Calendar.getInstance().getTime();




        Emergency.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(ViewBasicRunDataActivity.this).create();
                alertDialog.setTitle("Emergency crash sending help in");
                alertDialog.setMessage("00:10");
                alertDialog.setCancelable(false);
                alertDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alertDialog.show();   // 

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        alertDialog.setMessage("00:" + (millisUntilFinished / 1000));
                    }

                    @Override
                    public void onFinish() {
                        Intent intent = new Intent(ViewBasicRunDataActivity.this,EmergencyActivity.class);
                        startActivity(intent);

                    }
                }.start();
            }
        });



        GetData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                gyro = test.getGyroscope();
                gyroText = Arrays.toString(gyro);
                accelView1.setText(accelText);
                magAccelView1.setText(calcAccelText);
                gyroView1.setText(gyroText);
                tempView1.setText(tempText);
                speedView1.setText(speedText);
            }
        });

        plotButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {

                if (gyro != null) {
                            startActivity(new Intent(ViewBasicRunDataActivity.this, xyPlotActivity.class));
            }
            else{
                Toast.makeText(getApplicationContext(), "Please get values first", Toast.LENGTH_SHORT).show();
            }

            }
        });

        pieButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {

                if (gyro != null) {
                    startActivity(new Intent(ViewBasicRunDataActivity.this, pieChartActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please get values first", Toast.LENGTH_SHORT).show();
                }

            }
        });


            SaveData.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isInserted = myDb.insertData(Arrays.toString(accel),
                                    calcAccelText,
                                    Arrays.toString(gyro),
                                    tempText,
                                    speedText,
                                    Calendar.getInstance().getTime().toString()
                                    ); //FIX ME
                            if(isInserted == true)
                                Toast.makeText(ViewBasicRunDataActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(ViewBasicRunDataActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                        }
                    }
            );







    }

    public double[] getGyro(){
        return gyro;
    }

    public DataBaseHelper getMyDb() {
        return myDb;
    }
}










