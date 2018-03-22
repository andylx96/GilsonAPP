/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmergencyAPI;

/**
 *
 * @author Owner
 */
public class EmergencySignal {
    
    /**
     * EmergencySignal constructor.
     */
    EmergencySignal(){
        
    }
    
    boolean crashDetected;
    boolean countdownEnd;
    
    /**
     * Gets crash data and determines if to start emergency signal process.
     * @param crashData contains crash data for the users snowboard
     * @return True is crash was detected, False if no crash was detected
     */
    public boolean getCrashDetection(String crashData){
        return crashDetected;
    }
    
    /**
     * Starts countdown to call ski patrol.
     * @return True if countdown ended, False if it has not ended
     */
    public boolean startCountdown(){
        return countdownEnd;
    }
    
    /**
     * Calls ski patrol to notify them there has been an accident.
     * @param crashData contains crash data for the users snowboard
     */
    public void callSkiPatrol(String crashData){
        
    }
    
    /**
     * Cancels call to ski patrol.
     */
    public void cancelCall(){
        
    }
    
    
}