/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrashAPI;

import Snowboard.Snowboard;

/**
 *
 * This class will interpret crashes from the collected snow board data
 *
 * @author rober_000
 */
public class Crash {
    
    byte[] incomingData;
    String crashData;
    
    
     /**
     * @param snowboard board object that contains active data from the board
     */
    Crash(Snowboard snowboard){
        
    }
    
     /**
     * Gets the data collected by the snow board
     * @param snowboardData snowboard data
     */
    public void getSnowboardData(byte[] snowboardData){
        
        incomingData = snowboardData;
        
    }
    
    /**
     * Determines falls/crashes from the collected data
     * @param snowboardData dataset
     */ 
    public void interpretCrashData(byte[] snowboardData){
       
        
    }
    
    /**
     * Returns the crash data interpreted from the incoming snow board data
     * @return String data
     */
     public String returnCrashData(){
         
         return crashData;
         
     }
    
}
