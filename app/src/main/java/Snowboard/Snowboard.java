/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snowboard;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Snowboard Object Class
 * @author zabuz
 */
public class Snowboard {
    /**
     * Data set from snowboard
     */
    double[] snowboardDataSet;
    double[] accelerometer;
    double[] gyroscope;
    double temp;
    boolean pos_neg;


    public Snowboard(){
        snowboardDataSet = new double[65]; //add rows for location and time
        accelerometer = new double[3];
        accelerometer = new double[3];
        temp = 0;

    }

    /**
     * @return data set as byte array
     */
    public double[] getSnowboardDataSet() {
        return snowboardDataSet;
    }


    /**
     * Sets data set as byte array
     * @param snowboardDataSet snowboard data set as an array
     */
    public void setSnowboardDataSet(double[] snowboardDataSet) {
        this.snowboardDataSet = snowboardDataSet;
    }

    public double[] getAccelerometer() {
        DecimalFormat df3 = new DecimalFormat(".###");

        double[] tempArry = new double[3];
        tempArry[0] =  Double.parseDouble(df3.format(Math.random()));
        tempArry[1] =Double.parseDouble(df3.format(Math.random()));
        tempArry[2] = Double.parseDouble(df3.format(Math.random()));
//        return accelerometer;
        return tempArry;
    }

    public void setAccelerometer(double[] accelerometer) {
        this.accelerometer = accelerometer;
    }

    public double[] getGyroscope() {
//        return gyroscope;
        DecimalFormat df3 = new DecimalFormat(".###");

        // enable x axis to have negative numbers
        Random random = new Random();
        pos_neg = random.nextBoolean();

        double[] tempArry = new double[3];
        tempArry[0] = Double.parseDouble(df3.format(Math.random()));
        tempArry[1] =  Double.parseDouble(df3.format(Math.random()));
        tempArry[2] =  Double.parseDouble(df3.format(Math.random()));

        if(pos_neg){tempArry[0] = tempArry[0] * -1;}

        return tempArry;
    }

    public void setGyroscope(double[] gyroscope) {
        this.gyroscope = gyroscope;
    }

    public double getTemp() {
//        return temp;
        Random rand = new Random();
//rand.nextInt(10+1);

        DecimalFormat df3 = new DecimalFormat(".###");

        return Double.parseDouble(df3.format(45 + (50 - 45) * rand.nextDouble()));
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

}
