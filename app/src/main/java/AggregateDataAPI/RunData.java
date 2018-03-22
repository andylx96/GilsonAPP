/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AggregateDataAPI;

/**
 *
 * @author Laura
 * 
 * this class will be used to set / refer to the data of
 * a specific run
 */
public class RunData {
    
    byte[] runDataSet;
  
    
    public void setRunData(byte[] runDataSet){
        this.runDataSet= runDataSet;
    }
    
    public byte[] getRunData(){
        return runDataSet;
    }
}
