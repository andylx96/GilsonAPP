package Snowboard;

import java.io.PrintStream;
import java.util.Arrays;


/**
 * Created by Kyle on 3/28/2018.
 */

public class BasicRunData {



    public static void main(String[] args){

        Snowboard test = new Snowboard();

        /*
        Magnitude of acceleration calculation I found was "square root of (x^2+y^2+z^2)"
         */
        double[] accel = test.getAccelerometer();
        double v1 = accel[0];
        double v2 = accel[1];
        double v3 = accel[2];
        double calcV = ((v1*v1)+(v2*v2)+(v3*v3));
        double calcAccel = Math.sqrt(calcV);

        double[] gyro = test.getGyroscope();

        double temp = test.getTemp();

        System.out.println("The Accelerometer Data Values Are: " + Arrays.toString(accel));
        System.out.println("The Magnitude of Acceleration Is: " + calcAccel);
        /*
        Not sure what the units are supposed to be for the magniture of acceleration
         */
        System.out.println("The Gyroscope Data Values Are: " + Arrays.toString(gyro));
        System.out.println("The Temperature During Your Run Was: " + temp);




    }


}
