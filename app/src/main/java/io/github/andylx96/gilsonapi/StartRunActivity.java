package io.github.andylx96.gilsonapi;
/**
 * Created by Owner on 4/5/2018.
 */

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import Snowboard.GpsTracker;
import Snowboard.Snowboard;

public class StartRunActivity extends AppCompatActivity {


    private Button startButton;
    private Button endButton;
    double lat1;
    double lon1;
    double lat2;
    double lon2;

    double speed;
    long startTime = 0;
    long endTime;




    //Start and stop run class
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        startButton = (Button) findViewById(R.id.startRunButton);
        endButton = (Button) findViewById(R.id.endRunButton);
        ActivityCompat.requestPermissions(StartRunActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GpsTracker gt = new GpsTracker(getApplicationContext());
                Location l = gt.getLocation();
                if (l == null) {
                    Toast.makeText(getApplicationContext(), "GPS unable to get Value", Toast.LENGTH_SHORT).show();
                } else {
                    lat1 = l.getLatitude();
                    lon1 = l.getLongitude();
                    startTime = System.currentTimeMillis()/1000;

                    Toast.makeText(getApplicationContext(), "GPS Lat = " + lat1 + "\n lon = " + lon1, Toast.LENGTH_SHORT).show();
                    //add to basic run and agg run data
                    // mDatabase.child("Latitude").push().setValue(String.valueOf(lat));
                    // mDatabase.child("Longitude").push().setValue(String.valueOf(lon));


                }
            }
        });
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GpsTracker gt = new GpsTracker(getApplicationContext());
                Location l = gt.getLocation();
                if (l == null) {
                    Toast.makeText(getApplicationContext(), "GPS unable to get Value", Toast.LENGTH_SHORT).show();
                } else {
                    lat2 = l.getLatitude();
                    lon2 = l.getLongitude();

                    gt.setRunLength(lat1, lon1, lat2, lon2);

                    endTime = System.currentTimeMillis()/1000 - startTime;
                    gt.setRunTime(endTime);

                    Toast.makeText(getApplicationContext(), "distance is"+gt.getRunLength()+"  Time: "+endTime , Toast.LENGTH_SHORT).show();
                    //add to basic run and agg run data

                    //mDatabase.child("Latitude").push().setValue(String.valueOf(lat));
                    // mDatabase.child("Longitude").push().setValue(String.valueOf(lon));

                }
            }
        });
    }


}
