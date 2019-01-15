package io.github.andylx96.gilsonapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Kiad on 4/24/2018.
 */

public class Save {
    private Context ctx;
    private String NameOfFolder = "/GilsonApp";
    private String NameOfFile = "Gilson";

    public void SaveImage(Context context, Bitmap ImageToSave){
        ctx = context;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath()+ NameOfFolder;
        String CurrentDateAndTime = getCurrentDateAndTime();
        File dir = new File(file_path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dir, NameOfFile +  CurrentDateAndTime+ ".jpg");
        try{
            FileOutputStream fOut = new FileOutputStream(file);
            ImageToSave.compress(Bitmap.CompressFormat.JPEG,85,fOut);
            fOut.flush();
            fOut.close();
            MakeSureFileCreated(file);
            AbleToSave();
        }
        catch(FileNotFoundException e){UnableToSave();}
        catch(IOException e){UnableToSave();}
    }

    private void MakeSureFileCreated(File file){
        MediaScannerConnection.scanFile(ctx,
                new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String s, Uri uri) {
                        Log.e("ExternalStorage", "Scanned" + s +":");
                        Log.e("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }

    private String getCurrentDateAndTime(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
    private void UnableToSave(){
        Toast.makeText(ctx,"Picture was not able to save",Toast.LENGTH_LONG).show();
    }
    private void AbleToSave(){
        Toast.makeText(ctx,"Picture was saved to gallery",Toast.LENGTH_LONG).show();
    }
}
