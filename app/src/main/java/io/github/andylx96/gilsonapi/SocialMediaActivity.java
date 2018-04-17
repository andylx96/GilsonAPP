package io.github.andylx96.gilsonapi;

import android.app.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;


public class SocialMediaActivity extends Activity {

    ViewBasicRunDataActivity VB = new ViewBasicRunDataActivity();

    Button chooseRunButton;
    Button saveImageButton;
    TextView accelView1;
    TextView magAccelView1;
    TextView gyroView1;
    TextView tempView1;

    String accelText2 = VB.accelText;
    String calcAccelText2 = VB.calcAccelText;
    String gyroText2 = VB.gyroText;
    String tempText2 = VB.tempText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialmedia);

        chooseRunButton = findViewById(R.id.chooseRunButton);
        saveImageButton = findViewById(R.id.SaveImageButton);
        accelView1 = findViewById(R.id.accelView);
        magAccelView1 = findViewById(R.id.magAccelView);
        gyroView1 = findViewById(R.id.gyroView);
        tempView1 = findViewById(R.id.tempView);



        chooseRunButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {

                accelView1.setText(accelText2);
                magAccelView1.setText(calcAccelText2);
                gyroView1.setText(gyroText2);
                tempView1.setText(tempText2);

            }
        });

        //save image with chosen run data to camera roll
        saveImageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view) {


            }
        });






    }




}
