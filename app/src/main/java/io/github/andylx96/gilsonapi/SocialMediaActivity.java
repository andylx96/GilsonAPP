package io.github.andylx96.gilsonapi;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SocialMediaActivity extends AppCompatActivity {

    ViewBasicRunDataActivity VB = new ViewBasicRunDataActivity();

    Button chooseRunButton;
    Button saveImageButton;
    Button shareButton;
    TextView accelView1;
    TextView magAccelView1;
    TextView gyroView1;
    TextView tempView1;
    TextView speedView1;

    String accelText2 = VB.accelText;
    String calcAccelText2 = VB.calcAccelText;
    String gyroText2 = VB.gyroText;
    String tempText2 = VB.tempText;
    String speedText2 = VB.speedText;
    private View main;
    private ImageView imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialmedia);
        ActivityCompat.requestPermissions(SocialMediaActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        main = findViewById(R.id.root_content);
        imageView = (ImageView) findViewById(R.id.imgView);

        chooseRunButton = findViewById(R.id.chooseRunButton);
        saveImageButton = findViewById(R.id.SaveImageButton);
        shareButton = findViewById(R.id.shareButton);
        accelView1 = findViewById(R.id.accelView);
        magAccelView1 = findViewById(R.id.magAccelView);
        gyroView1 = findViewById(R.id.gyroView);
        tempView1 = findViewById(R.id.tempView);
        speedView1 = findViewById(R.id.speedView);


        chooseRunButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {

                accelView1.setText(accelText2);
                magAccelView1.setText(calcAccelText2);
                gyroView1.setText(gyroText2);
                tempView1.setText(tempText2);
                speedView1.setText(speedText2);

            }
        });

        //save image with chosen run data to camera roll
        saveImageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view) {

                Bitmap b = Screenshot.takescreenshotOfRootView(imageView);
//            imageView.setImageBitmap(b);
//            main.setBackgroundColor(Color.parseColor("#999999"));
//                Bitmap bitmap = takeScreenshot();
//                saveBitmap(b);
                Save savefile = new Save();
                savefile.SaveImage(SocialMediaActivity.this,b);
                Toast.makeText(SocialMediaActivity.this,"Screenshot saved",Toast.LENGTH_LONG).show();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view) {

                shareIntent();
            }
        });


    }

//    public Bitmap takeScreenshot() {
//        View rootView = findViewById(android.R.id.content).getRootView();
//        rootView.setDrawingCacheEnabled(true);
//        return rootView.getDrawingCache();
//    }

    public void saveBitmap(Bitmap bitmap) {
        try {
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + ".jpg";
            FileOutputStream fos = new FileOutputStream(mPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    private void shareIntent(){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        String imagePath = Environment.getExternalStorageDirectory() + "/" + ".jpg";
        File imageFileToShare = new File(imagePath);
        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image!"));
    }





}
