package io.github.andylx96.gilsonapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Pair2Activity extends AppCompatActivity {
    private TextView connectStatus;
    private TextView connectStatus2;
    private Button connectButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pair2);


//            connectStatus = (TextView) findViewById(R.id.connectStatus);
            connectStatus2 = (TextView) findViewById(R.id.connectionStatus2);
            connectButton = (Button) findViewById(R.id.connectDeviceButton);

            setButtonListener();
    }

    private void setButtonListener(){

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                connectStatus.setText("Connected");
                connectStatus2.setText("Connected");

            }
        });

    }

}
