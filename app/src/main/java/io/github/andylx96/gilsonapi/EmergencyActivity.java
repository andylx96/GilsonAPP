package io.github.andylx96.gilsonapi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Snowboard.GpsTracker;

/**
 * Created by rober_000 on 4/16/2018.
 */

public class EmergencyActivity extends Permissions {

    private TextView setNumberLabel;
    private EditText currentNumber;
    private Button sendMessageButton;
    SmsManager smsManager = SmsManager.getDefault();

    private static final int REQUEST_PERMISSION = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        currentNumber = findViewById(R.id.editPhoneNumber);
        setNumberLabel = findViewById(R.id.phoneNumberLabel);
        sendMessageButton = findViewById(R.id.sendMessageButton);
                requestAppPermissions(new String[]{
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.BLUETOOTH_ADMIN,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.SEND_SMS},
                R.string.msg,REQUEST_PERMISSION);





        setButtonListeners();

    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        //Do anything when permisson granted
        Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_LONG).show();
    }


    public void setButtonListeners(){




        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snowboard.GpsTracker gt = new Snowboard.GpsTracker(getApplicationContext());
                Location l = gt.getLocation();
                double lat = l.getLatitude();
                double lon = l.getLongitude();
                Toast.makeText(getApplicationContext(), "Sending Emergency Signal", Toast.LENGTH_LONG).show();
                smsManager.sendTextMessage(currentNumber.getText().toString(), null, "There has been a crash on the mountain. Please send ski patrol to these coordintes: Latitude-"+lat+" Longitude-"+lon, null, null);

            }


        });



    }
}