package io.github.andylx96.gilsonapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import Snowboard.Snowboard;

public class AdviceActivity extends AppCompatActivity {

    Snowboard test = new Snowboard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        double[] gyro = test.getGyroscope();
        double x = gyro[0];

        if(x < 0){setContentView(R.layout.activity_advice);}
        if(x > 0){setContentView(R.layout.activity_advice2);}

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.3),(int)(height*0.3));

    }
}
