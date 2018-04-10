package io.github.andylx96.gilsonapi;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import Snowboard.DatabaseOperations;


public class AggregateRunDataActivity extends AppCompatActivity{


    DatabaseOperations db;
    Button showRuns;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggregatedata);

        showRuns = findViewById(R.id.ShowRunsButton);
        //list = findViewById(R.id.listView);

        showRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //db.getDatabaseModel();

                Toast.makeText(getApplicationContext(), "Getting Database", Toast.LENGTH_SHORT).show();



            }
        });



    }


}
