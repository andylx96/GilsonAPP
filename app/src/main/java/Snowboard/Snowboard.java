/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snowboard;

/**
 * Snowboard Object Class
 * @author zabuz
 */
public class Snowboard {
    /**
     * Data set from snowboard
     */
    byte[] snowboardDataSet;


    Snowboard(){
        snowboardDataSet = new byte[100];

    }

    /**
     * @return data set as byte array
     */
    public byte[] getSnowboardDataSet() {
        return snowboardDataSet;
    }


    /**
     *
     * Sets data set as byte array
     * @param snowboardDataSet snowboard data set as an array
     */
    public void setSnowboardDataSet(byte[] snowboardDataSet) {
        this.snowboardDataSet = snowboardDataSet;
    }

}
