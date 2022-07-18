package com.codniv.e_transport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codniv.e_transport.adapter.JourneyAdapter;
import com.codniv.e_transport.db.AppDatabase;
import com.codniv.e_transport.db.Journey;
import com.codniv.e_transport.utils.DistanceCalculator;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1 ;
    private boolean locationServiceStatus = false;
    Button StartStopJourney;
    Double prevLat;
    Double prevLon;
    private AppDatabase db;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String date = formatter.format(new Date());
    private double distance = 0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getDbInstance(this);
        StartStopJourney = findViewById(R.id.main_journey_button);
        StartStopJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this , new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_LOCATION_PERMISSION);
                }
                else{
                    if(!locationServiceStatus)
                    {
                        //START JOURNEY
                        locationServiceStatus=true;
                       startLocationServices();
                       StartStopJourney.setText("Stop Journey");
                    }
                    else{
                        //END JOURNEY
                        locationServiceStatus=false;
                        stopLocationServices();
                        StartStopJourney.setText("Start Journey");
                    }
                }
            }
        });

        Button viewJourney = findViewById(R.id.main_view_journey_button);
        viewJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , ViewJourneyActivity.class);
                startActivity(intent);
            }
        });

        Button viewUsers = findViewById(R.id.main_view_users_button);
        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,UserListActivity.class);
                startActivity(intent);
            }
        });

        Button createUsers = findViewById(R.id.main_create_user);
        createUsers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,CreateUserActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void startLocationServices() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(300);
        locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        Toast.makeText(this,"Location service started", Toast.LENGTH_SHORT).show();
    }

    private final LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult != null && locationResult.getLocations().size() > 0) {
                int LatestLocationIndex = locationResult.getLocations().size() - 1;
                double latitude = locationResult.getLocations().get(LatestLocationIndex).getLatitude();
                double longitude = locationResult.getLocations().get(LatestLocationIndex).getLongitude();
                Log.i("lat :", longitude + " " + latitude);

                double parsedLat = Math.round(latitude * 10000.0) / 10000.0;
                double parsedLon = Math.round(longitude * 10000.0) / 10000.0;

                if(prevLat == null && prevLon ==null)
                {
                    prevLat = parsedLat;
                    prevLon = parsedLon;
                }
                else  if(prevLat != parsedLat && prevLon !=parsedLon)
                {
                    Double Newdistance = DistanceCalculator.distance(prevLat,prevLon,latitude,longitude);
                    Log.i("lat",""+Newdistance);
                    if(Newdistance > 0.5 )
                    {
                        distance +=  Newdistance;
                        prevLat = parsedLat;
                        prevLon = parsedLon;
                        Log.i("lat","distance "+distance);
                        //INSERT INTO DATABASE
                        Journey journey = new Journey(date,parsedLat,parsedLon,distance,1);
                        db.journeyDao().insertJourney(journey);
                        Log.i("lat","inserted to database");

                    }
                }
                else {
                    Log.i("lat", "Same place ");
                }
            }
        }

    };

    private void stopLocationServices(){
        LocationServices.getFusedLocationProviderClient(MainActivity.this).removeLocationUpdates(locationCallback);
        Toast.makeText(MainActivity.this,"Location service stopped", Toast.LENGTH_SHORT).show();

    }
}