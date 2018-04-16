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

public class StartRunActivity extends AppCompatActivity {


    private Button startButton;
    private Button endButton;

    Button btnLoc;

    //Start and stop run class???
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "GPS Lat = " + lat + "\n lon = " + lon, Toast.LENGTH_SHORT).show();
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
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "GPS Lat = " + lat + "\n lon = " + lon, Toast.LENGTH_SHORT).show();
                    //add to basic run and agg run data
                    // mDatabase.child("Latitude").push().setValue(String.valueOf(lat));
                    // mDatabase.child("Longitude").push().setValue(String.valueOf(lon));

                }
            }
        });
    }
}
