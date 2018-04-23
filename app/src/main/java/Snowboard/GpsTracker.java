package Snowboard;

/**
 * Created by Owner on 4/5/2018.
 */

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.content.Context.LOCATION_SERVICE;



public class GpsTracker implements LocationListener {

    Context context;
    double startLat;
    double startLon;
    double  endLat;
    double endLon;
    double runDist;
    double runTime;
    double speed;


    public GpsTracker(Context context) {
        super();
        this.context = context;
        startLat = 0;
        startLon = 0;
        endLat = 0;
        endLon = 0;
        runDist = 0;
        runTime = 0;
        speed= 0;
    }

    public Location getLocation(){
        if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            Log.e("fist","error");
            return null;
        }
        try {
            LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (isGPSEnabled){
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000,1,this);
                Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                return loc;
            }else{
                Log.e("sec","errpr");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    //return length of run
    public double getRunLength(){
        //start lat
        double theta = startLon - endLon;
        runDist = Math.sin(deg2rad(startLat))
                * Math.sin(deg2rad(endLat))
                + Math.cos(deg2rad(startLat))
                * Math.cos(deg2rad(endLat))
                * Math.cos(deg2rad(theta));
        runDist = Math.acos(runDist);
        runDist = rad2deg(runDist);
        runDist = runDist * 60 * 1.1515;
        return (runDist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public void setRunLength(double lat1, double lon1, double lat2, double lon2){
        startLat = lat1;
        startLon = lon1;
        endLat = lat2;
        endLon = lon2;
    }

    //return how long run lasted
    public long getRunTime(){
          return (long) runTime;
    }

    public void setRunTime(long time){
        runTime = time;
    }

    public void setSpeed(double time, double dist ){
        speed = dist/time;
    }

    public double getSpeed(){
        return speed;
    }

}