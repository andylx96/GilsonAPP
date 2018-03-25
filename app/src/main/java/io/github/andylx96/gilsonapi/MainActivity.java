package io.github.andylx96.gilsonapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button testButton ;
    private Button viewRunDataButton;
    private Button viewAggDataButton;
    private Button socialMediaButton;
    private Button emergancyButton;
    private Button crashButton;
    private Button pairButton;
    private Button adviceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testButton = (Button)findViewById(R.id.TestingButton);
        viewRunDataButton = (Button) findViewById(R.id.ViewRunDataButton);
        viewAggDataButton = (Button) findViewById(R.id.ViewAggregateDataButton);
        socialMediaButton = (Button) findViewById(R.id.SocialMediaButton);
        emergancyButton = (Button) findViewById(R.id.EmergancyButton);
        crashButton = (Button) findViewById(R.id.CrashButton);
        pairButton = (Button) findViewById(R.id.PairButton);
        adviceButton = (Button) findViewById(R.id.AdviceButton);

        setButtons();
    }




//    Setting OnClickListener For Test Button
    public void setButtons(){
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Changes Menus from This Class MainActivity to TestActivity Page
                startActivity(new Intent(MainActivity.this,TestActivity.class));
            }
        });

        adviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,AdviceActivity.class));
            }
        });


        pairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Pair2Activity.class));
            }
        });



    }



}
