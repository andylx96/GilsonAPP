package Snowboard;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Created by Kyle on 3/28/2018.
 */

public class BasicRunData {



    public static void main(String[] args){

        Snowboard test = new Snowboard();


        double[] accel = test.getAccelerometer();
        double[] gyro = test.getGyroscope();

        System.out.println("The Accelerometer Data Values Are: " + Arrays.toString(accel));
        System.out.println("The Gyroscope Data Values Are: " + Arrays.toString(gyro));
        System.out.println("The Temperature During Your Run Was: " + test.getTemp());
    }

}
