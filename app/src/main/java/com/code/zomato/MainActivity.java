package com.code.zomato;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();
    private Context context = MainActivity.this;

    private CardView cvSearch, cvCategories;
    private Toolbar toolbar;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private int locationRequestCode = 1000;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private double currentLatitude;
    private double currentLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        cvSearch = findViewById(R.id.cv_search);
        cvCategories = findViewById(R.id.cv_categories);

        cvSearch.setOnClickListener(this);
        cvCategories.setOnClickListener(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(20 * 1000);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {

                        currentLatitude = location.getLatitude();
                        currentLongitude = location.getLongitude();
                    }
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET}, locationRequestCode);
            }


        } else {
            // already permission granted
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {

                        currentLatitude = location.getLatitude();
                        currentLongitude = location.getLongitude();

                        Log.e(TAG, "currentLocation: " + currentLatitude + ", " + currentLongitude);
                    }
                }
            });
        }

    }

    private void initToolbar() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Foodie App");
    }

    @Override
    public void onClick(View view) {

        if (view == cvSearch) {

            Intent intent = new Intent(context, SearchActivity.class);
            intent.putExtra("lat", currentLatitude);
            intent.putExtra("long", currentLongitude);
            startActivity(intent);
            finish();
        }

        if (view == cvCategories) {

            Intent intent = new Intent(context, CategoriesActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e(TAG, "onRequestPermissionsResult requestCode: " + requestCode);
        switch (requestCode) {
            case 1000: {


                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if (location != null) {

                            currentLatitude = location.getLatitude();
                            currentLongitude = location.getLongitude();

                            Log.e(TAG, "permission location: " + currentLatitude + ", " + currentLongitude);
                        }
                    }
                });
                break;
            }
        }
    }

}