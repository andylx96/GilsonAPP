package Snowboard;

/**
 * Created by Kyle on 4/8/2018.
 */

public class DatabaseModel {


    private int Id;
    private String accelData;
    private String magAccel;
    private String gyroData;
    private String tempData;

    public DatabaseModel(int Id, String accelData, String magAccel, String gyroData, String tempData) {
        this.Id = Id;
        this.accelData = accelData;
        this.magAccel = magAccel;
        this.gyroData = gyroData;
        this.tempData = tempData;
    }

    public DatabaseModel() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getAccelData() {
        return accelData;
    }

    public void setAccelData(String accelData) {
        this.accelData = accelData;
    }

    public String getMagAccel() {
        return magAccel;
    }

    public void setMagAccel(String magAccel) {
        this.magAccel = magAccel;

    }

    public String getGyroDataData() {
        return gyroData;
    }

    public void setGyroData(String gyroData) {
        this.gyroData = gyroData;
    }

    public String getTempDataData() {
        return tempData;
    }

    public void setTempDataData(String tempData) {
        this.tempData = tempData;
    }
    public String toString(){
        return "Id: "+getId()+ "\n" +
                "Acceleromter Data: "+getAccelData() + "\n" +
                "Magnitude of Acceleration: " + getMagAccel() +
                "Gyroscope Data: "+getGyroDataData() + "\n" +
                "Temperature : "+getTempDataData();
    }
}

