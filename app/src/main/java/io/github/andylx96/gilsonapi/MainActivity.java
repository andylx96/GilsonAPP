package io.github.andylx96.gilsonapi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private Button testButton ;
    private Button viewRunDataButton;
    private Button viewAggDataButton;
    private Button socialMediaButton;
    private Button emergencyButton;
    private Button crashButton;
    private Button pairButton;
    private Button adviceButton;
    private Button startButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        testButton = (Button)findViewById(R.id.TestingButton);
//        viewRunDataButton = (Button) findViewById(R.id.ViewBasicRunDataButton);
//        viewAggDataButton = (Button) findViewById(R.id.ViewAggregateDataButton);
//        socialMediaButton = (Button) findViewById(R.id.SocialMediaButton);
//        emergencyButton = (Button) findViewById(R.id.EmergencyButton);
//        crashButton = (Button) findViewById(R.id.CrashButton);
        pairButton = (Button) findViewById(R.id.PairButton);
//        adviceButton = (Button) findViewById(R.id.AdviceButton);
//        viewRunDataButton = (Button) findViewById(R.id.ViewBasicRunDataButton);
        startButton = (Button) findViewById(R.id.StartRunBtn);



        setButtons();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    Setting OnClickListener For Test Button
    public void setButtons(){
//        socialMediaButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, SocialMediaActivity.class));
//            }
//        });
//
//
//        viewRunDataButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Changes Menus from This Class MainActivity to TestActivity Page
//                startActivity(new Intent(MainActivity.this, ViewBasicRunDataActivity.class));
//            }
//        });
//
//        adviceButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(MainActivity.this,AdviceActivity.class));
//            }
//        });
//
//        emergencyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(MainActivity.this,EmergencyActivity.class));
//            }
//        });

        pairButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,Pair2Activity.class));
            }
        });

//        viewAggDataButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(MainActivity.this,AggregateRunDataActivity.class));
//            }
//        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,StartRunActivity.class));
            }
        });


    }


    public void onPermissionsGranted(int requestCode) {
        //Do anything when permisson granted
        Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_ViewBasicRun) {
            Intent intent = new Intent(MainActivity.this,ViewBasicRunDataActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_ViewAgg) {
            startActivity(new Intent(MainActivity.this,AggregateRunDataActivity.class));

        } else if (id == R.id.nav_Social) {
            startActivity(new Intent(MainActivity.this, SocialMediaActivity.class));
        }

         else if (id == R.id.nav_Advice) {
        startActivity(new Intent(MainActivity.this,AdviceActivity.class));

        } else if (id == R.id.nav_Emerg) {
            startActivity(new Intent(MainActivity.this, EmergencyActivity.class));
        }
//         else if (id == R.id.nav_goals) {
//            startActivity(new Intent(Dashboard.this,Goal.class));
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
