package io.github.andylx96.gilsonapi;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
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
        String [] myRuns = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_aggregatedata, myRuns);
        list = (ListView) findViewById(R.id.listView);

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
