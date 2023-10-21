package com.android2023.evaluacionii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainActivity3 extends AppCompatActivity implements OnMapReadyCallback {

    double lat1, lng1, lat2, lng2, lat3, lng3;
    ImageView back, marker;
    Button btn;
    private GoogleMap googleMaps;
    private FusedLocationProviderClient fusedLocationClient;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        lat1 = getIntent().getDoubleExtra("Latitud 1", 0);
        lng1 = getIntent().getDoubleExtra("Longitud 1", 0);
        lat2 = getIntent().getDoubleExtra("Latitud 2", 0);
        lng2 = getIntent().getDoubleExtra("Longitud 2", 0);
        lat3 = getIntent().getDoubleExtra("Latitud 3", 0);
        lng3 = getIntent().getDoubleExtra("Longitud 3", 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        back = findViewById(R.id.imgBack2);
        marker = findViewById(R.id.imgLocation);
        btn = findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerUbicacionActual();
            }
        });
    }
    private void obtenerUbicacionActual() {
        // Verificar permisos
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si los permisos no están concedidos, solicítalos
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        // Obtener la ubicación actual
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            LatLng ubicacion = new LatLng(latitude,longitude);
                            googleMaps.addMarker(new MarkerOptions().position(ubicacion).title("Ubicacion Actual"));
                            googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,16));
                            Toast.makeText(getApplicationContext(), "Latitud: " + latitude + ", Longitud: " + longitude, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Ubicación no disponible", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMaps = map;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //LatLng sydney = new LatLng(-34, 151);
        if (lat2 != 0.0 && lng2 != 0.0){
            if(lat3 != 0.0 && lng3 != 0.0){
                LatLng direccion3 = new LatLng(lat3,lng3);
                map.addMarker(new MarkerOptions().position(direccion3).title("Direccion 3"));
            }
            LatLng direccion2 = new LatLng(lat2,lng2);
            map.addMarker(new MarkerOptions().position(direccion2).title("Direccion 2"));
        }
        LatLng direccion1 = new LatLng(lat1,lng1);
        map.addMarker(new MarkerOptions().position(direccion1).title("Direccion 1"));
        map.moveCamera(CameraUpdateFactory.newLatLng(direccion1));

    }

}