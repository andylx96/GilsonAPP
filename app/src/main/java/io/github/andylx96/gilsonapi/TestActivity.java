package io.github.andylx96.gilsonapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

import Snowboard.Snowboard;

public class TestActivity extends AppCompatActivity {
private TextView accelView;
private TextView gyroView;
private TextView tempView;
private Snowboard snowboard;
private Timer timer;
private Button pairButton;
private TextView connectView;
private Button generateDataButton;
//private MyTimer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        accelView= (TextView) findViewById(R.id.TestAccelView);
        gyroView = (TextView) findViewById(R.id.TestGyroView);
        tempView = (TextView) findViewById(R.id.TestTempView);
        connectView = (TextView) findViewById(R.id.TestPairView);
        pairButton = (Button) findViewById(R.id.TestConnectButton);
        generateDataButton = (Button) findViewById(R.id.GenDataButton);
        snowboard = new Snowboard();



setButtonListeners();

    }

    public void setButtonListeners(){




        generateDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//        if(timer != null){
//            timer.cancel();
//        }
//
//        timer = new Timer();
//        timer.schedule();

                accelView.setText("X: "+ Double.toString(snowboard.getAccelerometer()[0])+" \nY: "+ Double.toString(snowboard.getAccelerometer()[1])+ " \nZ: "+
                        Double.toString(snowboard.getAccelerometer()[2]));
                gyroView.setText("X: "+ Double.toString(snowboard.getAccelerometer()[0])+" \nY: "+ Double.toString(snowboard.getAccelerometer()[1])+ " \nZ: "+
                        Double.toString(snowboard.getAccelerometer()[2]));
                tempView.setText(Double.toString(snowboard.getTemp()));

            }


        });

        pairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectView.setText("Connected to Snowboard");
            }
        });



    }
}
