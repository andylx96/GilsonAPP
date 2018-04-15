package io.github.andylx96.gilsonapi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SocialMediaActivity extends Activity {


    Button chooseRunButton;
    Button saveImageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialmedia);

        chooseRunButton = findViewById(R.id.chooseRunButton);
        saveImageButton = findViewById(R.id.SaveImageButton);

        //allow user to choose specific run from database
        chooseRunButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {


            }
        });

        //save image with chosen run data to camera roll
        saveImageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {


            }
        });






    }


}
